package com.example._09sort;

import com.example.PrintUtil;

// 排序测试
public class SortTest {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 1, 3, 5, 7, 9};
        PrintUtil.print(arr);
//        sort1(arr);
//        sort2(arr);
//        sort3(arr);
//        sort4(arr);
//        sort5(arr, 0, arr.length - 1);
//        int[] temp = new int[arr.length];
//        sort6(arr, 0, arr.length - 1, temp);
        sort7(arr);
        PrintUtil.print(arr);
    }

    // 排序七 基数排序
    private static void sort7(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[][] temp = new int[10][arr.length];
        int[] tempIndex = new int[10];

        for (int i = 0, n = 1; i < String.valueOf(max).length(); i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int index = (arr[j] / n) % 10;
                temp[index][tempIndex[index]] = arr[j];
                tempIndex[index]++;
            }

            int t = 0;
            for (int j = 0; j < tempIndex.length; j++) {
                if (tempIndex[j] != 0) {
                    for (int k = 0; k < tempIndex[j]; k++) {
                        arr[t] = temp[j][k];
                        t++;
                    }
                    tempIndex[j] = 0;
                }
            }
        }
    }

    // 排序六 归并排序
    private static void sort6(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort6(arr, left, mid, temp);
            sort6(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                temp[t] = arr[j];
                t++;
                j++;
            } else {
                temp[t] = arr[i];
                t++;
                i++;
            }
        }
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        int tempLeft = left;
        t = 0;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }

    // 排序五 快速排序
    private static void sort5(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int temp;
        int midValue = arr[(l + r) / 2];
        while (l < r) {
            while (arr[l] < midValue) {
                l++;
            }
            while (arr[r] > midValue) {
                r--;
            }
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == midValue) {
                r--;
            }
            if (arr[r] == midValue) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (l < right) {
            sort5(arr, l, right);
        }
        if (left < r) {
            sort5(arr, left, r);
        }

    }

    // 排序四 希尔排序
    private static void sort4(int[] arr) {
        int insertIndex;
        int insertValue;
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                insertIndex = i;
                insertValue = arr[i];
                if (arr[insertIndex - step] > insertValue) {
                    while (insertIndex - step >= 0 && arr[insertIndex - step] > insertValue) {
                        arr[insertIndex] = arr[insertIndex - step];
                        insertIndex -= step;
                    }
                    if (insertIndex != i) {
                        arr[insertIndex] = insertValue;
                    }
                }
            }
        }
//        int temp;
//        for (int step = arr.length / 2; step > 0; step /= 2) {
//            for (int i = step; i < arr.length; i++) {
//                for (int j = i - step; j >= 0; j -= step) {
//                    if (arr[j] > arr[j + step]) {
//                        temp = arr[j + step];
//                        arr[j + step] = arr[j];
//                        arr[j] = temp;
//                    }
//                }
//            }
//        }
    }

    // 排序三 插入排序
    private static void sort3(int[] arr) {
        int insertIndex;
        int insertValue;
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
        }
    }

    // 排序二 选择排序
    private static void sort2(int[] arr) {
        int minValue;
        int minIndex;
        for (int i = 0; i < arr.length - 1; i++) {
            minValue = arr[i];
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < minValue) {
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }
    }


    // 排序一 冒泡排序
    private static void sort1(int[] arr) {
        int temp;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                return;
            } else {
                flag = false;
            }
        }
    }
}
