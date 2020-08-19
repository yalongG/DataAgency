package com.example._08recursion;

// 迷宫
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用 1表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        map[6][4] = 1;
        map[5][4] = 1;

        System.out.println(setWay(map, 1, 1));

        print(map);
    }

    // 输出地图
    private static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }

    // 使用递归回溯来给小球找路
    // 说明
    // 1.map表示地图
    // 2.i，j表示从地图的那个位置出发
    // 3.如果小球能到map[6][5]则说明通路找到
    // 4.约定：当map[i][j]为0表示改点没有走过，1表示墙 2表示通路可以走 3表示该位置已经走过，但是走不通
    // 5.在走迷宫时，需要确定一个策略(方法) 下->右->上->左 如果改点走不通，再回溯

    /**
     * 使用递归回溯来给小球找路
     *
     * @param map 地图
     * @param i   从那个位置开始找
     * @param j   从那个位置开始找
     * @return 如果找到通路返回真，否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        print(map);
        System.out.println("------------------------");

        if (map[6][5] == 2) { // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) { // 如果当前这个点还没有走过
                // 按照策略走
                // 下->右->上->左
                map[i][j] = 2; // 假定改点是可以走通
                if (setWay(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左走
                    return true;
                } else {
                    // 说明改点是走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else { // 如果map[i][j] != 0，可能是1，2，3
                return false;
            }
        }
    }
}
