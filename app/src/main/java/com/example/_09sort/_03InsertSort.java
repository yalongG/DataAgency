package com.example._09sort;

import com.example.PrintUtil;

// 插入排序
public class _03InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[]{101, 34, 119, 1};
        PrintUtil.print(arr);
        insertSort(arr);
        PrintUtil.print(arr);
    }

    // 插入排序
    private static void insertSort(int[] arr) {
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出while循环是，说明插入位置找到，insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }
}
