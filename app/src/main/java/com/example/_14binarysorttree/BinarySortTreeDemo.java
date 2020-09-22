package com.example._14binarysorttree;

// 二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的添加结点 到 binarySortTree
        for (int anArr : arr) {
            Node node = new Node(anArr);
            binarySortTree.add(node);
        }
        binarySortTree.infixOrder();

//        binarySortTree.delNode(2);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);
//        binarySortTree.delNode(3);
//
        binarySortTree.delNode(7);
//        binarySortTree.delNode(10);

        System.out.println("---------------------");
        binarySortTree.infixOrder();
    }
}

// 创建二叉排序树
class BinarySortTree {
    private Node root;

    // 查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    // 删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        // 1.需要先去找到要删除的结点，targetNode
        Node targetNode = search(value);
        // 如果没有找到要删除的结点
        if (targetNode == null) {
            return;
        }
        // 如果我们发现 当前二叉排序树 只有一个结点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 去找到 targetNode 的父节点
        Node parent = searchParent(value);
        // 如果要删除的结点 是叶子结点
        if (targetNode.left == null && targetNode.right == null) {
            // 判断 targetNode 是父节点的左子节点，还是右子节点
            if (parent.left != null && parent.left.value == value) { // 是左子结点
                parent.left = null;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) { //
            targetNode.value = delRightTreeMin(targetNode.right);
        } else { // 删除只有一棵子树的结点
            // 如果要删除的结点有左子结点
            if (targetNode.left != null) {
                // 如果 targetNode 是 parent 的左子结点
                if (parent != null) {
                    if (parent.left != null && parent.left.value == value) {  // targetNode 是parent 的左子结点
                        parent.left = targetNode.left;
                    } else if (parent.right != null && parent.right.value == value) { // targetNode 是parent 的右子结点
                        parent.right = targetNode.left;
                    }
                } else {
                    root = targetNode.left;
                }
            } else {
                // 如果 targetNode 是 parent 的右子结点
                if (parent != null) {
                    if (parent.left != null && parent.left.value == value) {  // targetNode 是parent 的左子结点
                        parent.left = targetNode.right;
                    } else if (parent.right != null && parent.right.value == value) { // targetNode 是parent 的右子结点
                        parent.right = targetNode.right;
                    }
                } else {
                    root = targetNode.right;
                }
            }
        }
    }

    // 编写方法
    // node 传入的结点(当做二叉排序树的跟节点)
    // 返回的是以node 为跟节点的二叉排序树的最小结点的值
    // 删除最小结点
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环的查找左结点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // 这是target 就指向了最小结点
        delNode(target.value);
        return target.value;
    }

    // 添加结点的方法
    public void add(Node node) {
        if (this.root == null) {
            this.root = node;
        } else {
            this.root.add(node);
        }
    }

    public void infixOrder() {
        if (this.root == null) {
            System.out.println("当前二叉排序树为空，不能遍历");
        } else {
            this.root.infixOrder();
        }
    }
}

// 创建Node结点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 查找要删除的结点
    // value 希望删除的结点的值
    // 如果找到返回该节点，否则返回null
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) { // 如果查找的值小于当前结点，向左子树递归查找
            if (this.left == null) { // 如果左子结点为空
                return null;
            }
            return this.left.search(value);
        } else { // 如果查找的值不小于当前结点，向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    // 查找要删除结点的父节点
    // value 要找的结点的值
    // 返回的是要删除的结点的父节点，如果没有就返回null
    public Node searchParent(int value) {
        // 如果当前结点就是要删除的结点的父节点，就返回
        if ((this.left != null && this.left.value == value)
                || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            // 如果查找的值小于当前结点的值，并且当前结点的左子结点不为空
            if (this.value > value && this.left != null) {
                return this.left.searchParent(value); // 向左子树递归查找
            } else if (this.value <= value && this.right != null) {
                return this.right.searchParent(value); // 向右子树递归查找
            } else {
                return null; // 没有找到父节点
            }
        }
    }

    // 添加结点的方法
    // 递归的形式添加，注意要满足二叉排序数的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断传入的结点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            if (this.left == null) { // 如果当前结点左子结点为null
                this.left = node;
            } else {
                this.left.add(node); // 递归向左子树添加
            }
        } else { // 添加的结点的值，大于当前结点的值
            if (this.right == null) {  // 如果右子树为空
                this.right = node;
            } else {
                this.right.add(node); // 递归向右子树添加
            }
        }
    }

    // 中序遍历二叉树
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
