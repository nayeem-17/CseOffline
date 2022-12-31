package offline07.java;

public class Node {
    private Node parent;
    private Node rightNode;
    private Node leftNode;
    private int height;
    private int value;

    /**
     * 
     */
    public Node() {
        this.parent = null;
        this.height = 0;
        this.leftNode = null;
        this.leftNode = null;
        this.value = -1;
    }

    public boolean insert(int value) {

        if (this.value > value) {
            if (this.leftNode == null) {
                this.leftNode = new Node();
                this.leftNode.value = value;
                this.leftNode.parent = this;
                this.leftNode.height = 1;
                if (this.rightNode == null) {
                    this.height++;
                    return false;
                }
            } else {
                boolean isViolated = this.leftNode.insert(value);
                int prevHeight = Math.max(this.getLeftNodeHeight(), this.getRightNodeHeight()) + 1;
                this.rebalance();
                if (prevHeight == this.height)
                    return isViolated;
                return true;
            }
        } else {
            if (this.rightNode == null) {
                this.rightNode = new Node();
                this.rightNode.value = value;
                this.rightNode.parent = this;
                this.rightNode.height = 1;
                if (this.leftNode == null) {
                    this.height++;
                    return false;
                }
            } else {
                boolean isViolated = this.rightNode.insert(value);
                int prevHeight = Math.max(this.getLeftNodeHeight(), this.getRightNodeHeight()) + 1;
                this.rebalance();
                if (prevHeight == this.height)
                    return isViolated;
                return true;
            }
        }
        return false;
    }

    public int getBalanceFactor() {
        int lefChildHeight = 0;
        int rightChildHeight = 0;
        if (this.rightNode != null)
            rightChildHeight = this.rightNode.height;
        if (this.leftNode != null)
            lefChildHeight = this.leftNode.height;
        return lefChildHeight - rightChildHeight;
    }

    public void adjustHeight() {
        int lefChildHeight = 0;
        int rightChildHeight = 0;
        if (this.rightNode != null)
            rightChildHeight = this.rightNode.height;
        if (this.leftNode != null)
            lefChildHeight = this.leftNode.height;
        this.height = Math.max(lefChildHeight, rightChildHeight) + 1;

    }

    public int getRightNodeHeight() {
        if (this.rightNode == null)
            return 0;
        return this.rightNode.height;
    }

    public int getLeftNodeHeight() {
        if (this.leftNode == null)
            return 0;
        return this.leftNode.height;
    }

    public void rebalance() {
        // have to balance the tree
        int balanceFactor = getBalanceFactor();
        if (Math.abs(balanceFactor) > 1) {
            if (balanceFactor > 0) {
                if (this.getLeftNodeHeight() > this.getRightNodeHeight()) {
                    this.rightRotate();
                } else {
                    this.leftNode.leftRotate();
                    this.rightRotate();
                }
            } else {
                if (this.getRightNodeHeight() > this.getLeftNodeHeight()) {
                    this.leftRotate();
                } else {
                    this.rightNode.rightRotate();
                    this.leftRotate();
                }
            }
        }
        this.adjustHeight();
        // this.height = Math.max(this.leftNode.height, this.rightNode.height) + 1;
    }

    public Boolean search(int value) {
        if (this.value == value)
            return true;
        else if (this.value > value) {
            if (this.leftNode == null)
                return false;
            else
                return this.leftNode.search(value);
        } else {
            if (this.rightNode == null)
                return false;
            else
                return this.rightNode.search(value);
        }
    }

    public boolean delete(int value) {
        if (this.value > value) {
            Boolean isViolated = this.leftNode.delete(value);
            this.adjustHeight();
            if (Math.abs(this.getBalanceFactor()) > 1)
                isViolated = true;
            this.rebalance();
            return isViolated;
        } else if (this.value < value) {
            Boolean isViolated = this.rightNode.delete(value);
            this.adjustHeight();
            if (Math.abs(this.getBalanceFactor()) > 1)
                isViolated = true;
            this.rebalance();
            return isViolated;
        } else {
            // donno what todo
            if (this.leftNode == null && this.rightNode == null) {
                // leaf node
                if (this.parent == null) {
                    this.value = -1;
                    return false;
                } else if (this.parent.leftNode.value == this.value) {
                    this.parent.leftNode = null;
                    return false;
                } else {
                    this.parent.rightNode = null;
                    return false;
                }
            } else if (this.leftNode == null) {
                if (this.parent == null) {
                    this.value = this.rightNode.value;
                    this.rightNode = null;
                } else if (this.rightNode.value == -1) {
                    if (this.parent.leftNode.value == this.value) {
                        this.parent.leftNode = null;
                        return false;
                    } else {
                        this.parent.rightNode = null;
                        return false;
                    }
                }
                this.parent.rightNode = this.rightNode;
                this.rightNode.parent = this.parent;

            } else if (this.rightNode == null) {
                if (this.parent == null) {
                    this.value = this.leftNode.value;
                    this.leftNode = null;
                } else if (this.leftNode.value == -1) {
                    if (this.parent.leftNode.value == this.value) {
                        this.parent.leftNode = null;
                        return false;
                    } else {
                        this.parent.rightNode = null;
                        return false;
                    }
                }
                this.parent.leftNode = this.leftNode;
                this.leftNode.parent = this.parent;
            } else {
                // get min value
                int minValue = this.rightNode.getMinValue();
                this.value = minValue;
                this.rightNode.delete(minValue);
            }
        }
        return false;
    }

    private int getMinValue() {
        if (this.leftNode == null)
            return this.value;
        else
            return this.leftNode.getMinValue();
    }

    public void leftRotate() {
        // node x -> this
        // node y -> this.rightChild
        // x<=> y
        if (this.rightNode == null)
            return;
        Node temp = this.rightNode;
        if (temp.rightNode == null) {
            temp.rightNode = new Node();
        }
        if (temp.leftNode == null) {
            temp.leftNode = new Node();
        } // first exchange values
          // fix parent values

        temp.leftNode.parent = temp;
        temp.rightNode.parent = temp;

        int tempValue = temp.value;
        temp.value = this.value;
        this.value = tempValue;

        this.rightNode = this.rightNode.rightNode;
        this.rightNode.parent = this;

        temp.rightNode = temp.leftNode;
        temp.leftNode = this.leftNode;

        temp.parent = this;
        // temp.height = Math.max(temp.leftNode.height, temp.rightNode.height) + 1;
        temp.adjustHeight();
        this.leftNode = temp;

        this.adjustHeight();
        // this.height = Math.max(this.leftNode.height, this.rightNode.height) + 1;
    }

    public void rightRotate() {
        // node x -> this
        // node y -> this.leftChild
        // x<=> y
        if (this.leftNode == null)
            return;
        Node temp = this.leftNode;
        if (temp.rightNode == null) {
            temp.rightNode = new Node();
        }
        if (temp.leftNode == null) {
            temp.leftNode = new Node();
        }

        temp.rightNode.parent = temp;
        temp.leftNode.parent = temp;

        // first exchange values
        // fix parent values
        int tempValue = temp.value;
        temp.value = this.value;
        this.value = tempValue;

        this.leftNode = this.leftNode.leftNode;
        this.leftNode.parent = this;

        temp.leftNode = temp.rightNode;
        temp.rightNode = this.rightNode;

        temp.parent = this;
        // temp.height = Math.max(temp.leftNode.height, temp.rightNode.height) + 1;
        temp.adjustHeight();
        this.rightNode = temp;

        this.adjustHeight();
        // this.height = Math.max(this.leftNode.height, this.rightNode.height) + 1;

    }

    /**
     * @return the rightNode
     */
    public Node getRightNode() {
        return rightNode;
    }

    /**
     * @param rightNode the rightNode to set
     */
    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * @return the leftNode
     */
    public Node getLeftNode() {
        return leftNode;
    }

    /**
     * @param leftNode the leftNode to set
     */
    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
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

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
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

    public String print() {
        String outputString = "";
        if (this.value != -1)
            outputString += value;
        else
            return outputString;

        if ((this.leftNode == null) && this.rightNode == null)
            return outputString;
        if (this.leftNode == null) {
            if (this.rightNode.value == -1)
                return outputString;
            outputString += "()";
        } else {
            outputString += "(";
            outputString += this.leftNode.print();
            outputString += ")";
        }
        if (this.rightNode == null) {
            if (this.leftNode.value == -1)
                return outputString;
            outputString += "()";
        } else {
            outputString += "(";
            outputString += this.rightNode.print();
            outputString += ")";
        }
        return outputString;
    }
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Node [height=" + height + ", leftNode=" + leftNode + ", rightNode=" + rightNode + ", value=" + value
                + "]";
    }
}
