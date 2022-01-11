package offline03.java;

public class Edge {
    private int source;
    private int destination;
    private float cost;

    public Edge(int source, int destination, float cost) {
        this.cost = cost;
        this.source = source;
        this.destination = destination;
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
    public float getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(float cost) {
        this.cost = cost;
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
