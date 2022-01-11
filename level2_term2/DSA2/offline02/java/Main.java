package offline02.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "input.txt";
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
        int source = scan.nextInt();
        int destination = scan.nextInt();

        // Dijkstra.singleSourceShortestPath(graph, source, destination);
        BellmanFord.singleSourceShortestPath(graph, source, destination);
        scan.close();
    }
}
