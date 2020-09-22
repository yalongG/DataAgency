package com.example._12tree;

// 线索化二叉树 中序遍历二叉树
public class _03ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 中序线索二叉树
        HeroNode1 root = new HeroNode1(1, "tom");
        HeroNode1 node2 = new HeroNode1(3, "jack");
        HeroNode1 node3 = new HeroNode1(6, "smith");
        HeroNode1 node4 = new HeroNode1(8, "mary");
        HeroNode1 node5 = new HeroNode1(10, "king");
        HeroNode1 node6 = new HeroNode1(14, "dim");

        // 二叉树
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;

        node3.left = node6;

        BinaryTree1 binaryTree1 = new BinaryTree1();
        binaryTree1.setRoot(root);
        binaryTree1.infixOrder();
        System.out.println("-------------------");
        binaryTree1.threadedNodes();

//        HeroNode1 leftNode = node5.left;
//        System.out.println(leftNode.toString());

        // 当线索化二叉树后，不能在使用原来的遍历方法
//        binaryTree1.infixOrder();
        binaryTree1.threadedList();
    }
}

// 定义BinaryTree，实现了线索化功能的二叉树
class BinaryTree1 {
    private HeroNode1 root;

    // 为了实现线索化，需要创建要给当前节点的前驱节点的指针
    // 在递归进行线索化是，pre 总是保留前一个节点
    private HeroNode1 pre = null;

    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    // 遍历线索化二叉树方法
    public void threadedList() {
        // 定义一个变量，存储当前遍历的结点，从root开始
        HeroNode1 node = root;
        while (node != null) {
            // 循环找到leftType == 1的结点
            // 后面随着遍历而变化，因为leftType == 1时，说明该节点是按照线索化处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.left;
            }
            // 打印当前这个结点
            System.out.println(node);
            // 如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                node = node.right;
                System.out.println(node);
            }
            // 替换这个遍历的结点
            node = node.right;
        }
    }

    // 编写对二叉树进行中序线索化的方法
    // node 就是当前需要线索化的节点
    public void threadedNodes(HeroNode1 node) {
        // 如果node == null，不能线索化
        if (node == null) {
            return;
        }
        // 1.先线索化左子树
        threadedNodes(node.left);
        // 2.线索化当前节点
        // 2.1 处理当前节点的前驱节点
        if (node.left == null) {
            // 让当前节点的左指针指向前序节点
            node.left = pre;
            //  修改当前节点的左指针的类型,指向的是前驱节点
            node.setLeftType(1);
        }

        // 2.2 处理后继结点
        if (pre != null && pre.right == null) {
            // 让前驱结点的右指针指向当前结点
            pre.right = node;
            // 修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        pre = node;
        // 3.在线索化右子树
        threadedNodes(node.right);
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后续遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序遍历
    public HeroNode1 preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
        return null;
    }

    // 中序遍历
    public HeroNode1 infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
        return null;
    }

    // 后续遍历
    public HeroNode1 postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
        return null;
    }

    // 删除节点
    public void delNode(int no) {
        if (this.root != null) {

            // 如果只有一个root节点，这里立即判断root是不是就是要删除的节点
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空，无法删除");
        }
    }
}

// 创建HeroNode
// 先创建HeroNode节点
class HeroNode1 {
    private int no;
    private String name;
    public HeroNode1 left; // 默认null
    public HeroNode1 right; // 默认null

    // 说明
    // 1.如果leftType == 0 表示指向的是左子树，如果 1 则表示前驱节点
    // 2.如果rightType == 0 表示指向的是右子树，如果 1 表示指向后继节点
    private int leftType;
    private int rightType;

    public HeroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 递归删除节点
    // 如果删除的节点是叶子节点，则删除该节点
    // 如果删除的节点是非叶子节点，则删除该子数
    public void delNode(int no) {
        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                return;
            }
        }

        if (this.right != null) {
            if (this.right.no == no) {
                this.right = null;
                return;
            }
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }
    }

    // 编写前序遍历的方法
    public void preOrder() {
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后续遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node，如果没有找到返回null
     */
    public HeroNode1 preOrderSearch(int no) {
        // 比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        HeroNode1 resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }


    /**
     * 中序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node，如果没有找到返回null
     */
    public HeroNode1 infixOrderSearch(int no) {
        HeroNode1 resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 后序遍历查找
     *
     * @param no 查找no
     * @return 如果找到就返回该Node，如果没有找到返回null
     */
    public HeroNode1 postOrderSearch(int no) {
        HeroNode1 resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }

        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        }
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}
