package com.example._07PolandNotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation1 {
    public static void main(String[] args) {
        // 完成将一个中缀表达式转成后缀表达式的功能
        // 说明
        // 1. (3+1)*2+((2+32)*4)-5 => 3 1 + 2 * 2 32 + 4 * + 5 -
        // 2.因为直接对str进行操作，不方便，因此现将"1+((2+3)*4)-5" => 中缀的表达式对应的list

        String infix = "(3+1)*2+((2+32)*4)-5";
        List<String> stringList = getList(infix);
        System.out.println(stringList);
        String poland = getPoland(stringList);
        System.out.println(poland);
        int value = getValue(poland);
        System.out.println(value);
    }

    // 根据逆波兰表达式求值
    private static int getValue(String poland) {
        Stack<Integer> integerStack = new Stack<>();
        String[] split = poland.split(" ");
        for (String item : split) {
            if (item.matches("\\d++")) {
                integerStack.push(Integer.parseInt(item));
            } else {
                int num1 = integerStack.pop();
                int num2 = integerStack.pop();
                integerStack.push(Operation.opera(num1, num2, item));
            }
        }
        return integerStack.pop();
    }

    // 获取逆波兰表达式
    private static String getPoland(List<String> stringList) {
        StringBuilder polandBuild = new StringBuilder();
        Stack<String> operaStack = new Stack<>();
        for (String item : stringList) {
            if (item.matches("\\d++")) { // 如果是数字
                polandBuild.append(item);
                polandBuild.append(" ");
            } else {
                switch (item) {
                    case "(":  // 如果是(
                        operaStack.push(item);
                        break;
                    case ")":  // 如果是)
                        while (!operaStack.peek().equals("(")) {
                            polandBuild.append(operaStack.pop());
                            polandBuild.append(" ");
                        }
                        operaStack.pop();
                        break;
                    default:  // 如果是符号
                        if (operaStack.isEmpty()) { // 如果栈为空，则直接入栈
                            operaStack.push(item);
                        } else {
                            if (operaStack.peek().equals("(")) { // 如果栈中是(，直接入栈
                                operaStack.push(item);
                            } else {
                                // 如果栈不为空 ，切栈顶的优先级大于等于站内的优先级
                                while (!operaStack.isEmpty() && Operation.getOperationPriority(operaStack.peek()) >= Operation.getOperationPriority(item)) {
                                    polandBuild.append(operaStack.pop());
                                    polandBuild.append(" ");
                                }
                                operaStack.push(item);
                            }
                        }
                        break;
                }
            }
        }
        polandBuild.append(operaStack.pop());
        return polandBuild.toString();
    }

    // 将表达式转成集合
    public static List<String> getList(String infix) {
        List<String> stringList = new ArrayList<>();
        int index = 0;
        while (index < infix.length()) {
            char ch = infix.charAt(index);
            if (ch < 48 || ch > 57) { // 是符号
                stringList.add(String.valueOf(ch));
                index++;
            } else {
                StringBuilder num = new StringBuilder(String.valueOf(ch));
                index++;
                while (index < infix.length() && infix.charAt(index) >= 48 && infix.charAt(index) <= 57) {
                    num.append(infix.charAt(index));
                    index++;
                }
                stringList.add(num.toString());
            }
        }
        return stringList;
    }
}

class Operation {
    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";

    public static int getOperationPriority(String opera) {
        int value = 0;
        switch (opera) {
            case ADD:
                value = 1;
                break;
            case SUB:
                value = 1;
                break;
            case MUL:
                value = 2;
                break;
            case DIV:
                value = 2;
                break;
            default:
                break;
        }
        return value;
    }

    public static int opera(int num1, int num2, String opera) {
        int value = 0;
        switch (opera) {
            case ADD:
                value = num2 + num1;
                break;
            case SUB:
                value = num2 - num1;
                break;
            case MUL:
                value = num2 * num1;
                break;
            case DIV:
                value = num2 / num1;
                break;
            default:
                break;
        }
        return value;
    }
}
