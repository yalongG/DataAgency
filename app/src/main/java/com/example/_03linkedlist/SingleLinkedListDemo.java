package com.example._03linkedlist;

public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.show();
//        linkedList.add(new HeroNode(1,"宋江","及时雨"));
//        linkedList.add(new HeroNode(2,"卢俊义","玉麒麟"));
//        linkedList.add(new HeroNode(3,"吴用","智多星"));
//        linkedList.add(new HeroNode(4,"林冲","豹子头"));
        linkedList.addByOrder(new HeroNode(4, "林冲", "豹子头"));
        linkedList.addByOrder(new HeroNode(3, "吴用", "智多星"));
        linkedList.addByOrder(new HeroNode(2, "卢俊义", "玉麒麟"));
        linkedList.addByOrder(new HeroNode(1, "宋江", "及时雨"));
        linkedList.addByOrder(new HeroNode(3, "吴用", "智多星"));
        linkedList.show();
//        linkedList.update(new HeroNode(2, "小卢", "玉麒麟～～"));
        linkedList.delete(3);
        linkedList.show();
        linkedList.delete(6);
        linkedList.show();

    }
}

// 定义SingleLinkedList 管理我们的英雄
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动
    private HeroNode head = new HeroNode(0, "", "");

    // 添加节点到单向链表
    // 思路：当不考虑编号的顺序时
    // 1.找到当前链表的最后节点
    // 2.将最后这个节点的next 指向新的节点
    public void add(HeroNode node) {
        // 因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp = head;
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环是，temp就指向了链表的最后
        // 将最后这个节点的next 指向新的节点
        temp.next = node;
    }

    // 第二种添加英雄的方法，根据排名将英雄出入指定位置
    // （如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode node) {
        // 因为head节点不能动，因此我们需要一个辅助变量temp
        // 因为是单链表，因为我们找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 标示添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) { // 说明temp已经在链表的最后
                break;
            }
            if (temp.next.getNo() > node.getNo()) { // 位置找到，就在temp的后面添加
                break;
            } else if (temp.next.getNo() == node.getNo()) { // 说明希望添加的heroNode的编号已然存在
                flag = true; // 说明编号存在
                break;
            }
            temp = temp.next; // 后移，遍历当前链表
        }
        // 判断flag的值
        if (flag) { // 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", node.getNo());
        } else { // 插入到链表中,在temp后面
            node.next = temp.next;
            temp.next = node;
        }
    }

    // 修改节点的信息，根据no编号来修改，即no编号不能修改
    // 说明
    // 1.根据node的no 来修改
    public void update(HeroNode node) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 找到需要修改的节点，根据no标号
        // 先定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false; // 表示是否找到该节点
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.getNo() == node.getNo()) { // 找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到修改的节点
        if (flag) {
            temp.setName(node.getName());
            temp.setName(node.getNickname());
        } else {
            System.out.printf("没有找到 编号%d的节点，不能修改\n", node.getNo());
        }
    }

    // 删除节点
    // 思路
    // 1.head 不能动，因此我们需要一个temp辅助节点，找到待删除节点的前一个
    // 2.说明我们在比较是，是temp.next.no 和需要删除的节点的no比较
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标志是否找到待删除的节点
        while (true) {
            if (temp.next == null) { // 已经到链表的最后
                break;
            }
            if (temp.next.getNo() == no) {
                // 找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { // 找到
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d 节点不存在\n", no);
        }
    }

    // 显示链表
    public void show() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp.toString());
            // 将temp后移
            temp = temp.next;
        }
    }
}

// 定义一个HeroNode，每一个HeroNode 对象就是一个节点
class HeroNode {
    private int no;
    private String name;
    private String nickname;
    public HeroNode next; // 指向下一个节点

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
