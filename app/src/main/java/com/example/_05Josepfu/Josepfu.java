package com.example._05Josepfu;

// 约瑟夫问题
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLikedList likedList = new CircleSingleLikedList();
        likedList.addBoy(5);
        likedList.show();
        likedList.countBoy(1, 2, 5);
    }
}

// 创建一个环形的单向链表
class CircleSingleLikedList {
    // 创建一个first节点，当前没有编号
    private Boy first;

    // 添加小孩节点，构建成一个环形链表
    public void addBoy(int nums) {
        // nums 做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针，帮忙构建环形链表
        // 使用for循环来创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个节点
            if (i == 1) {
                first = boy;
                first.setNext(first); // 构成环
                curBoy = first; // 让curBoy指向第一个小孩
            } else {
                boy.setNext(curBoy.getNext());
                curBoy.setNext(boy);
                curBoy = curBoy.getNext();
            }
        }
    }

    // 遍历当前的环形链表
    public void show() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("无数据");
            return;
        }
        Boy curBoy = first;
        do {
            System.out.println(curBoy.toString());
            curBoy = curBoy.getNext();
        } while (first != curBoy);
    }

    // 根据用户的输入，计算出出圈的顺序

    /**
     * @param startNo     表示从第几个小孩开始数数
     * @param countNumber 表示数几下
     * @param nums        表示走出有多少个小孩在圈中
     */
    public void countBoy(int startNo, int countNumber, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误");
            return;
        }
        // 创建一个辅助指针，帮助完成小孩出圈
        Boy help = first;
        while (true) {
            if (help.getNext() == first) { // 说明helper指向最后小孩节点
                break;
            }
            help = help.getNext();
        }
        // 先让frist和help移动k-1次
        // 当小孩报数前，让first和helper指针同时移动m-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            help = help.getNext();
        }
        // 当小孩报数时，让first和helper指针同时移动m - 1次，然后出圈
        // 这里是一个循环操作，知道圈中只有一个节点

        while (true) {
            if (help == first) { // 说明圈中只有一个节点
                System.out.println(help.toString());
                break;
            }
            // 让first 和 helper指针同时移动countNum - 1次
            for (int i = 0; i < countNumber - 1; i++) {
                help = help.getNext();
                first = first.getNext();
            }
            // 这时first指向的节点，就是出圈的小孩节点
            System.out.println(first.toString());
            // 这时将first指向的小孩出圈
            first = first.getNext();
            help.setNext(first);
        }
    }
}

//创建一个boy类，表示一个节点
class Boy {
    private int no; // 编号
    private Boy next; // 指向下一个节点

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
