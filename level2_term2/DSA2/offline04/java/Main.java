package offline04.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "input.txt";
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        int numberOfTeams = scanner.nextInt();
        String[] teams = new String[numberOfTeams];
        Integer[] wins = new Integer[numberOfTeams];
        Integer[] looses = new Integer[numberOfTeams];
        Integer[] remaining = new Integer[numberOfTeams];
        Integer[][] g = new Integer[numberOfTeams][numberOfTeams];
        for (int i = 0; i < numberOfTeams; i++) {
            teams[i] = scanner.next();
            wins[i] = scanner.nextInt();
            looses[i] = scanner.nextInt();
            remaining[i] = scanner.nextInt();
            for (int j = 0; j < numberOfTeams; j++) {
                g[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < teams.length; i++) {
            Graph graph = EdmondsKarp.createGraph(teams, wins, remaining, g, i);
            boolean isEliminated = false;
            for (Edge e : graph.getEdges()[graph.getLength() - 1])
                if (e.getCapacity() < 0) {
                    System.out.println(teams[i] + " is eliminated.");
                    System.out.println("They can win at most " + wins[i] + "+" + remaining[i] + " = " + (wins[i]
                            + remaining[i]) + " games");
                    isEliminated = true;
                    break;
                }

            if (!isEliminated) {
                float total = 0;
                for (Edge e : graph.getEdges()[0])
                    total += e.getCapacity();
                float maxFlow = EdmondsKarp.maxFlow(graph, 0, graph.getLength() - 1);
                if (maxFlow < total) {
                    System.out.println(teams[i] + " is eliminated.");
                    System.out.println("They can win at most " + wins[i] + "+" + remaining[i] + " = " + (wins[i]
                            + remaining[i]) + " games");
                }
            }
        }
        scanner.close();
    }
}
