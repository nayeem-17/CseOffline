package level2_term1.DSA.offline02.java;

public class LinkedList {
    private Node node;
    private LinkedList prev;
    private LinkedList next;

    public LinkedList() {
        this.node = null;
        this.prev = this;
        this.next = this;
    }

    public LinkedList(Node node) {
        this.node = node;
        this.prev = this;
        this.next = this;
    }

    public void insert(Node node) {
        if (this.node == null) {
            this.node = node;
        } else if (this.next == this) {
            this.next = new LinkedList(node);
            this.prev = this.next;
            this.next.next = this;
            this.next.prev = this;
        } else {
            LinkedList currentNode = this;
            while (currentNode.next != this) {
                currentNode = currentNode.next;
            }
            currentNode.next = new LinkedList(node);
            this.prev = currentNode.next;
            currentNode.next.next = this;
            currentNode.next.prev = currentNode;
        }
    }

    public void insert(int index, Node node) {
        LinkedList currentNode = this;
        if (index == 0) {
            insertAtFirst(node);
        } else {
            for (int i = 0; i < index; i++) {
                if (currentNode.next == this)
                    throw new Error("index out of bound");
                else
                    currentNode = currentNode.next;
            }
            if (currentNode.next == this) {
                currentNode.next = new LinkedList(currentNode.node);
                currentNode.next.prev = currentNode;
                currentNode.node = node;
                this.prev = currentNode.next;
                currentNode.next.next = this;
            } else {
                LinkedList temp = currentNode.next;
                currentNode.next = new LinkedList(currentNode.node);
                currentNode.node = node;
                temp.prev = currentNode.next;
                currentNode.next.prev = currentNode;
                currentNode.next.next = temp;
            }
        }
    }

    public Node getNode(int index) {
        if (this.node == null) {
            System.out.println("No element in this list!!!");
            return null;
        }
        if (index == 0)
            return this.node;

        LinkedList currentNode = this;
        for (int i = 0; i < index; i++) {
            if (currentNode.next == this)
                throw new Error("index out of bound");
            else
                currentNode = currentNode.next;
        }
        return currentNode.node;
    }

    public void remove(int playerId) {
        if (this.node == null) {
            System.out.println("No element in this list!!!");
        } else if (this.node.getPlayerId() == playerId) {
            LinkedList temp = this.next;
            this.node = this.next.node;
            this.next = temp.next;
            temp = null;
        } else {
            LinkedList currentNode = this;
            while (true) {
                if (currentNode.next == this) {
                    if (currentNode.node.getPlayerId() == playerId) {
                        currentNode.prev.next = this;
                        this.prev = currentNode.prev;
                        currentNode = null;
                        return;
                    }
                    return;
                } else if (currentNode.prev == this) {

                }
                if (currentNode.node.getPlayerId() == playerId) {

                    currentNode.prev.next = currentNode.next;
                    currentNode.next.prev = currentNode.prev;
                    currentNode = null;
                    return;
                }
                currentNode = currentNode.next;
            }
        }
    }

    public void insertAtFirst(Node node) {
        if (this.node == null) {
            this.node = node;
        } else if (this.next == null) {
            this.next = new LinkedList(this.node);
            this.node = node;
            this.next.next = this;
            this.prev = this.next;
        } else {
            LinkedList tempNext = this.next;
            this.next = new LinkedList(this.node);
            this.node = node;
            tempNext.prev = this.next;
            this.next.prev = this;
            this.next.next = tempNext;
        }
    }

    public void print() {
        LinkedList currentNode = this;
        if (this.node == null) {
            System.out.println("The list is empty!");
            return;
        }
        while (currentNode.next != this) {
            System.out.println(currentNode.node);
            currentNode = currentNode.next;
        }
        System.out.println(currentNode.node);
    }

    int get(int playerId) {
        int index = 0;
        if (this.node == null) {
            System.out.println("No element in this list!!!");
            return -1;
        } else {
            LinkedList currentNode = this;
            while (true) {
                if (currentNode.next == this) {
                    if (currentNode.node.getPlayerId() == playerId)
                        return index;
                    break;
                }
                if (currentNode.node.getPlayerId() == playerId)
                    return index;
                index++;
                currentNode = currentNode.next;
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insert(new Node(1, 4));
        list.insert(new Node(2, 5));
        list.insert(new Node(3, 2));
        list.insert(new Node(-1, 12));

        // for (int i = 0; i < 10; i++)
        // list.insert(new Node(i + 1, i * i + 1));
        // System.out.println(list.get(-1));
        list.remove(2);
        list.print();
        // System.out.println("```````````````````Testing````````````````````````````");
        // for (int i = 0; i < 4; i++) {
        // list.remove(i);
        // list.print();
        // System.out.println("```````````````````Testing````````````````````````````");

        // }
    }
}
