package com.example._14binarysorttree;

// 二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的添加结点 到 binarySortTree
        for (int anArr : arr) {
            Node node = new Node(anArr);
            binarySortTree.add(node);
        }
        binarySortTree.infixOrder();
    }
}

// 创建二叉排序树
class BinarySortTree {
    private Node root;

    // 添加结点的方法
    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public void infixOrder() {
        if (this.root == null) {
            System.out.println("当前二叉排序树为空，不能遍历");
        } else {
            this.root.infixOrder();
        }
    }
}

// 创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 添加结点的方法
    // 递归的形式添加，注意要满足二叉排序数的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断传入的结点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            if (this.left == null) { // 如果当前结点左子结点为null
                this.left = node;
            } else {
                this.left.add(node); // 递归向左子树添加
            }
        } else { // 添加的结点的值，大于当前结点的值
            if (this.right == null) {  // 如果右子树为空
                this.right = node;
            } else {
                this.right.add(node); // 递归向右子树添加
            }
        }
    }

    // 中序遍历二叉树
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
