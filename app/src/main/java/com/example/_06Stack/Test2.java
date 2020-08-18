package com.example._06Stack;

// 计算器，有括号
public class Test2 {
    public static void main(String[] args) {
        String expression = "(10+2)*6+7+10*(10+2)";

        Stack1 numStack = new Stack1(10);
        Stack1 operaStack = new Stack1(10);
        StringBuilder sb = new StringBuilder();
        Num num;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            switch (operaStack.operaType(ch)) {
                case -1: // -1是()  什么也不干
                    if (i == expression.length() - 1) {
                        num = new Num(Integer.parseInt(sb.toString()));
                        numStack.push(num);
                    }
                    break;
                case 0: //  0是+-*/操作符
                    num = new Num(Integer.parseInt(sb.toString()));
                    numStack.push(num);
                    sb = new StringBuilder();
                    opera(operaStack, numStack, ch);
                    break;
                case 1: //  1是数字
                    sb.append(ch - 48);
                    if (i == expression.length() - 1) {
                        num = new Num(Integer.parseInt(sb.toString()));
                        numStack.push(num);
                    }
                    break;
            }
        }
        while (!operaStack.isEmpty()) {
            int num1 = numStack.pop().getValue();
            int num2 = numStack.pop().getValue();
            int opera = operaStack.pop().getValue();
            int value = operaStack.opera(num1, num2, opera);
            num = new Num(value);
            numStack.push(num);
        }
        System.out.println(numStack.pop().getValue());
    }

    // 进行操作
    private static void opera(Stack1 operaStack, Stack1 numStack, char ch) {
        if (operaStack.isEmpty()) { // 操作符栈为空 将操作符放入栈里面
            Num num = new Num(ch);
            num.setPriority(operaStack.operaProity(ch));
            operaStack.push(num);
        } else {
            if (operaStack.operaProity(ch) <= operaStack.peek().getPriority()) {
                // 当前操作符的优先级小于等于栈中的优先级
                int num1 = numStack.pop().getValue();
                int num2 = numStack.pop().getValue();
                int opera = operaStack.pop().getValue();
                int value = operaStack.opera(num1, num2, opera);
                Num num = new Num(value);
                numStack.push(num);
                opera(operaStack, numStack, ch);
            } else {
                // 当前操作符的优先级大于栈中的优先级
                Num num = new Num(ch);
                num.setPriority(operaStack.operaProity(ch));
                operaStack.push(num);
            }
        }
    }
}

// 栈
class Stack1 {
    private int maxSize; // 最大容量
    private int top = -1; // 栈顶
    private Num[] stack;
    private int priority = 0; // 优先级

    public Stack1(int maxSize) {
        this.maxSize = maxSize;
        stack = new Num[maxSize];
    }

    // 栈是否满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈是否为空
    public boolean isEmpty() {
        return top == -1;
    }

    // 打印栈
    public void show() {
        for (int i = 0; i <= top; i++) {
            System.out.println(stack[i].toString());
        }
    }

    // 压入栈
    public void push(Num num) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }
        stack[++top] = num;
    }

    // 弹出栈
    public Num pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top--];
    }

    // 取出数据，不弹出
    public Num peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top];
    }

    // 获取符号类型 -1是()  0是+-*/操作符  1是数字
    public int operaType(int opera) {
        // 如果是(),优先级增加
        if (opera == '(' || opera == ')') {
            if (opera == '(') {
                priority += 5;
            }
            if (opera == ')') {
                priority -= 5;
            }
            return -1;
        }
        if (opera == '+' || opera == '-' || opera == '*' || opera == '/') {
            return 0;
        }
        return 1;
    }

    // 获取操作符的优先级
    public int operaProity(int opera) {
        if (opera == '+' || opera == '-') {
            return priority;
        } else if (opera == '*' || opera == '/') {
            return 1 + priority;
        }
        return -1;
    }

    // 计算
    public int opera(int num1, int num2, int opera) {
        if (opera == '+') {
            return num2 + num1;
        } else if (opera == '-') {
            return num2 - num1;
        } else if (opera == '*') {
            return num2 * num1;
        } else if (opera == '/') {
            return num2 / num1;
        }
        return -1;
    }
}

class Num {
    private int value; // 操作符或数字
    private int priority; // 优先级

    public Num(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Num{" +
                "value=" + value +
                ", priority=" + priority +
                '}';
    }
}
