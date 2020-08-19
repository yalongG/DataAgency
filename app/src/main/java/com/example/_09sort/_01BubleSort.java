package com.example._09sort;

// 冒泡排序
public class _01BubleSort {
    public static void main(String[] args) {
        int arr[] = {7, 5, 4, 3, 1, 2, 6};
//        sort(arr);
        sort1(arr);
//        print(arr);
    }

    // 添加flag优化排序
    private static void sort1(int[] arr) {
        boolean flag = false;
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            print(arr);
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }

    // 普通的排序
    public static void sort(int[] arr) {
        // 排序把大的数据放到最后
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            print(arr);
        }
    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
