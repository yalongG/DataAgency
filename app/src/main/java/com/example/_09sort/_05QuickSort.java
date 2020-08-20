package com.example._09sort;

import com.example.PrintUtil;

// 快速排序
public class _05QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        PrintUtil.print(arr);
        quickSort(arr, 0, arr.length - 1);
        PrintUtil.print(arr);
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left; //左下表
        int r = right; // 又下标
        // pivot 中轴值
        int pivot = arr[(l + r) / 2];
        int temp; // 临时变量，作为交换时使用
        // while 循环的目的是让比 pivot值小的放到左边
        // 比 pivot值大放到右边
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot) {
                r--;
            }
            // 如果l >= r成立，说明pivot的左右两的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot的值
            if (l >= r) {
                break;
            }
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后，发现这个arr[l] == pivot的值 --,前移
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现这个arr[r] == pivot的值 ++,后移
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果l == r，必须l++,r--，否则为出险栈溢出
        if (l == r) {
            l++;
            r--;
        }

        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }

        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }

    }
}
