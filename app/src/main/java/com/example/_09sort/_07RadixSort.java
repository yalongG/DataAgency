package com.example._09sort;

import com.example.PrintUtil;

// 基数排序
public class _07RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        PrintUtil.print(arr);
        radixSort(arr);
        PrintUtil.print(arr);
    }

    private static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];
        for (int k = 0, n = 1; k < String.valueOf(max).length(); k++, n *= 10) {
            for (int i = 0; i < arr.length; i++) {
                int digitOfElement = arr[i] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;
            for (int i = 0; i < bucketElementCounts.length; i++) {
                if (bucketElementCounts[i] != 0) {
                    for (int j = 0; j < bucketElementCounts[i]; j++) {
                        arr[index++] = bucket[i][j];
                    }
                    bucketElementCounts[i] = 0;
                }
            }
        }


//        for (int k = 0; k < String.valueOf(max).length(); k++) {
//            // 第一轮排序(针对每个元素的个位进行排序处理)
//            // 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
//
//            // 说明
//            // 1.二维数组包含10个一维数组
//            // 2.为了防止在放入数的时候，数据溢出，则每个一维数据(桶)，大小定位arr.length
//            // 3.基数排序是使用空间换时间的经典算法
//            int[][] bucket = new int[10][arr.length];
//
//            // 为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录每个桶的每次放入的数据个数
//            int[] bucketElementCounts = new int[10];
//            for (int i = 0; i < arr.length; i++) {
//                // 取出每个元素的个位数
//                int digitOfElement = (arr[i] / (int) Math.pow(10, k)) % 10;
//                // 放入到对应的桶中
//                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
//                bucketElementCounts[digitOfElement]++;
//            }
//            // 按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
//            int index = 0;
//            // 遍历每一桶，并将桶中的数据，放入原数组中
//            for (int i = 0; i < bucketElementCounts.length; i++) {
//                // 如果桶中有数据,才放入到原数组
//                if (bucketElementCounts[i] != 0) {
//                    // 循环该桶,即第i个桶
//                    for (int j = 0; j < bucketElementCounts[i]; j++) {
//                        // 取出元素，放入到arr中
//                        arr[index++] = bucket[i][j];
//                    }
//                    bucketElementCounts[i] = 0;
//                }
//            }
//        }
    }
}
