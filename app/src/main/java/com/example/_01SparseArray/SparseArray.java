package com.example._01SparseArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 稀疏数组
public class SparseArray {
    public static void main(String[] args) {
//        arrToSparse();
        arrToSparseFile();
        sparseFileToArr();
    }

    // 稀疏数组文件转成数组
    private static void sparseFileToArr() {
        File file = new File("sparse.text");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            String[] split = new String(bytes).split(" ");
            int[][] sparseArr = new int[split.length / 3][3];
            for (int i = 0; i < split.length; i++) {
                sparseArr[i / 3][i % 3] = Integer.parseInt(split[i]);

            }
            // 打印稀疏数组
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    System.out.print(sparseArr[i][j] + " ");
                }
                System.out.println();
            }
            int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
            for (int i = 1; i < sparseArr.length; i++) {
                chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
            }
            for (int i = 0; i < chessArr.length; i++) {
                for (int j = 0; j < chessArr[i].length; j++) {
                    System.out.print(chessArr[i][j] + " ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将数组转成稀疏数组文件
    private static void arrToSparseFile() {
        // 创建一个原始的二位数组 11 * 11
        // 0 表示没有棋子 1表示黑子 2表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[3][5] = 2;
        // 输出二维数组
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                System.out.print(chessArr1[i][j] + " ");
            }
            System.out.println();
        }

        int count = 0; // 稀疏数组的长度
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }

        int[][] sparseArr = new int[count + 1][3];
        sparseArr[0][0] = 11; // 列
        sparseArr[0][1] = 11; // 行
        sparseArr[0][2] = count;

        int number = 0; // 下标起记录作用
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    number++;
                    sparseArr[number][0] = i;
                    sparseArr[number][1] = j;
                    sparseArr[number][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println("打印稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.print(sparseArr[i][j] + " ");
            }
            System.out.println();
        }

        File file = new File("sparse.text");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            for (int i = 0; i < sparseArr.length; i++) {
                for (int j = 0; j < sparseArr[i].length; j++) {
                    outputStream.write(String.valueOf(sparseArr[i][j] + " ").getBytes());
                }
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将数组转化成稀疏数组 并且稀疏数组转成数组
    private static void arrToSparse() {
        // 创建一个原始的二位数组 11 * 11
        // 0 表示没有棋子 1表示黑子 2表示白子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][4] = 2;
        chessArr1[3][5] = 2;
        // 输出二维数组
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                System.out.print(chessArr1[i][j] + " ");
            }
            System.out.println();
        }

        int count = 0; // 稀疏数组的长度
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                }
            }
        }

        int[][] sparseArr = new int[count + 1][3];
        sparseArr[0][0] = 11; // 列
        sparseArr[0][1] = 11; // 行
        sparseArr[0][2] = count;

        int number = 0; // 下标起记录作用
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    number++;
                    sparseArr[number][0] = i;
                    sparseArr[number][1] = j;
                    sparseArr[number][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println("打印稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.print(sparseArr[i][j] + " ");
            }
            System.out.println();
        }

        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 打印生成的二维素组
        for (int i = 0; i < chessArr2.length; i++) {
            for (int j = 0; j < chessArr2[i].length; j++) {
                System.out.print(chessArr2[i][j] + " ");
            }
            System.out.println();
        }
    }
}
