package com.example._17algorithm;

import java.util.Arrays;

// 弗洛伊德算法
public class _09Floyd {
    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535; // 表示不可链接
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};
        Graph1 graph1 = new Graph1(vertex.length, matrix, vertex);
        graph1.floyd();
        graph1.show();
    }
}

// 创建图
class Graph1 {
    private char[] vertex; // 存放定点的数组
    private int[][] dis; // 保存，从各个顶点出发到其它定点的距离，最后的结果，也是保留在该数组
    private int[][] pre; // 保存到达目标顶点的前驱顶点

    // 构造器
    // length 大小
    // matrix 邻接矩阵
    // vertex 顶点数组
    public Graph1(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];

        // 对 pre 数组初始化,存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示 pre 数组和 dis 数组
    public void show() {
        for (int[] aPre : pre) {
            System.out.println(Arrays.toString(aPre));
        }
        System.out.println("---------------------");
        for (int[] di : dis) {
            System.out.println(Arrays.toString(di));

        }
    }

    // 弗洛伊德算法
    public void floyd() {
        int len = 0; // 变量保存距离
        // 对中间顶点的遍历，k 就是中间顶点的下标 ['A','B','C','D','E','F','G']
        for (int k = 0; k < dis.length; k++) {
            // 从 i 顶点开始出发['A','B','C','D','E','F','G']
            for (int i = 0; i < dis.length; i++) {
                // 到达 j 顶点 ['A','B','C','D','E','F','G']
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j]; // => 求出从 i 顶点出发，经过 k 中间顶点，到达 j 顶点距离
                    if (len < dis[i][j]) { // 如果 len 小于 dis[i][j]
                        dis[i][j] = len; // 更新距离
                        pre[i][j] = pre[k][j]; // 更新前驱顶点
                    }
                }
            }
        }
    }
}
