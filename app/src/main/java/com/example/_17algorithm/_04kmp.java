package com.example._17algorithm;

import java.util.Arrays;

// kmp算法
public class _04kmp {
    public static void main(String[] args) {
//        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
//        String str2 = "尚硅谷你尚硅你";
//        long time = System.currentTimeMillis();
//        System.out.println(violenceMatch(str1, str2));
//        System.out.println(System.currentTimeMillis() - time);
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(Arrays.toString(kmpNext(str2)));
        System.out.println(kmpSearch(str1, str2, kmpNext(str2)));
    }

    /**
     * 写出我们的kmp搜索算法
     *
     * @param str1 原字符串
     * @param str2 子串
     * @param next 部分匹配表,字串对应的部分匹配表
     * @return 如果是-1没有匹配到，否则返回第一个匹配的位置
     */
    private static int kmpSearch(String str1, String str2, int[] next) {
        // 遍历 str1
        for (int i = 0, j = 0; i < str1.length(); i++) {
            // 需要处理 str1.charAt(i) != str2.charAt(j)
            // KMP 算法的额核心点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    // 获取到一个字符串(子串)的部分匹配值
    private static int[] kmpNext(String dest) {
        // 创建一个next 数组，保存部分匹配值
//        ABCDABD
        int[] next = new int[dest.length()];
        next[0] = 0; // 如果字符串长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            // 当不相等的时候
            // KMP算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            // 当这个条件满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }


    // 暴力匹配算法实现
    private static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Length = s1.length;
        int s2Length = s2.length;

        int i = 0; // i索引指向s1
        int j = 0; // j索引指向s2
        while (i < s1Length && j < s2Length) { // 保证匹配时不越界
            if (s1[i] == s2[j]) { // 匹配成功
                i++;
                j++;
            } else { // 没有匹配成功
                i = i - (j - 1);
                j = 0;
            }
        }
        // 判断是否匹配成功
        if (j == s2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
