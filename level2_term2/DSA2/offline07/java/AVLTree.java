package offline07.java;

public class AVLTree {
    Node root;

    AVLTree() {
        this.root = null;
    }

    public void insert(int value) {
        if (this.root == null) {
            this.root = new Node();
            this.root.setHeight(1);
            this.root.setValue(value);
        } else {
            boolean isViolated = root.insert(value);
            if (isViolated) {
                System.out.println("Height invariant violated.");
                System.out.print("After rebalancing:   ");
            }
        }
        System.out.println(this.root.print());
    }

    public void delete(int value) {
        if (this.root == null)
            return;
        else {
            boolean isViolated = this.root.delete(value);
            if (isViolated) {
                System.out.println("Height invariant violated.");
                System.out.print("After rebalancing:   ");
            }
        }
        System.out.println(this.root.print());
    }

    public boolean find(int value) {
        if (this.root == null)
            return false;
        return this.root.search(value);
    }
}
