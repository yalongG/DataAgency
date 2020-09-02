package com.example._16graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 图
public class Graph {
    private List<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图的对应的邻结矩阵
    private int numOfEdges; // 表示边的数目

    public static void main(String[] args) {
        int n = 5; // 结点的个数
        Graph graph = new Graph(n);
        String[] vertexValues = {"A", "B", "C", "D", "E"};
        for (String vertexValue : vertexValues) {
            graph.insertVertex(vertexValue);
        }

        // 添加边
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

    }

    // 构造器
    public Graph(int n) {
        // 初始化矩阵和  vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    // 图中常用的方法
    // 返回结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    // 得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    // 返回结点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    // 返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    // 添加边

    /**
     * @param v1     点的下标，即是第几个顶点
     * @param v2     第二个顶点的对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
