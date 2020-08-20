package com.example._09sort;

import com.example.PrintUtil;

// 归并排序
public class _06MergetSort {
    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        PrintUtil.print(arr);
        mergSort(arr, 0, arr.length - 1, temp);
        PrintUtil.print(arr);

    }

    // 分+合的方法
    public static void mergSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; // 中间的索引
            // 向左递归进行分
            mergSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergSort(arr, mid + 1, right, temp);
            // 到合并时
            merge(arr, left, mid, right, temp);
        }
    }

    // 合并的方法

    /**
     * 合并方法
     *
     * @param arr   待排序的数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边的索引
     * @param temp  中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i，左边有序序列的初始索引
        int j = mid + 1; // 初始化j，右边有序序列的初始索引
        int t = 0; // t指向temp数组的当前索引
        // 先把左右两边的数据(有序)按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完位置
        while (i <= mid && j <= right) { // 继续
            if (arr[i] <= arr[j]) { // 如果左边的有序序列的当前元素，小于等于右边的有序的当前元素
                // 即将左边的元素，拷贝到temp数组
                // 然后t++，i++
                temp[t] = arr[i];
                t++;
                i++;
            } else {// 反之将右边的元素，拷贝到temp数组
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 将有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) { // 说明左边的有序序列还有剩余的元素，全部填充到temp中
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) { // 说明右的有序序列还有剩余的元素，全部填充到temp中
            temp[t] = arr[j];
            t++;
            j++;
        }

        // 将temp数组的元素拷贝到arr
        // 注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        System.out.println("tempLeft=" + tempLeft + ",right=" + right);
        PrintUtil.print(temp);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
//        PrintUtil.print(arr);
    }
}
