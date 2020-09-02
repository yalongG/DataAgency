package com.example._16graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 图
public class Graph {
    private List<String> vertexList; // 存储顶点集合
    private int[][] edges; // 存储图的对应的邻结矩阵
    private int numOfEdges; // 表示边的数目
    // 定义一个boolean[]，记录某个结点是否被访问过
    private boolean[] isVisited;

    public static void main(String[] args) {
        int n = 8; // 结点的个数
        Graph graph = new Graph(n);
//        String[] vertexValues = {"A", "B", "C", "D", "E"};
        String[] vertexValues = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (String vertexValue : vertexValues) {
            graph.insertVertex(vertexValue);
        }

        // 添加边
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();
        graph.dfs();
//        graph.bfs();

    }

    // 构造器
    public Graph(int n) {
        // 初始化矩阵和  vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 得到第一个邻接结点的下标 w
     *
     * @param index index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    // 深度优先遍历算法
    // i 第一次就是 0
    private void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + " -> ");
        // 将结点设置为已经访问
        isVisited[i] = true;

        // 查找结点i的第一个邻接结点 w
        int w = getFirstNeighbor(i);
        while (w != -1) { // 说明有
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    // 对dfs 进行一个重载，遍历我们所有的结点，并进行dfs
    public void dfs() {
        // 遍历所有的结点进行dfs[]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    // 对一个结点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u; // 表示队列的头结点对应下标
        int w; // 邻接结点w
        // 队列，结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<Integer>();
        // 访问结点，输出结点的信息
        System.out.print(getValueByIndex(i) + " -> ");
        // 标记为已访问
        isVisited[i] = true;
        // 将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            // 取出队列的头结点下标
            u = queue.removeFirst();
            // 得到第一个临界点的下标
            w = getFirstNeighbor(u);
            while (w != -1) { // 找到
                if (!isVisited[w]) {
                    // 访问结点，输出结点的信息
                    System.out.print(getValueByIndex(w) + " -> ");
                    // 标记为已访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 以 u 为前驱点，找 w 后面的下一个结点
                w = getNextNeighbor(u, w);
            }
        }
    }

    // 遍历所有结点进行广度优先重载
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
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
