package com.example._06Stack;

import java.util.Scanner;

// 数组模式栈
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; // 控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("show:表示显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:表示添加数据到栈");
            System.out.println("pop:表示从栈取出数据");

            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.show();
                    break;
                case "exit":
                    loop = false;
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    try {
                        stack.push(value);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据" + stack.pop());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }
}

// 数组模拟栈
class ArrayStack {
    private int maxSize;// 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据放在该数组中
    private int top = -1; // top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int e) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈已经满了");
            return;
        }
        stack[++top] = e;
    }

    // 出栈pop
    public int pop() {
        // 先判断栈是否为空
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top--];
    }

    // 遍历栈,遍历时，需要从栈顶开始显示数据
    public void show() {
        if (isEmpty()) {
            System.out.println("栈为空");
        }

        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }
}
