package offline06.java;

import java.util.ArrayList;

public class BinomialTree {
    Node root;
    int order;

    /**
     * @return the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Node root) {
        this.root = root;
    }

    /**
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    BinomialTree(int value) {
        this.root = new Node(value);
        this.order = 0;
    }

    BinomialTree(Node node) {
        this.root = node;
        this.order = node.children.size();
    }

    int getMaxValue() {
        return this.root.getValue();
    }

    BinomialTree merge(BinomialTree tree) {
        if (this.root.getValue() > tree.root.getValue()) {
            tree.root.parent = this.root;
            this.root.addChild(tree.root);
            this.order++;
            return this;
        } else {
            this.root.parent = tree.root;
            tree.root.addChild(this.root);
            tree.order++;
            return tree;
        }
    }

    boolean replaceValue(int prev_val, int new_val) {
        return this.root.replaceValue(prev_val, new_val);
    }

    void print() {
        ArrayList<ArrayList<Node>> nodes = new ArrayList<ArrayList<Node>>();
        ArrayList<Node> node0 = new ArrayList<>();
        node0.add(this.root);
        nodes.add(node0);
        for (int i = 1; i <= this.order; i++) {
            ArrayList<Node> temp = new ArrayList<>();
            for (Node n : nodes.get(i - 1)) {
                temp.addAll(n.children);
            }
            nodes.add(temp);
        }
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print("Level " + i + " : ");
            for (Node n : nodes.get(i))
                System.out.print(n.value + " ");
            System.out.println();
        }
        System.out.println();
    }
}
