package com.example._10search;

import java.util.Arrays;

// 斐波那契算法
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(arr, 2);
        System.out.println(i);
    }

    // 因为后面我们 mid = low + F(k-1) -1 ，需要使用斐波那契数列，因此我们需要先获取到一个斐波那契数列
    // 非递归方法得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[20];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    // 编写斐波那契查找算法
    // 使用非递归的方式编写算法

    /**
     * @param arr 数组
     * @param key 我们需要查找的关键码
     * @return 返回对应的下标 如果没有则返回 -1
     */
    public static int fibSearch(int[] arr, int key) {
        int low = 0;
        int height = arr.length - 1;
        int k = 0; // 表示斐波那契分割数值的下标
        int mid = 0; // 存放mid值
        int[] fib = fib(); // 获取到斐波那契数列
        // 获取到斐波那契分割数值的下标
        while (height > fib[k] - 1) {
            k++;
        }
        // 因为fib[k]值可能大于arr的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向arr
        // 不足的部分会使用0填充
        int[] temp = Arrays.copyOf(arr, fib[k]);
        // 需要使用arr数组最后的数填充temp
        for (int i = height + 1; i < temp.length; i++) {
            temp[i] = arr[arr.length - 1];
        }

        // 使用while来循环处理，找到我们的数key
        while (low <= height) { // 只要这个条件满足就一直找
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]) { // 我们应该继续向数组的前面查找
                height = mid - 1;
                // 1.全部元素 = 前面的元素 + 后面的元素
                // 2. f[k] = f[k-1] + f[k-2]
                // 因为前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                // 即 在f[k-1]的前面继续查找k--
                // 即下次循环 mid = f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                // 1.全部元素 = 前面的元素 + 后面的元素
                // 2. f[k] = f[k-1] + f[k-2]
                // 因为前面有f[k-2]个元素，所以可以继续拆分 f[k-2] = f[k-3] + f[k-4]
                // 即 在f[k-2]的前面继续查找k-=2
                // 即下次循环 mid = f[k-1-2]-1
                k -= 2;
            } else {
                if (mid <= height) {
                    return mid;
                } else {
                    return height;
                }
            }
        }
        return -1;
    }
}
