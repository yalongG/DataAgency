package com.example._10search;

// 顺序查找
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 9, 11, -1, 34, 89};
        int index = seqSearch(arr, 11);
        System.out.println(index == -1 ? "没有找到" : "找到了index=" + index);
    }

    // 顺序查找
    private static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
