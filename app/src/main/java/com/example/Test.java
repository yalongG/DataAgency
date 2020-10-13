package com.example;

public class Test {
    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 0, 1, 3, 5, 7, 9};
        PrintUtil.print(arr);
//        sort1(arr);
//        sort2(arr);
//        sort3(arr);
//        sort4(arr);
//        sort5(arr, 0, arr.length - 1);
//        int[] temp = new int[arr.length];
//        sort6(arr, temp, 0, arr.length - 1);
//        sort7(arr);
        sort8(arr);
        PrintUtil.print(arr);
    }

    // 堆排序
    private static void sort8(int[] arr) {
        int temp;
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapSort(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            heapSort(arr, 0, j);
        }

    }

    private static void heapSort(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    // 基数排序
    private static void sort7(int[] arr) {
        int bucket = 8;
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int temp[][] = new int[bucket][arr.length];
        int tempIndex[] = new int[bucket];
        int t = 0;
        for (int i = 0, step = 1; i < String.valueOf(max).length(); i++, step *= bucket) {
            for (int j = 0; j < arr.length; j++) {
                int index = arr[j] / step % bucket;
                temp[index][tempIndex[index]++] = arr[j];
            }
            t = 0;
            for (int j = 0; j < tempIndex.length; j++) {
                if (tempIndex[j] != 0) {
                    for (int k = 0; k < tempIndex[j]; k++) {
                        arr[t++] = temp[j][k];
                    }
                    tempIndex[j] = 0;
                }
            }
        }
    }

    // 归并
    private static void sort6(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort6(arr, temp, left, mid);
            sort6(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        int tempLeft = left;
        t = 0;
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }

    // 快排
    private static void sort5(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int midValue = arr[l + (r - l) / 2];
        int temp;
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
            temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;

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
        if (r > left) {
            sort5(arr, left, r);
        }
    }

    // 希尔
    private static void sort4(int[] arr) {
        int insertIndex;
        int insertValue;
        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = step; i < arr.length; i++) {
                insertIndex = i;
                insertValue = arr[i];
                while (insertIndex - step >= 0 && arr[insertIndex - step] > insertValue) {
                    arr[insertIndex] = arr[insertIndex - step];
                    insertIndex -= step;
                }
                if (insertIndex != i) {
                    arr[insertIndex] = insertValue;
                }
            }
        }
//        int temp;
//        for (int step = arr.length / 2; step > 0; step /= 2) {
//            for (int i = step; i < arr.length; i++) {
//                for (int j = i - step; j >= 0; j -= step) {
//                    if (arr[j + step] < arr[j]) {
//                        temp = arr[j];
//                        arr[j] = arr[j + step];
//                        arr[j + step] = temp;
//                    }
//                }
//            }
//        }
    }

    // 插入
    private static void sort3(int[] arr) {
        int insertIndex;
        int insertValue;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i - 1;
            insertValue = arr[i];
            while (insertIndex >= 0 && arr[insertIndex] > insertValue) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
        }
    }

    // 选择
    private static void sort2(int[] arr) {
        int minIndex;
        int minValue;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            minValue = arr[minIndex];
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

    // 冒泡
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
                break;
            } else {
                flag = false;
            }
        }
    }
}
