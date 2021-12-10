package offline01.java;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimMST {
    public static List<Edge> findMST(Graph graph) {
        int len = graph.getLength();
        List<Edge> result = new ArrayList<>();
        // float totalCost = 0;
        int currentNode = 0;
        boolean visited[] = new boolean[len];
        PriorityQueue<Edge> edgePQ = new PriorityQueue<>((a, b) -> (int) (a.getCost() - b.getCost()));

        edgePQ.add(graph.getEdges()[0].get(0));

        while (true) {

            edgePQ.poll();
            if (!visited[currentNode])
                for (Edge edge : graph.getEdges()[currentNode]) {
                    if (visited[edge.getDestination()] == false)
                        edgePQ.add(edge);
                }

            visited[currentNode] = true;

            if (edgePQ.size() != 0) {
                Edge topEdge = edgePQ.peek();
                if (visited[topEdge.getDestination()] == true) {
                    continue;
                }

                result.add(topEdge);
                // totalCost += edgePQ.peek().getCost();
                currentNode = topEdge.getDestination();
            } else
                break;

        }
        // System.out.println("Total Cost = " + totalCost);
        return result;
    }
}
