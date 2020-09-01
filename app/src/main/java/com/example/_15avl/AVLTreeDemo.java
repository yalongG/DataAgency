package com.example._15avl;

// avl 树 平衡二叉树
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
        System.out.println(avlTree.getRoot());
    }
}

// 创建AVL tree
class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 返回当前结点的高度,以该节点为跟节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : this.left.height(), right == null ? 0 : this.right.height()) + 1;
    }

    // 左旋转方法
    private void leftRotate() {
        // 创建新的结点，以当前跟节点的值
        Node newNode = new Node(this.value);
        // 把新的结点的左子树设置成当前结点的左子树
        newNode.left = this.left;
        // 把新的结点的右子树，设置成当前结点的右子树的左子树
        newNode.right = this.right.left;
        // 把当前结点的值替换成右子结点的值
        this.value = this.right.value;
        // 把当前结点的右子树设置成右子树的右子树
        this.right = this.right.right;
        // 把当前结点的左子树设置成 新的结点
        this.left = newNode;
    }

    // 右旋转
    private void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = left.value;
        this.left = this.left.left;
        right = newNode;
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

        // 当添加完一个结点后，如果：右子树的高度 - 左子树的高度 > 1
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树 的左子树的高度 大于 它的右子树的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                // 先对右子结点进行右旋转
                this.right.rightRotate();
            }
            // 在对当前结点进行左旋转
            leftRotate();
            return;
        }
        if (rightHeight() - leftHeight() < -1) {
            // 如果它的左子树的右子树高度大于它的左子树的高度
            if (this.left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前结点的左结点 -> 左旋转
                this.left.leftRotate();
            }
            // 在对当前结点进行右旋转
            rightRotate();
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
