package offline03.java;

import java.util.Arrays;
import java.util.List;

public class FW {
    public static void allPairShortestPath(Graph graph) {
        int len = graph.getLength();
        float[][] result = new float[len][len];
        Arrays.stream(result).forEach(a -> Arrays.fill(a, Float.MAX_VALUE));
        for (int i = 0; i < len; i++) {
            result[i][i] = 0;
            List<Edge> edges = graph.getEdges()[i];
            for (Edge edge : edges) {
                result[i][edge.getDestination()] = edge.getCost();
            }
        }
        for (int k = 1; k < len; k++)
            for (int i = 1; i < len; i++)
                for (int j = 1; j < len; j++)
                    if (result[i][k] + result[k][j] < result[i][j])
                        result[i][j] = result[i][k] + result[k][j];
        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                if (result[i][j] == Float.MAX_VALUE)
                    System.out.print("INF ");
                else
                    System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
