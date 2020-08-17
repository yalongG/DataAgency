package com.example._06Stack;

// 链表 栈
public class LinkStackDemo {
    public static void main(String[] args) {
        LinkStack stack = new LinkStack();
        stack.push(new NodeList(1));
        stack.push(new NodeList(2));
        stack.push(new NodeList(3));
        stack.push(new NodeList(4));
        stack.show();
        System.out.println("-------------------");
        NodeList pop = stack.pop();
        System.out.println(pop.toString());
        pop = stack.pop();
        System.out.println(pop.toString());
        pop = stack.pop();
        System.out.println(pop.toString());
        pop = stack.pop();
        System.out.println(pop.toString());
    }
}

class LinkStack {
    private NodeList top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(NodeList node) {
        node.next = top;
        top = node;
    }

    public NodeList pop() {
        if (isEmpty()) {
            throw new RuntimeException("为空");
        }

        NodeList temp = top;
        top = top.next;
        return temp;
    }

    public void show() {
        NodeList temp = top;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

class NodeList {
    private int value;
    NodeList next;

    public NodeList(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NodeList{" +
                "value=" + value +
                '}';
    }
}
