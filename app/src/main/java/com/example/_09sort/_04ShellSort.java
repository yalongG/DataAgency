package com.example._09sort;

import com.example.PrintUtil;

// 希尔排序
public class _04ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        PrintUtil.print(arr);
        shellSort(arr);
        PrintUtil.print(arr);
    }

    // 希尔排序
    private static void shellSort(int[] arr) {
        int min;
        int minIndex;
        // 移位法
        for (int step = arr.length / 2; step > 0; step /= 2) {
            // 从 stop个元素，逐个对所在组进行插入操作
            for (int i = step; i < arr.length; i++) {
                minIndex = i;
                min = arr[minIndex];
                if (arr[minIndex] < arr[minIndex - step]) {
                    while (minIndex - step >= 0 && min < arr[minIndex - step]) {
                        arr[minIndex] = arr[minIndex - step];
                        minIndex -= step;
                    }
                    if (minIndex != i) {
                        arr[minIndex] = min;
                    }
                }
            }
        }
//        int temp;
//        // 交换法
//        for (int step = arr.length / 2; step > 0; step /= 2) {
//            for (int i = step; i < arr.length; i++) {
//                for (int j = i - step; j >= 0; j -= step) {
//                    if (arr[j] > arr[j + step]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + step];
//                        arr[j + step] = temp;
//                    }
//                }
//            }
//        }

//        int temp;
//        int step = arr.length / 2;
//        while (step > 0) {
//            // 交换法
//            for (int i = step; i < arr.length; i++) {
//                for (int j = i - step; j >= 0; j -= step) {
//                    if (arr[j] > arr[j + step]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + step];
//                        arr[j + step] = temp;
//                    }
//                    PrintUtil.print(arr);
//                }
//            }
//            step /= 2;
//        }

//        // 第一轮排序
//        // 因为第一轮排序，是将10个数据分成了5组
//        int temp;
//        for (int i = 5; i < arr.length; i++) {
//            // 遍历各组中所有的元素(共5组，每组有2个元素)，步长5
//            for (int j = i - 5; j >= 0; j -= 5) {
//                // 如果当前元素大于加上步长后的那个元素，说明需要交换
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        PrintUtil.print(arr);
//
//        for (int i = 2; i < arr.length; i++) {
//            for (int j = i - 2; j >= 0; j -= 2) {
//                // 如果当前元素大于加上步长后的那个元素，说明需要交换
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        PrintUtil.print(arr);
//
//        for (int i = 1; i < arr.length; i++) {
//            for (int j = i - 1; j >= 0; j -= 1) {
//                // 如果当前元素大于加上步长后的那个元素，说明需要交换
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        PrintUtil.print(arr);
    }
}
