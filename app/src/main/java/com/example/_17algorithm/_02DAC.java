package com.example._17algorithm;

// 分治算法
public class _02DAC {
    public static void main(String[] args) {
        HanoiTower hanoitower = new HanoiTower();
        hanoitower.hanoiTower(5, 'A', 'B', 'C');
    }
}

// 汉诺塔
class HanoiTower {

    // 汉诺塔的移动方法
    // 使用分治算法
    public void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第" + num + "个盘从" + a + "->" + c);
        } else {
            // 如果我们有n >= 2情况，我们总是可以看做是两个盘1.最下边的一个盘  2.上面的所有盘
            // 1. 先把子上main的所有盘a->b,i懂过程会使用到c
            hanoiTower(num - 1, a, c, b);
            // 2.把最下边的盘 a->c
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            // 3.把B塔的所有盘从b->c,移动过程使用到a 塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
