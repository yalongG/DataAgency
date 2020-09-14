package com.example._17algorithm;


import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

// 马踏棋盘 算法
public class _10horse {
    private static int X; // 棋盘的列
    private static int Y; // 棋盘的行
    // 创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    // 使用一个属性，标记是否棋盘的所有位置都被访问过
    private static boolean finished; // 如果为 true ，表示成功

    public static void main(String[] args) {
        X = 8;
        Y = 8;

        int row = 1; // 马初始位置的行，从1开始编号
        int column = 1; // 马初始位置的列，从1开始编号
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y]; // 初始值都是false
        // 测试一下耗时
        long time = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        System.out.println(System.currentTimeMillis() - time);

        for (int[] rows : chessboard) {
            System.out.println(Arrays.toString(rows));
        }
    }

    // 完成马踏棋盘问题的算法
    // chessboard 棋盘
    // row 马当前位置的行 从 0 开始
    // column 马当前位置的列，从 0 开始
    // step 是第几步，初始位置即使第一步
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true; // 标记该位置已经访问
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        // 对ps进行排序，排序的规则就是对 ps 的所有的 Point 对象的下一步的位置的数目，进行非递减排序
        sort(ps);
        // 遍历
        while (!ps.isEmpty()) {
            Point p = ps.remove(0); // 取出下一个可以走的位置
            // 判断改点是否已经访问过了
            if (!visited[p.y * X + p.x]) { // 说明还没有访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }

        // 判断马是否完成了任务，使用step和应该走的步数比较
        // 如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        // 说明： step < X * Y 成立的情况有两种
        // 1.棋盘到目前为止，仍然没有走完
        // 2.棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    // 根据当前的位置，计算马还能走哪些位置(Point)，并放入到一个集合中(ArrayList)，最多有8个位置
    public static ArrayList<Point> next(Point curPoint) {
        // 创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        // 创建一个 Point
        Point p1 = new Point();
        // 表示 马 可以走 5 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 6
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 7
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 0
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 1
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 2
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 3
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        // 4
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1.x, p1.y));
        }
        return ps;
    }

    // 根据当前这一步的所有的下一步的选择位置，进行非递减排序,减少回溯的次数
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取到 o1的下一步的所有位置的个数
                int count1 = next(o1).size();
                // 获取到 o2的下一步的所有位置的个数
                int count2 = next(o2).size();
                if (count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }
}

class Point {
    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}
