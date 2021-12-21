package offline03.java;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private ArrayList<Edge>[] edges;

    private int length;

    public Graph(int n) {
        this.edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            this.edges[i] = new ArrayList<Edge>();
        }
        this.length = n;
    }

    public void addEdges(int from, int to, float cost) {
        Edge node = new Edge(from, to, cost);
        // Edge node1 = new Edge(to, from, cost);
        this.edges[from].add(node);
        // this.edges[to].add(node1);
    }

    public int getLength() {
        return length;
    }

    public float getCost(int from, int to) {
        // return this.edges[from][to];
        List<Edge> temp = this.edges[from];
        for (Edge node : temp) {
            if (node.getDestination() == to)
                return node.getCost();
        }
        return -1;
    }

    /**
     * @return the edges
     */
    public ArrayList<Edge>[] getEdges() {
        return edges;
    }

}
