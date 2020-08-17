package com.example._06Stack;

public class Test {
    public static void main(String[] args) {
        String expression = "10+2*6+7+10*10";

        Stack numStack = new Stack(10);
        Stack operaStack = new Stack(10);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (operaStack.isOpera(ch)) { // 如果是符号
                numStack.push(Integer.parseInt(sb.toString()));
                sb = new StringBuilder();
                opera(operaStack, numStack, ch);
            } else { // 如果数字
                sb.append(ch - 48);
                if (i == expression.length() - 1) { // 如果是最后一位
                    numStack.push(Integer.parseInt(sb.toString()));
                }
            }
        }

        while (!operaStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int opera = operaStack.pop();
            numStack.push(operaStack.opera(opera, num1, num2));
        }
        System.out.println(numStack.pop());
    }

    // 操作数据
    private static void opera(Stack operaStack, Stack numStack, char ch) {
        if (!operaStack.isEmpty()) { // 不为空
            if (operaStack.operaPriority(ch) <= operaStack.operaPriority(operaStack.peek())) {
                // 当前操作符的优先级小于栈中的优先级
                int num1 = numStack.pop();
                int num2 = numStack.pop();
                int opera = operaStack.pop();
                numStack.push(operaStack.opera(opera, num1, num2));
                // 继续判断当前操作符的优先级跟下一个优先级进行对比
                opera(operaStack, numStack, ch);
            } else {
                // 当前符号的优先级大于栈中符号的优先级
                operaStack.push(ch);
            }
        } else { // 为空
            operaStack.push(ch);
        }

    }
}

class Stack {
    private int maxSize;
    private int top = -1;
    private int[] stack;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满了");
            return;
        }
        stack[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }

    public boolean isOpera(int opera) {
        return opera == '+' || opera == '-' || opera == '*' || opera == '/';
    }

    public int operaPriority(int opera) {
        if (opera == '*' || opera == '/') {
            return 1;
        } else if (opera == '+' || opera == '-') {
            return 0;
        }
        return -1;
    }

    public int opera(int opera, int num1, int num2) {
        if (opera == '*') {
            return num2 * num1;
        } else if (opera == '/') {
            return num2 / num1;
        } else if (opera == '+') {
            return num2 + num1;
        } else if (opera == '-') {
            return num2 - num1;
        }
        return -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void show() {
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i]);
        }
    }
}