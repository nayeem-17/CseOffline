package level2_term1.DSA.offline3.java;

public class Queue {
    private Node node;
    private int capacity;

    private class Node {
        char data;
        Node next;
        Node prev;
    }

    public char dequeue() {
        if (isEmpty())
            throw new Error("The stack is empty!!!");
        Node temp = this.node.next;
        char data;
        if (temp == this.node) {
            data = this.node.data;
            this.node = null;
        } else {
            data = this.node.data;
            this.node.data = this.node.next.data;
            this.node.next = temp.next;
            temp.next.prev = this.node;
        }
        capacity--;
        return data;
    }

    public boolean isEmpty() {
        return node == null;
    }

    public void enqueue(char data) {
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

    public void print() {
        Node currentNode = this.node;
        if (this.node == null) {
            System.out.println("The queue is empty!");
            return;
        }
        System.out.println("The queue is ");
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

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.enqueue('a');
        queue.enqueue('b');
        queue.print();
        queue.dequeue();
        queue.print();
    }
}
