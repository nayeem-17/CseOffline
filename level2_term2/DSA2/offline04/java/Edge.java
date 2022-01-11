package offline04.java;

public class Edge {
    private int source;
    private int destination;
    private float flow;
    private float capacity;

    public Edge(int source, int destination, float capacity) {
        this.flow = 0;
        this.source = source;
        this.destination = destination;
        this.capacity = capacity;
    }

    /**
     * @return the capacity
     */
    public float getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the destination
     */
    public int getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * @return the cost
     */
    public float getFlow() {
        return flow;
    }

    /**
     * @param cost the cost to set
     */
    public void setFlow(float flow) {
        this.flow = flow;
    }

    /**
     * @return the source
     */
    public int getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(int source) {
        this.source = source;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        // return "Edge [cost=" + cost + ", destination=" + destination + ", source=" +
        // source + "]";
        return "(" + source + "," + destination + ")";
    }

}
