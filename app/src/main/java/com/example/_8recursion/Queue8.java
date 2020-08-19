package com.example._8recursion;

// 8皇后问题
public class Queue8 {
    // 定义一个max表示共有多少个皇后
    int max = 8;
    // 定义数组array,保存皇后的放置位置的结果
    int[] array = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
    }

    // 编写一个方法，放置第n个方法
    private void check(int n) {
        if (n == max) { // n = 8
            print();
            count++;
            return;
        }
        // 依次放如皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            // 先把当前皇后 n 放到该行的第i列
            array[n] = i;
            // 判断当放置第n个皇后的i列时，是否冲突
            if (judge(n)) { // 不冲突
                // 接着放第n + 个皇后，开始递归
                check(n + 1);
            }
        }
    }

    // 查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第n个皇后
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            // array[i] == array[n] 判断第n个皇后是否跟前面的皇后在同一列
            // Math.abs(n - i) == Math.abs(array[n] - array[i])判断第n个皇后是否跟前面的皇后在同一斜线
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) { // 说明是在同一列
                return false;
            }
        }
        return true;
    }

    // 写一个方法，可以将皇后摆放的位置打印出来
    private void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }

}
