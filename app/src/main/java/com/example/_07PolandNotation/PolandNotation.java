package com.example._07PolandNotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 逆波兰表达式
public class PolandNotation {
    public static void main(String[] args) {
        // 先定义一个逆波兰表达式
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        // 说明：为了方便，逆波兰表达式数字和符号使用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 -";
//        // 思路
//        // 1.现将 3 4 + 5 * 6 - 1 放到ArrayList中
//        // 2.将ArrayList 传递给一个方法，配合栈完成计算
//        List<String> listString = getListString(suffixExpression);
//        System.out.println(listString);
//        System.out.println(calculate(listString));

        // (3+4)*2-(5*3)+6
        String s = "(3+4)*2-(5*3)+6";
        System.out.println(getPoland(s));
        String s1 = "3 4 + 2 * 5 3 * - 6 +";
        System.out.println(calculate(getListString(s1)));
        System.out.println(calculate(getListString(getPoland(s))));
    }

    private static String getPoland(String s) {
        StringBuilder poland = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        OperaManage operaManage = new OperaManage();
        Stack<Opera> operaList = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            // 1 是括号 0 是符号 -1 是数字
            switch (operaManage.operType(ch)) {
                case 1:  // 1 是括号
                    if (i == s.length() - 1) {
                        poland.append(" ");
                        poland.append(sb.toString());
                    }
                    break;
                case 0:  // 0 是符号
                    poland.append(" ");
                    poland.append(sb.toString());
                    sb = new StringBuilder();
                    Opera operaNew = new Opera(ch, operaManage.getOperPriority(ch));
                    merage(operaList, operaNew, poland);
                    break;
                case -1:  //-1 是数字
                    sb.append(ch - 48);
                    if (i == s.length() - 1) {
                        poland.append(" ");
                        poland.append(sb.toString());
                    }
                    break;
            }
        }

        while (!operaList.isEmpty()) {
            poland.append(" ");
            poland.append(new String(new char[]{(char) operaList.pop().value}));
        }
        String string = poland.toString();

        return string.substring(1, string.length());
    }

    private static void merage(Stack<Opera> operaStack, Opera operaNew, StringBuilder poland) {
        if (operaStack.isEmpty()) {
            operaStack.push(operaNew);
        } else { // 不为空
            if (operaStack.peek().priority >= operaNew.priority) {
                Opera pop = operaStack.pop();
                poland.append(" ");
                poland.append(new String(new char[]{(char) pop.value}));
                merage(operaStack, operaNew, poland);
            } else {
                operaStack.push(operaNew);
            }
        }
    }


    // 将一个逆波兰表达式，依次将数据和运算符放入到arrayList中
    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            list.add(split[i]);
        }
        return list;
    }

    // 计算完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {
        // 创建一个栈
        Stack<String> stack = new Stack<>();
        // 遍历list
        for (String s : list) {
            // 这里使用一个正则表达式取出数
            if (s.matches("\\d+")) { // 匹配的是多位数
                stack.push(s);
            } else {
                // pop 出两个数并运算,再入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int value = 0;
                switch (s) {
                    case "+":
                        value = num2 + num1;
                        break;
                    case "-":
                        value = num2 - num1;
                        break;
                    case "*":
                        value = num2 * num1;
                        break;
                    case "/":
                        value = num2 / num1;
                        break;
                }
                stack.push(String.valueOf(value));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

class OperaManage {
    private int priority = 0; // 优先级

    // 获取优先级
    public int getOperPriority(int ch) {
        if (ch == '+' || ch == '-') {
            return priority;
        }
        if (ch == '*' || ch == '/') {
            return priority + 2;
        }
        return 0;
    }

    // 1 是括号 0 是符号 -1 是数字
    public int operType(int ch) {
        if (ch == '(') {
            priority += 5;
            return 1;
        }
        if (ch == ')') {
            priority -= 5;
            return 1;
        }

        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return 0;
        }
        return -1;
    }
}

class Opera {
    int value;
    int priority;

    public Opera(int value, int priority) {
        this.value = value;
        this.priority = priority;
    }
}
