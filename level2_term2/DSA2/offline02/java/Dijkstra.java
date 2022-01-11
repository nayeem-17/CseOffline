package offline02.java;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Dijkstra {
    public static void singleSourceShortestPath(Graph graph, int source, int destination) {
        Queue<PairEdge> queue = new PriorityQueue<>((a, b) -> (int) (a.getCost() - b.getCost()));

        float cost[] = new float[graph.getLength() + 1];
        int[] parents = new int[graph.getLength() + 1];
        for (int i = 0; i < graph.getLength() + 1; i++)
            cost[i] = Float.MAX_VALUE;

        // List<Edge> result = new ArrayList<>();
        queue.add(new PairEdge(source, 0));
        cost[source] = 0;

        while (!queue.isEmpty()) {
            PairEdge currentPairEdge = queue.poll();
            if (!queue.isEmpty())
                queue.remove();

            for (Edge e : graph.getEdges()[currentPairEdge.getNode()]) {
                if (cost[e.getDestination()] > cost[e.getSource()] + e.getCost()) {
                    cost[e.getDestination()] = cost[e.getSource()] + e.getCost();
                    queue.add(new PairEdge(e.getDestination(), cost[e.getDestination()]));
                    parents[e.getDestination()] = e.getSource();
                    // System.out.println(e);
                }
            }
        }
        // System.out.println(Arrays.toString(cost));
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