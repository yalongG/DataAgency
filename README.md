# DataAgency
数据结构

##### _01SparseArray  稀疏数组

如何将二维数组转成稀疏数组(涉及到数据的压缩)，以及稀疏数组转成数组。以及文件的暂存等。

```java
          arrToSparse(); // 将数组转化成稀疏数组 并且稀疏数组转成数组
//        arrToSparseFile();  // 将数组转成稀疏数组文件
//        sparseFileToArr(); // 稀疏数组文件转成数组
```

##### _02Queue 队列

ArrayQueueDemo数组模拟队列 ArrayQueue

CircleQueueDemo数组模拟环形队列CircleQueue

##### _03linkedlist 单链表

单链表 SingleLinkedList

普通添加add()，根据下标添加addByOrder(),更新update(),删除delete(),显示打印show()
getLength()获取链表的长度
findLastIndexNode()查找单链表的倒数第k个节点
reverse()代码反转
print()逆序打印

单链表测试 LinkListDemo

```java
getNodeList(); //获得单链表
getLastNode(hear,3);//获取单链表倒数第k个元素
reverse(hear);// 链表反转
printReverse(hear); // 在不改变单链表顺序的情况下，反向打印单链表
merge(nodeList1, nodeList2); // 两个有序单链表进行合并，合并成一个有序链表
```

##### _04DoubleLinedList 双向链表

DoubleLinkListDemo双向链表测试 

```java
show();  // 遍历双向列表的方法
add(HeroNode node);     // 添加数据
addByOrder(HeroNode node);   // 根据id有序添加
update(HeroNode node);  // 更新链表数据
delete(int no);  // 删除链表数据
```

##### _05Josepfu 环形链表的约瑟夫问题

Josepfu 约瑟夫问题

```java
 addBoy(int nums); //  添加节点，构建成一个环形链表
 show(); // 遍历当前的环形链表
 countBoy(int startNo, int countNumber, int nums); // 计算出出圈的顺序
```

##### _06Stack 栈

ArrayStackDemo 数组实现栈操作

```java
push(int e); // 入栈
pop(); // 出栈
```

LinkStackDemo 链表模拟栈操作

```java
push(NodeList e); // 入栈
pop(); // 出栈
```

Calculator 用栈完成计算机的操作

Test自己写的计算器，改进了其方法操作

Test2自己写的计算器，添加（）的操作

Test3用系统提供的栈，实现计算机的操作

##### _07PolandNotation 逆波兰表达式

PolandNotation字符串生成逆波兰表达式

对逆波兰表达式求值

PolandNotation1 中缀表达式转化成后缀表达式

##### _8recursion 递归

MiGong 迷宫

Queue8八皇后问题

##### _09sort 排序

_01BubleSort冒泡排序
_02SelectSort选择排序
_03InsertSort插入排序
_04ShellSort希尔排序
_05QuickSort快速排序
_06MergetSort归并排序
_07RadixSort基数排序
SortTest 排序测试，一共有7中排序

##### _10search 查找

SeqSearch 顺序查找
BinarySearch 二分查找法
InsertValueSearch 差值查找
FibonacciSearch 斐波那契查找

##### _11hashtab 哈希表

HashTabDemo 哈希表测试

##### _12tree 树

_01BinaryTreeDemo 二叉树
TreePrintTest 数打印测试
_02ArrBinaryTreeDemo 数组顺序存储二叉树
_03ThreadedBinaryTreeDemo 线索化二叉树
HeapSort 堆排序

##### _13huffmantree 赫夫曼树

HuffmanTree 赫夫曼树 用数组生成赫夫曼树
HuffmanCode 赫夫曼编码 将字符串通过赫夫曼编码进行压缩与反解压

##### _14binarysorttree 二叉排序树

BinarySortTreeDemo 二叉排序树的创建,结点的删除

##### _15avl 平衡二叉树

AVLTreeDemo avl 平衡二叉树的左旋右旋

##### _16graph 图

Graph 图

##### _17algorithm 十大算法

_01BinarySearchNoRecur 非递归二分法查找
_02DAC 分治算法，汉诺塔问题
_03dynamic 动态规划，背包问题
_04kmp KMP算法
_05Greedy 贪心算法
_06Prim 普里姆算法
_07Kruskal 克鲁斯卡算法
_08Dijkstra 迪杰斯特拉 算法
_09Floyd 弗洛伊德 算法
_10horse 马踏棋盘 算法


