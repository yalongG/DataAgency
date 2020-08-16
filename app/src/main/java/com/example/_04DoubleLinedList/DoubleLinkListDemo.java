package com.example._04DoubleLinedList;

public class DoubleLinkListDemo {
    public static void main(String[] args) {
        DoubleLinkList linkedList = new DoubleLinkList();
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
//        linkedList.show();
//        linkedList.update(new HeroNode(2, "小卢", "玉麒麟～～"));
//        linkedList.delete(1);
//        linkedList.show();
//        linkedList.delete(6);
        linkedList.show();
    }
}

// 创建一个双向链表的类
class DoubleLinkList {
    // 先初始化头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    // 返回头节点
    public HeroNode getHead() {
        return head;
    }

    // 遍历双向列表的方法
    public void show() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    // 添加数据
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    // 根据id有序添加
    public void addByOrder(HeroNode node) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.getNo() > node.getNo()) {
                break;
            } else if (temp.next.getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("已经存在。。。");
        } else {
            // 添加 如果是最后一个
            if (temp.next == null) {
                temp.next = node;
                node.pre = temp;
            } else {
                node.next = temp.next;
                node.pre = temp;
                temp.next.pre = node;
                temp.next = node;
            }
        }
    }

    // 更新链表数据
    public void update(HeroNode node) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNo() == node.getNo()) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.setName(node.getName());
            temp.setNickname(node.getNickname());
        } else {
            System.out.println("找不到");
        }
    }

    // 删除链表数据
    public void delete(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.getNo() == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) {
                // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("找不到");
        }
    }
}


// 定义一个HeroNode，每一个HeroNode 对象就是一个节点
class HeroNode {
    private int no;
    private String name;
    private String nickname;
    public HeroNode next; // 指向下一个节点，默认为null
    public HeroNode pre; // 指向前一个节点，默认为null

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