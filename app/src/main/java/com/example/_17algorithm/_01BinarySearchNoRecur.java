package com.example._17algorithm;

// 二分法查找 非递归
public class _01BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        System.out.println(binarySerach(arr, 100));

    }

    // 二分查找非递归实现
    // arr 需要查找的数组, arr 是升序排序
    // target 需要查找的数
    // 返回对应的下标，-1表示没有找到
    private static int binarySerach(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right) { // 说明继续查找
            mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
