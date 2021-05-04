// package level2_term1.DSA.offline06.java;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Integer> withDFS(Graph graph, int[] startingCity) {
        List<Integer> collectedPieces = new ArrayList<Integer>();
        for (int i = 0; i < startingCity.length; i++) {
            int temp = graph.doDFS(startingCity[i]);
            collectedPieces.add(temp);
        }
        return collectedPieces;
    }

    public static List<Integer> withBFS(Graph graph, int[] startingCity) {
        List<Integer> collectedPieces = new ArrayList<Integer>();
        for (int i = 0; i < startingCity.length; i++) {
            int count = 0;
            boolean[] visited = new boolean[graph.getLength()];
            graph.doBFS(startingCity[i], visited);
            for (int j = 0; j < visited.length; j++) {
                if (visited[j] == true)
                    count += graph.getHiddenPieces()[j];
            }
            collectedPieces.add(count);
        }
        return collectedPieces;
    }

    public static void main(String[] args) {
        int C, R, L, F;
        Scanner scanner = new Scanner(System.in);
        C = scanner.nextInt();
        R = scanner.nextInt();
        L = scanner.nextInt();
        F = scanner.nextInt();
        Graph graph = new Graph(C);
        for (int i = 0; i < R; i++) {
            int from, to;
            from = scanner.nextInt();
            to = scanner.nextInt();
            graph.addEdges(from, to);
        }
        for (int i = 0; i < L; i++) {
            int city, hiddenPieces;
            city = scanner.nextInt();
            hiddenPieces = scanner.nextInt();
            graph.setHiddenPieces(city, hiddenPieces);
        }
        int startingCity[] = new int[F];
        for (int i = 0; i < F; i++) {
            int friend, city;
            city = scanner.nextInt();
            friend = scanner.nextInt();
            startingCity[friend] = city;
        }

        List<Integer> collectedPieces = withBFS(graph, startingCity);
        // List<Integer> collectedPieces = withDFS(graph, startingCity);

        int totalCollectedPieces = collectedPieces.stream().mapToInt(a -> a).sum();
        int totalHiddenPieces = graph.getTotalHiddenPieces();
        String result = "";
        if (totalCollectedPieces == totalHiddenPieces) {
            result += "Mission Accomplished";

        } else {
            result += "Mission Impossible";
        }
        result += "\n";

        result += (totalCollectedPieces + " out of " + totalHiddenPieces + " pieces are collected");
        result += "\n";
        for (int i = 0; i < collectedPieces.size(); i++) {
            result += (i + " collected " + collectedPieces.get(i) + " pieces");
            result += "\n";
        }
        try {
            File outputFile = new File("output.txt");
            FileOutputStream outputFileStream = new FileOutputStream(outputFile);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            outputFileStream.write(result.getBytes());
            outputFileStream.flush();
            outputFileStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // System.out.println(result);
        scanner.close();
    }
}
