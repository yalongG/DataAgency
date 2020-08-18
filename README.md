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

##### _8recursion

MiGong 迷宫



