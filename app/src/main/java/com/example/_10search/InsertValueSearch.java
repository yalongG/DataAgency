package com.example._10search;

// 差值查找
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        //
        int i = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println(i);
    }

    // 编写差值查找算法 要求数组有序

    /**
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 查找的值
     * @return 如果找到，返回对应的小表，如果没有找到，返回-1
     */
    private static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            return -1;
        }
        int mid = left + (findValue - arr[left]) * (right - left) / (arr[right] - arr[left]);
        int midValue = arr[mid];
        if (findValue > midValue) {
            return insertValueSearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return insertValueSearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }
}
