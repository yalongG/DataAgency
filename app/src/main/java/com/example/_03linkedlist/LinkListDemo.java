package com.example._03linkedlist;

public class LinkListDemo {
    public static void main(String[] args) {
//        NodeList hear = getNodeList();
//        printNodeList(hear);

//        System.out.println("--------------");
//        getLastNode(hear,3);
//        NodeList reverse = reverse(hear);
//        System.out.println("--------------");
//        printNodeList(reverse);
//        System.out.println("--------------");
//        printReverse(hear);

        NodeList nodeList1 = new NodeList(1);
        NodeList nodeList3 = new NodeList(3);
        NodeList nodeList5 = new NodeList(5);
        NodeList nodeList7 = new NodeList(7);
        nodeList1.next = nodeList3;
        nodeList3.next = nodeList5;
        nodeList5.next = nodeList7;

        NodeList nodeList2 = new NodeList(2);
        NodeList nodeList4 = new NodeList(4);
        NodeList nodeList6 = new NodeList(6);
        nodeList2.next = nodeList4;
        nodeList4.next = nodeList6;

//        printNodeList(nodeList1);
//        printNodeList(nodeList2);
        NodeList nodeList = merge(nodeList1, nodeList2);
        printNodeList(nodeList);
    }

    // 合并两个有序链表
    private static NodeList merge(NodeList nodeList1, NodeList nodeList2) {
        NodeList hear = new NodeList(-1);
        NodeList cur = hear;
        int flag; // 1表示链表1数据为null，2表示链表2数据为null
        while (true) {
            if (nodeList1 == null) {
                flag = 1;
                break;
            }
            if (nodeList2 == null) {
                flag = 2;
                break;
            }
            if (nodeList1.getValue() < nodeList2.getValue()) { // 判断大小
                NodeList next = nodeList1.next;
                nodeList1.next = null;
                cur.next = nodeList1;
                cur = cur.next;
                nodeList1 = next;
            } else {
                NodeList next = nodeList2.next;
                nodeList2.next = null;
                cur.next = nodeList2;
                cur = cur.next;
                nodeList2 = next;

            }
        }
        if(flag == 1){
            while (nodeList2 != null){
                NodeList next = nodeList2.next;
                nodeList2.next = null;
                cur.next = nodeList2;
                cur = cur.next;
                nodeList2 = next;
            }
        }else{
            while (nodeList1 != null){
                NodeList next = nodeList1.next;
                nodeList1.next = null;
                cur.next = nodeList1;
                cur = cur.next;
                nodeList1 = next;
            }
        }
        return hear.next;
    }

    // 反向打印链表
    private static void printReverse(NodeList hear) {
        if (hear != null) {
            printReverse(hear.next);
            System.out.println(hear);
        }
    }

    // 反转链表
    private static NodeList reverse(NodeList hear) {
        NodeList cur = hear;
        NodeList next;
        NodeList reverseHear = new NodeList(-1);
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHear.next;
            reverseHear.next = cur;
            cur = next;
        }
        return reverseHear.next;
    }

    // 获取倒数第k个元素
    private static void getLastNode(NodeList hear, int k) {
        NodeList cur = hear;
        NodeList low = hear;
        int i = 1;
        while (cur != null) {
            if (i > k) {
                low = low.next;
            }
            i++;
            cur = cur.next;
        }
        System.out.println(low.toString());
    }

    // 打印元素
    private static void printNodeList(NodeList hear) {
        NodeList cur = hear;
        while (cur != null) {
            System.out.println(cur.toString());
            cur = cur.next;
        }
    }

    // 添加元素
    private static NodeList getNodeList() {
        NodeList nodeList1 = new NodeList(1);
        NodeList nodeList2 = new NodeList(2);
        NodeList nodeList3 = new NodeList(3);
        NodeList nodeList4 = new NodeList(4);
        NodeList nodeList5 = new NodeList(5);
        NodeList nodeList6 = new NodeList(6);
        NodeList nodeList7 = new NodeList(7);
        NodeList nodeList8 = new NodeList(8);
        NodeList nodeList9 = new NodeList(9);
        NodeList nodeList10 = new NodeList(10);
        nodeList1.next = nodeList2;
        nodeList2.next = nodeList3;
        nodeList3.next = nodeList4;
        nodeList4.next = nodeList5;
        nodeList5.next = nodeList6;
        nodeList6.next = nodeList7;
        nodeList7.next = nodeList8;
        nodeList8.next = nodeList9;
        nodeList9.next = nodeList10;
        return nodeList1;
    }
}

class NodeList {
    private int value;
    public NodeList next;

    public NodeList(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NodeList{" +
                "value=" + value +
                '}';
    }
}