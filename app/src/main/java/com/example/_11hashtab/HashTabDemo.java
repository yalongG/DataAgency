package com.example._11hashtab;

import java.util.Scanner;

// 哈希表 demo
public class HashTabDemo {
    public static void main(String[] args) {
        //创建哈希表
        HashTab hashTab = new HashTab(7);
        // 写一个简单菜单测试
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:显示雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入id");
                    hashTab.findEmp(scanner.nextInt());
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

// 创建HashTab 管理多条链表
class HashTab {
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    // 构造器
    public HashTab(int size) {
        // 初始化 empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        this.size = size;
        // 分别初始化每个链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员
    public void add(Emp emp) {
        // 根据员工的id，得到该员工应当添加到那条链表
        empLinkedListArray[hashFun(emp.id)].add(emp);
    }

    // 根据输入的id，查找雇员
    public void findEmp(int id) {
        Emp emp = empLinkedListArray[hashFun(id)].findEmpById(id);
        if (emp != null) {
            System.out.println(emp.toString());
        } else {
            System.out.println("在哈希表中，没有找到该雇员～");
        }
    }

    //编写散列函数，使用一个简单的取模法
    private int hashFun(int id) {
        return id % size;
    }

    // 遍历所有的链表，遍历hashtab
    public void list() {
        for (EmpLinkedList linkedList : empLinkedListArray) {
            linkedList.list();
        }
    }
}

//创建一个EmpLinkedList，表示链表
class EmpLinkedList {
    // 头指针，指向第一个Emp，因此我们这个链表的head 是直接指向第一个Emp
    private Emp head; // 默认为空

    // 添加雇员到链表
    // 说明
    // 1.假定当添加雇员时，id是自增长的，即id的分配总是从小到大
    //   因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        // 如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是添加第一个雇员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (curEmp.next != null) { // 说明到链表最后
            curEmp = curEmp.next;
        }
        // 退出时直接将emp加入链表
        curEmp.next = emp;
    }

    // 遍历链表的雇员信息
    public void list() {
        if (head == null) { // 说明链表为空
            System.out.println("当前链表为空");
            return;
        }
        System.out.println("当前链表的信息为");
        Emp curEmp = head;
        while (curEmp != null) {
            System.out.println(curEmp.toString());
            curEmp = curEmp.next;
        }
    }

    // 根据id 查找雇员
    // 如果查找到，就返回Emp，如果没有找到，就返回null
    public Emp findEmpById(int id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (curEmp != null) {
            if (curEmp.id == id) { // 找到
                break;
            }
            curEmp = curEmp.next;
        }
        return curEmp;
    }
}


// 表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next; // 默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


