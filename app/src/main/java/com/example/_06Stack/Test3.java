package com.example._06Stack;

import java.util.Stack;

public class Test3 {
    public static void main(String[] args) {
        String expression = "(10+2)*6+7+2*(10*(10+2))";
        Stack<Integer> numStack = new Stack<>();
        Stack<Opera> operaStack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        OperaManager operaManager = new OperaManager();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            switch (operaManager.operaType(ch)) {
                case -1: // ()
                    if (i == expression.length() - 1) { // 如果是最后一个直接放入栈中
                        numStack.push(Integer.parseInt(sb.toString()));
                    }
                    break;
                case 0: // 0 符号
                    numStack.push(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                    opera(operaStack, numStack, operaManager, ch);
                    break;
                case 1: // 1数字
                    sb.append(ch - 48);
                    if (i == expression.length() - 1) { // 如果是最后一个直接放入栈中
                        numStack.push(Integer.parseInt(sb.toString()));
                    }
                    break;
            }
        }

        while (!operaStack.isEmpty()) {
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int opera = operaStack.pop().getValue();
            numStack.push(operaManager.opera(num1, num2, opera));
        }
        System.out.println(numStack.pop());
    }

    // 进行操作
    private static void opera(Stack<Opera> operaStack, Stack<Integer> numStack, OperaManager operaManager, char ch) {
        if (operaStack.isEmpty()) { // 如果是空，直接放入
            operaStack.push(new Opera(ch, operaManager.operaPriority(ch)));
        } else { // 不为空
            if (operaManager.operaPriority(ch) <= operaStack.peek().getPriority()) { // 如果当前符号的优先级小于等于栈顶符号的优先级
                int num1 = numStack.pop();
                int num2 = numStack.pop();
                int opera = operaStack.pop().getValue();
                numStack.push(operaManager.opera(num1, num2, opera));
                opera(operaStack, numStack, operaManager, ch); // 继续判断
            } else {
                operaStack.push(new Opera(ch, operaManager.operaPriority(ch)));
            }
        }
    }
}

// 操作符的管理类
class OperaManager {
    private int priority = 0;

    // -1 () 0 符号 1数字
    public int operaType(int ch) {
        if (ch == '(') {
            priority += 5;
            return -1;
        }
        if (ch == ')') {
            priority -= 5;
            return -1;
        }

        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return 0;
        }
        return 1;
    }

    // 操作符的优先级
    public int operaPriority(int ch) {
        if (ch == '+' || ch == '-') {
            return priority;
        } else if (ch == '*' || ch == '/') {
            return 1 + priority;
        }
        return -1;
    }

    public int opera(int num1, int num2, int opera) {
        int value;
        switch (opera) {
            case '+':
                value = num2 + num1;
                break;
            case '-':
                value = num2 - num1;
                break;
            case '*':
                value = num2 * num1;
                break;
            case '/':
                value = num2 / num1;
                break;
            default:
                value = 0;
                break;
        }
        return value;

    }
}

class Opera {
    private int value;
    private int priority;

    public Opera(int value, int priority) {
        this.value = value;
        this.priority = priority;
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
        return "Opera{" +
                "value=" + value +
                ", priority=" + priority +
                '}';
    }
}