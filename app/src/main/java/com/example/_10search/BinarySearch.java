package com.example._10search;

import java.util.ArrayList;

// 二分查找
// 注意：使用二分查找的前提是 该数组是有序的
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 15);
        System.out.println(resIndex);
        ArrayList<Integer> integers = binarySearch2(arr, 0, arr.length - 1, 1000);
        if (integers != null) {
            System.out.println(integers);
        }
    }

    // 二分查找算法

    /**
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findValue 要查找的值
     * @return 如果找到返回下标，如果没有找到就返回 -1
     */
    private static int binarySearch(int[] arr, int left, int right, int findValue) {
        // 当left 大于 right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return binarySearch(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return binarySearch(arr, left, mid - 1, findValue);
        } else {
            return mid;
        }
    }

    // 完成一个课后思考题

    /**
     * {1, 8, 10, 89, 1000，1000,1000， 1234} 当一个有序数组中，
     * 有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
     * <p>
     * 思路分析
     * 1.在找到mid 索引值，不要马上返回
     * 2.向mid索引值的左边扫描，将所有满足1000的元素的下标，加入到集合ArrayList
     * 3.向mid 索引值的右边扫描，将所有满足1000的元素的下标，加入到集合ArrayList
     * 4.将ArrayList返回
     */

    private static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
        // 当left 大于 right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        int midValue = arr[mid];
        if (findValue > midValue) {
            return binarySearch2(arr, mid + 1, right, findValue);
        } else if (findValue < midValue) {
            return binarySearch2(arr, left, mid - 1, findValue);
        } else {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {  // 退出
                    break;
                }
                resIndexList.add(temp--);
            }

            temp = mid;
            while (true) {
                if (temp >= arr.length || arr[temp] != findValue) {  // 退出
                    break;
                }
                resIndexList.add(temp++);
            }
            return resIndexList;
        }
    }
}
