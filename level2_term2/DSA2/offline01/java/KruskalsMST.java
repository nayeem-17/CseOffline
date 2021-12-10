package offline01.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsMST {
    public static List<Edge> findMST(Graph graph) {
        List<Edge> result = new ArrayList<>();
        List<Edge> allEdges = new ArrayList<>();
        for (ArrayList<Edge> e : graph.getEdges()) {
            allEdges.addAll(e);
        }
        Collections.sort(allEdges, (e1, e2) -> {
            return (int) (e1.getCost() - e2.getCost());
        });

        DisjointSet disjointSet = new DisjointSet(graph.getLength());
        for (Edge edge : allEdges) {
            if (disjointSet.findRepresentative(edge.getSource()) != disjointSet
                    .findRepresentative(edge.getDestination())) {
                disjointSet.union(edge.getSource(), edge.getDestination());
                result.add(edge);
                // System.out.println(edge);
            }
        }
        return result;
    }
}
