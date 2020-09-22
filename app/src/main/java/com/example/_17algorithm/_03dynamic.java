package com.example._17algorithm;

// 动态规划
public class _03dynamic {
    public static void main(String[] args) {
        // KnapsackProblem 背包问题
        int[] w = {1, 4, 3}; // 物品的总量
        int[] val = {1500, 3000, 2000}; // 物品的价值
        int m = 4; // 背包的容量
        int n = val.length; // 物品的个数

        // 为了记录放商品的情况，
        // 创建一个二维数组
        // 行 表示容量
        // 列 表示物品
        int[][] v = new int[n + 1][m + 1];

        // 为了记录放入商品的情况，我们记录一个二维数组
        int[][] path = new int[n + 1][m + 1];

        // 初始化第一行和第一列，在本程序中，可以不去处理，因为默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; // 将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; // 将第一行设置为0
        }

        // 根据前面得到的公式来动态规划处理
        for (int i = 1; i < v.length; i++) { // 不处理第一行
            for (int j = 1; j < v[i].length; j++) { // 不处理第一列
                // 公式
                if (w[i - 1] > j) { // 因为我们程序i是从1开始的，因此原来的公式中w[i] 修改成w[i-1]
                    v[i][j] = v[i - 1][j];
                } else {
                    // 说明：
                    // 因为我们的i从1开始，因此公式需要调整
//                    v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
                    // 为了记录商品存放到背包的情况，我们不能简单的使用上面的公式，需要使用if-else来处理
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        // 把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }


        // 输出一下v看一下
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + "  ");
            }
            System.out.println();
        }

        // 输出最后我们放入的哪些商品
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + "  ");
            }
            System.out.println();
        }

        int i = path.length - 1; // 行的最大下标
        int j = path[0].length - 1; // 列的最大下标
        while (i > 0 && j > 0) { // 从path的最后开始找
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入到背包");
                j -= w[i - 1];
            }
            i--;
        }
    }
}
