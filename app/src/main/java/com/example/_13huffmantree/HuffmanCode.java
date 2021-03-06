package com.example._13huffmantree;

import android.support.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 哈夫曼编码
public class HuffmanCode {
    public static void main(String[] args) {
//        String str = "i like like like java do you like a java";
//        byte[] contentBytes = str.getBytes();
//
//        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
//        System.out.println(new String(huffmanCodeBytes));
//
//        // 如何将数据进行解压(解码)
//        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println(new String(decode));

//        zipFile("/Users/mac/Desktop/Flutter 实战.pdf", "/Users/mac/Desktop/file.zip");
//        unZipFile();
    }

    // 编写一个方面，完成对压缩文件的解压

    /**
     * 完成对压缩文件的解压
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    private static void unZipFile(String zipFile, String dstFile) {
        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = decode(codes, huffmanBytes);
            // 将bytes数组写入到目标文件
            os = new FileOutputStream(dstFile);
            // 写数据到 dstFile中
            os.write(bytes);
            os.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (ois != null) {
                    ois.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 编写方法，将一个文件进行压缩

    /**
     * 将一个文件进行压缩
     *
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放大哪个目录
     */
    private static void zipFile(String srcFile, String dstFile) {
        FileInputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            // 创建文件的输入流
            is = new FileInputStream(srcFile);
            // 创建一个和源文件大小的byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 直接对源文件压缩
            byte[] bytes = huffmanZip(b);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(bytes);
            // 这里我们以对象流的方式写入赫夫曼编码，是为了以后我们恢复源文件时使用
            // 注意一定要把赫夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 完成数据的解压
    // 思路
    // 1.将 huffmanCodeBytes 重新先转成 赫夫曼编码对应的二进制字符串
    // 2.将 赫夫曼编码对应的二进制的字符串 对照 赫夫曼编码 转成原来的字符串


    /**
     * 编写一个方法，完成对压缩数据的解码
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到 huffmanBytes 对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            stringBuilder.append(byteToBitString(i != huffmanBytes.length - 1, huffmanBytes[i]));
        }

        // 把字符串按照指定的赫夫曼编码进行解码
        // 把赫夫曼编码表进行调换，因为反向查询
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解成一个索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 递增的取出key
                String key = stringBuilder.substring(i, i + count); // i 不动 让count 移动，指定匹配到一个字符
                if (map.containsKey(key)) {
                    b = map.get(key);
                    flag = false;
                } else {
                    count++;
                }
            }
            list.add(b);
            i += count;
        }

        // 当for循环结束后，list中就存放了所有的字符
        // 把list中的数据放入byte[] 并返回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制的字符串
     *
     * @param flag 标志是否需要补高位 如果是true表示需要补高位,否则不用 如果是最后一个字节，无须补高位
     * @param b    b
     * @return 是该b 对应的二进制的字符串(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量保存 b
        int temp = b;
        // 如果是正数 还存在补高位的问题
        if (flag) {
            temp |= 256; // 按位与256
        }

        String str = Integer.toBinaryString(temp);// 返回的是2进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    // 使用一个方法，将前面的方法封装起来，便于我们的调用

    /**
     * @param bytes 原始的字符串对应的字节数组
     * @return 是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<NodeList> nodes = getNodes(bytes);
        // 根据 nodes 创建赫夫曼树
        NodeList huffmanTree = createHuffmanTree(nodes);
        // 对应的赫夫曼编码 根据赫夫曼树
        Map<Byte, String> huffmanCodes = getCodes(huffmanTree);
//        {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
//        System.out.println(huffmanCodes.toString());
        // 根据赫夫曼编码对字节数组进行压缩
        return zip(bytes, huffmanCodes);
    }


    /**
     * 编写一个方法,将字符串对应的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码 压缩后的字节数组
     *
     * @param bytes        原始的字符串对应的byte数组
     * @param huffmanCodes 生成的赫夫曼编码map
     * @return 返回赫夫曼编码处理后的byte[]数组
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1. 利用 huffmanCodes 将bytes转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        // 将 字符串转成byte[]数组
        // 统计返回 byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建存储压缩后的 byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为每8位对应一个byte，所以步长+8
            String strByte;
            // 将strByte转成一个byte，放入到 huffmanCodeBytes
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i, stringBuilder.length());
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            //            huffmanCodeBytes[index++] = Byte.parseByte(strByte);
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return huffmanCodeBytes;
    }

    // 生成赫夫曼树对应的赫夫曼编码
    // 思路：
    // 1.将赫夫曼编码表存放在Map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2.在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    // 为了调用方便，我们重载 getCodes
    private static Map<Byte, String> getCodes(NodeList root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        // 处理root的右子树
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能:将传入的node结点的所有叶子结点的赫夫曼编码，存放到 huffmanCodes 中
     *
     * @param node          传入的结点，默认从跟节点
     * @param code          路径:左子结点为0 右子节点为1
     * @param stringBuilder 路径的拼接
     */
    private static void getCodes(NodeList node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        if (node != null) { // 如果node == null 不处理
            // 判断当前node 是叶子节点还是非叶子结点
            if (node.data == null) { // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { // 说明是一个叶子结点
                // 就表示找到某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    // 前序遍历
    private static void preOrder(NodeList root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    // 通过list创建对应的霍夫曼树
    private static NodeList createHuffmanTree(List<NodeList> nodeLists) {
        while (nodeLists.size() > 1) {
            Collections.sort(nodeLists);
            NodeList leftNode = nodeLists.get(0);
            NodeList rightNode = nodeLists.get(1);
            // 创建一棵新的二叉树，它的根节点没有data,只有权值
            NodeList parent = new NodeList(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 将已经处理的两棵二叉树从nodes删除
            nodeLists.remove(leftNode);
            nodeLists.remove(rightNode);
            // 将新的二叉树加入到nodes
            nodeLists.add(parent);
        }
        return nodeLists.get(0);
    }

    /**
     * 接收一个字节数组转成一个集合
     *
     * @param bytes 字节数组
     * @return 集合
     */
    private static List<NodeList> getNodes(byte[] bytes) {
        List<NodeList> nodes = new ArrayList<>();
        // 存储每一个byte出现的次数
        // 遍历bytes
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) { // Map还没有这个字符数据，第一次
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        // 把每一个键值对转成一个Node对象，并加入nodes集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new NodeList(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

// 创建node，加数据和权值
class NodeList implements Comparable<NodeList> {
    Byte data; // 存放数据(字符)本身，比如'a' = 97  ' ' = 32
    int weight; // 权值,表示字符出现的次数
    NodeList left;
    NodeList right;

    public NodeList(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(@NonNull NodeList o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "NodeList{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
