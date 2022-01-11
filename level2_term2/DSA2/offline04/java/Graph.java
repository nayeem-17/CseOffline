package offline04.java;

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

    public void addEdges(int from, int to, float capacity) {
        Edge node = new Edge(from, to, capacity);
        Edge node1 = new Edge(to, from, 0);
        this.edges[from].add(node);
        this.edges[to].add(node1);
    }

    public int getLength() {
        return length;
    }

    public float getFlow(int from, int to) {
        // return this.edges[from][to];
        List<Edge> temp = this.edges[from];
        for (Edge node : temp) {
            if (node.getDestination() == to)
                return node.getFlow();
        }
        return -1;
    }

    /**
     * @return the edges
     */
    public ArrayList<Edge>[] getEdges() {
        return edges;
    }

    public Edge getEdge(int source, int destination) {
        Edge temp = new Edge(0, 0, 0);
        for (Edge e : this.edges[source]) {
            if (e.getDestination() == destination) {
                temp.setCapacity(e.getCapacity());
                temp.setDestination(e.getDestination());
                temp.setFlow(e.getFlow());
                temp.setSource(e.getSource());
                break;
            }
        }
        return temp;
    }

    public void updateEdge(int previous, int current, float flow) {

        for (Edge e : this.edges[current]) {
            if (e.getDestination() == previous) {
                e.setCapacity(e.getCapacity() + flow);
                break;
            }
        }

        for (Edge e : this.edges[previous]) {
            if (e.getDestination() == current) {
                e.setCapacity(e.getCapacity() - flow);
                break;
            }
        }
    }
}
