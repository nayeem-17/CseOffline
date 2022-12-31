package offline06.java;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap {
    List<BinomialTree> heap;
    int noOfElements;

    MaxHeap() {
        heap = new ArrayList<>();
        noOfElements = 0;
    }

    void merge(MaxHeap m) {
        if (m.heap.size() == 0)
            return;
        if (this.heap.size() == 0) {
            this.heap = m.heap;
            this.noOfElements = m.noOfElements;
            return;
        }

        BinomialTree carry = null;
        int n1 = this.noOfElements;
        int n2 = m.noOfElements;
        int new_size = (int) (Math.log((double) (n1 + n2)) / Math.log(2)) + 1;
        for (int i = 0; i < new_size; i++) {
            if (this.heap.size() == i)
                this.heap.add(null);
            if (m.heap.size() == i)
                m.heap.add(null);
            if (this.heap.get(i) == null) {
                if (m.heap.get(i) != null) {
                    // - 0 1
                    if (carry == null)
                        this.heap.set(i, m.heap.get(i)); // 0 0 1
                    else {
                        carry = m.heap.get(i).merge(carry); // 1 0 1
                    }
                } else {
                    // - 0 0
                    if (carry != null) {
                        this.heap.set(i, carry); // 1 0 0
                        carry = null;
                    }
                }
            } else if (m.heap.get(i) == null) {
                // - 1 0
                if (carry != null) {
                    // 1 1 0
                    carry = carry.merge(this.heap.get(i));
                    this.heap.set(i, null);
                }
            } else {
                // - 1 1
                if (carry == null) {
                    // 0 1 1
                    carry = m.heap.get(i).merge(this.heap.get(i));
                    this.heap.set(i, null);
                } else {
                    // 1 1 1
                    carry = carry.merge(m.heap.get(i));
                }
            }

        }
        this.noOfElements = n1 + n2;

    }

    int findMax() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < this.heap.size(); i++)
            if (this.heap.get(i) != null)
                if (max < this.heap.get(i).getMaxValue())
                    max = this.heap.get(i).getMaxValue();
        return max;
    }

    void insert(int value) {
        MaxHeap h = new MaxHeap();
        h.heap.add(new BinomialTree(value));
        h.noOfElements = 1;
        this.merge(h);
        System.out.println("Inserted " + value);
    }

    int extractMax() {
        int max = this.findMax();
        this.deleteMax();
        return max;
    }

    void deleteMax() {
        BinomialTree tree;
        int max = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < this.heap.size(); i++)
            if (this.heap.get(i) != null)
                if (max < this.heap.get(i).getMaxValue()) {
                    max = this.heap.get(i).getMaxValue();
                    index = i;
                }
        tree = this.heap.get(index);
        this.heap.set(index, null);
        MaxHeap m = new MaxHeap();
        for (Node n : tree.root.children) {
            m.heap.add(new BinomialTree(n));
        }
        this.merge(m);
    }

    void addBinomialTree(BinomialTree tree) {
        this.heap.add(tree);
    }

    void increaseKey(int prev_value, int new_value) {
        for (int i = 0; i < heap.size(); i++)
            if (this.heap.get(i) != null && this.heap.get(i).replaceValue(prev_value, new_value))
                break;
        System.out.println("Increase " + prev_value + " .The updated value is " + new_value);

    }

    void print() {
        System.out.println("Printing Binomial Heap...");
        System.out.println("—-------------------------");
        for (int i = 0; i < this.heap.size(); i++) {
            if (this.heap.get(i) != null) {
                System.out.println("Binomial Tree, B " + this.heap.get(i).order);
                this.heap.get(i).print();
            }
        }
        System.out.println("—-------------------------");

    }
}
