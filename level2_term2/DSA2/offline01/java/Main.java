package offline01.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "mst.in";
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        int vertices = scan.nextInt();
        int noOfEdges = scan.nextInt();
        Graph graph = new Graph(vertices);
        for (int i = 0; i < noOfEdges; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            float cost = scan.nextFloat();
            graph.addEdges(from, to, cost);
        }

        List<Edge> result = PrimMST.findMST(graph);
        String primOutString = "List of edges selected by Prim’s: {";
        float minimumCost = 0;
        float minimumCostK = 0;
        for (Edge edge : result) {
            primOutString += edge;
            minimumCost += edge.getCost();
        }

        primOutString += "}";
        System.out.println("Cost of the minimum spanning tree : " + minimumCost);
        System.out.println(primOutString);

        String kruskalOutString = "List of edges selected by Kruskal’s: {";
        List<Edge> result1 = KruskalsMST.findMST(graph);
        for (Edge edge : result1) {
            kruskalOutString += edge;
            minimumCostK += edge.getCost();
        }
        kruskalOutString += "}";
        System.out.println(kruskalOutString);
        scan.close();
    }
}
