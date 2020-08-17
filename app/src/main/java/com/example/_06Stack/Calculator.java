package com.example._06Stack;

// 计算器
public class Calculator {
    public static void main(String[] args) {
        String expression = "50+2*6-2*5+7";
        // 创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义需要的相关变量
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        StringBuilder keepNum = new StringBuilder(); // 用于拼接多位数
        char ch = ' '; //将每次扫描到的char保存到ch里面
        // 开始while循环的扫描expression
        for (int i = 0; i < expression.length(); i++) {
            ch = expression.charAt(i);
            if (operStack.isOper(ch)) { // 如果是运算符
                numStack.push(Integer.parseInt(keepNum.toString()));
                keepNum = new StringBuilder();
                operation(numStack, operStack, ch);
            } else { // 如果是数字
                // 当处理多位数是，不能发现是一个数就立即入栈，因为它可能是多位数
                // 在处理数时，需要向expression的表达式的index后再看一位，如果时数继续扫描
                // 因此我们需要定义一个字符串变量，用于拼接
                // 处理多位数
                keepNum.append(ch - 48);
                if (i == expression.length() - 1) {
                    numStack.push(ch - 48);
                }
            }
        }

        // 当表达式扫描完毕，就顺序的从数栈和富豪栈中pop出相应的数字和符号
        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        numStack.show();
    }

    public static void operation(ArrayStack2 numStack, ArrayStack2 operStack, char ch) {
        if (operStack.isEmpty()) { // 如果为空
            operStack.push(ch);
        } else { // 如果不为空
            // 当前优先级小于里面的优先级
            if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                int num1 = numStack.pop();
                int num2 = numStack.pop();
                int oper = operStack.pop();
                int res = numStack.cal(num1, num2, oper);
                numStack.push(res);
                operation(numStack, operStack, ch);
            } else {
                // 当前的优先级大于里面的优先级
                operStack.push(ch);
            }
        }

    }
}

// 先创建一个栈
// 数组模拟栈，需要扩展功能
class ArrayStack2 {
    private int maxSize;// 栈的大小
    private int[] stack; // 数组，数组模拟栈，数据放在该数组中
    private int top = -1; // top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize) {
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

    // 增加一个方法，可以返回当前栈顶的值，但是不是真正的pop
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空");
        }
        return stack[top];
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

    // 返回运算符的优先级,优先级时程序员来确定，优先级使用数字表示
    // 数字越大，优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达时只有+，-，*,/
        }
    }

    // 判断是不是一个运算符
    public boolean isOper(char val) {
        return val == '*' || val == '/' || val == '+' || val == '-';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0; // res 用于存放计算结果
        switch (oper) {
            case '+':
                res = num2 + num1;
                break;
            case '-':
                res = num2 - num1; // 注意顺序
                break;
            case '*':
                res = num2 * num1;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}
