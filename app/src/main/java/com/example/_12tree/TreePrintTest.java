package com.example._12tree;

public class TreePrintTest {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);
        Node node15 = new Node(15);

        node4.left = node8;
        node4.right = node9;
        node5.left = node10;
        node5.right = node11;
        node6.left = node12;
        node6.right = node13;
        node7.left = node14;
        node7.right = node15;

        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        node1.left = node2;
        node1.right = node3;

        prePrint(node1);
        System.out.println();
        System.out.println("----------------------");
        midPrint(node1);
        System.out.println();
        System.out.println("----------------------");
        backPrint(node1);

        // 前序打印
        // 1=>2=>4=>8=>9=>5=>10=>11=>3=>6=>12=>13=>7=>14=>15
        // 中序打印
        // 8=>4=>9=>2=>10=>5=>11=>1=>12=>6=>13=>3=>14=>7=>15
        // 后序打印
        // 8=>9=>4=>10=>11=>5=>2=>12=>13=>6=>14=>15=>7=>3=>1
    }

    // 前序打印
    private static void prePrint(Node node) {
        if (node != null) {
            System.out.print(node.toString());
            prePrint(node.left);
            prePrint(node.right);
        }
    }


    // 中序打印
    private static void midPrint(Node node) {
        if (node != null) {
            midPrint(node.left);
            System.out.print(node.toString());
            midPrint(node.right);
        }
    }


    // 中序打印
    private static void backPrint(Node node) {
        if (node != null) {
            backPrint(node.left);
            backPrint(node.right);
            System.out.print(node.toString());
        }
    }
}

class Node {
    int id;
    Node left;
    Node right;

    public Node(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id + "=>";
    }
}
