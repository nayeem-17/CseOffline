package offline06.java;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int value;
    List<Node> children;
    Node parent;

    public Node(int value) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the children
     */
    public List<Node> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(List<Node> children) {
        this.children = children;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    boolean replaceValue(int prev_val, int new_val) {
        if (this.value == prev_val) {
            this.value = new_val;
            this.heapify();
        }
        for (int i = 0; i < children.size(); i++) {
            boolean result = this.children.get(i).replaceValue(prev_val, new_val);
            if (result == false)
                continue;
            return result;
        }
        return false;
    }

    void addChild(Node node) {
        this.children.add(node);
    }

    void heapify() {
        if (this.parent == null)
            return;
        if (this.parent.value < this.value) {
            int temp = this.parent.value;
            this.parent.setValue(this.value);
            this.setValue(temp);
            this.parent.heapify();
        }
    }

    // public static void main(String[] args) {
    // List<Integer> l = new ArrayList<Integer>();
    // l.add(null);
    // l.set(0, 11221);
    // System.out.println(l.get(0));
    // System.out.println(l.size());
    // }
}
