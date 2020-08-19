package com.example._09sort;

// 选择排序
public class _02SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{101, 34, 119, 1};
        print(arr);
        selectSort(arr);
        print(arr);

    }

    // 选择排序
    public static void selectSort(int[] arr) {
        // 设置一个最小的值，和相对的下标
        int minIndex;
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            print(arr);
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + "  ");
        }
        System.out.println();

    }
}
