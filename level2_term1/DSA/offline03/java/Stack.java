package level2_term1.DSA.offline3.java;

public class Stack {
    private Node node;
    private int capacity;

    private class Node {
        Object data;
        Node next;
        Node prev;
    }

    Stack() {
        this.node = null;
        this.capacity = 0;
    }

    public boolean isEmpty() {
        return node == null;
    }

    public void push(Object data) {
        if (this.node == null) {
            this.node = new Node();
            this.node.data = data;
            this.node.prev = this.node;
            this.node.next = this.node;
        } else {
            Node temp = this.node.prev;
            temp.next = new Node();
            temp.next.data = data;
            temp.next.prev = temp;
            this.node.prev = temp.next;
            temp.next.next = this.node;
        }
        this.capacity++;
    }

    public Object pop() {
        if (isEmpty())
            throw new Error("The stack is empty!!!");
        Node temp = this.node.prev;
        Object data;
        if (temp == this.node) {
            data = this.node.data;
            this.node = null;
        } else {
            data = temp.data;
            temp.prev.next = this.node;
            this.node.prev = temp.prev;
        }
        capacity--;
        return data;
    }

    public void print() {
        Node currentNode = this.node;
        if (this.node == null) {
            System.out.println("The stack is empty!");
            return;
        }
        System.out.println("The stack is ");
        while (currentNode.next != this.node) {
            System.out.println(currentNode.data);
            currentNode = currentNode.next;
        }
        System.out.println(currentNode.data);
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

}
