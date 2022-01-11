package offline04.java;

public class PairEdge {
    private int node;
    private float cost;

    /**
     * @param node
     * @param cost
     */
    public PairEdge(int node, float cost) {
        this.node = node;
        this.cost = cost;
    }

    /**
     * @return the node
     */
    public int getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(int node) {
        this.node = node;
    }

    /**
     * @return the cost
     */
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
    }

}
