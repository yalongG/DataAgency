package com.example._17algorithm;

import java.util.Arrays;

// 克鲁斯卡算法
public class _07Kruskal {
    private int edgeNum; // 边的个数
    private char[] vertexs; // 顶点数组集合
    private int[][] matrix; // 邻接矩阵
    // 使用 INF 表示两个顶点不能联通
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0},
        };

        _07Kruskal kruskal = new _07Kruskal(vertexs, matrix);
//        kruskal.print();
//        System.out.println(Arrays.toString(kruskal.getEdges()));
//        kruskal.sortEdges(kruskal.getEdges());
//        System.out.println(Arrays.toString(kruskal.getEdges()));
        kruskal.kruskal();
    }

    // 构造器
    public _07Kruskal(char[] vertexs, int[][] matrix) {
        // 初始化顶点数 和边的个数
        int vlen = vertexs.length;
        //初始化顶点
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        // 初始化边
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边
        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    // 打印邻接矩阵
    private void print() {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    // 对边进行排序处理，冒泡
    private void sortEdges(EData[] edges) {
        EData eData;
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    eData = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = eData;
                }
            }
        }
    }

    /**
     * @param ch 顶点的值
     * @return 返回ch顶点对应的下标，如果找不到返回 -1
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    // 获取图中的边，放到EData[] 数组中，后面我们需要遍历该数组
    // 是通过matrix 邻接矩阵来获取
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    // 获取下标为 i 的顶点的终点，用于后面判断两个顶点的终点是否相同
    // ends 数组就是记录了各个顶点对应的终点是哪个，ends 数组是在遍历过程中，逐步形成的
    // i 表示传入的顶点对应的下标
    // 返回的就是 下标为 i 的这个顶点对应的终点的下标
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private void kruskal() {
        int index = 0; // 表示最后结果数组的索引
        int[] ends = new int[edgeNum]; // 用于保存 "已有最小生成树" 中的每个顶点在最小生成树中的终点
        // 创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        // 获取图中所有的边的集合，一共有12条边
        EData[] edges = getEdges();
        sortEdges(edges);
//        System.out.println(edges);

        // 遍历 edges 数组，将边添加到最小生成树中时，判断是准备加入的边是否形成回来，如果没有，就加入rets，否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 获取到第 i 条边的第一个顶点(起点)
            int p1 = getPosition(edges[i].start);
            // 获取到第 i 条边的第二个顶点(终点)
            int p2 = getPosition(edges[i].end);

            // 获取 p1 这个顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);

            // 获取 p2 这个顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);
            // 是否构成回路
            if (m != n) { // 没有构成回路
                ends[m] = n; // 设置 m 在"已有最小生成树"中的终点
                rets[index++] = edges[i]; // 有一条边加入到rest 数组
            }
        }
        // 统计并打印"最小生成树"，输出 rets
        System.out.println(Arrays.toString(rets));

    }
}

// 创建一个类 EData,它的对象实例就表示一条边
class EData {
    char start; // 边的一个点
    char end; // 边的另一个点
    int weight; // 边的权值

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

