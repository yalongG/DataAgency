package com.example._13huffmantree;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 赫夫曼树
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrder();
    }

    // 创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        // 第一步为了操作方便
        // 1.遍历 arr 数组
        // 2.将arr的每个元素构建成一个Node
        // 3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        // 处理过程是循环的过程
        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);
            // 取出跟节点权值最小的二叉树
            // (1)取出权值最小的结点(二叉树)
            Node left = nodes.get(0);
            // (2)取出权值第二小的结点(二叉树)
            Node right = nodes.get(1);
            // (3)构建一颗新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            // (4)从ArrayList删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            // (5)将parent加入到nodes
            nodes.add(parent);
        }
        // 返回赫夫曼树的头结点
        return nodes.get(0);
    }
}

// 创建结点类
// 为了让Node 对象持续排序 Collections 集合排序
// 让Node 实现Comparable 接口
class Node implements Comparable<Node> {
    int value; // 结点权值
    Node left; // 指向左子结点
    Node right; // 指向右子结点

    // 写一个前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(@NonNull Node o) {
        // 从小到大进行排序
        return this.value - o.value;
    }
}
