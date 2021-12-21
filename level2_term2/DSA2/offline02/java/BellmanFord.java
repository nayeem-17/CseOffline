package offline02.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BellmanFord {
    public static void singleSourceShortestPath(Graph graph, int source, int destination) {
        float cost[] = new float[graph.getLength() + 1];
        int[] parents = new int[graph.getLength() + 1];
        for (int i = 0; i < graph.getLength() + 1; i++)
            cost[i] = Float.MAX_VALUE;
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < graph.getLength(); i++)
            for (Edge e : graph.getEdges()[i])
                edges.add(e);
        cost[source] = 0;
        for (int i = 0; i < graph.getLength(); i++) {
            for (Edge e : edges) {
                if (cost[e.getDestination()] > cost[e.getSource()] + e.getCost()) {
                    cost[e.getDestination()] = cost[e.getSource()] + e.getCost();
                    parents[e.getDestination()] = e.getSource();
                }
            }
        }

        for (Edge e : edges)
            if (cost[e.getDestination()] > cost[e.getSource()] + e.getCost()) {
                System.out.println("The graph contains a negative cycle");
                return;
            }

        Stack<Integer> path = new Stack<>();

        int dest = destination;

        while (true) {
            int currentParent = parents[dest];
            if (currentParent == source) {
                path.push(currentParent);
                break;
            }
            path.push(currentParent);
            dest = currentParent;
        }
        System.out.println("Shortest path cost: " + cost[destination]);
        String outString = "";
        while (!path.empty()) {
            outString += path.pop() + "->";
        }
        outString += destination;
        System.out.println(outString);
        return;
    }
}
