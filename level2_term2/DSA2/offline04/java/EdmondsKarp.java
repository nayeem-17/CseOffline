package offline04.java;

import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

    public static Graph createGraph(String[] teams, Integer[] wins, Integer[] remaining, Integer[][] g,
            int currentTeam) {
        int numberOfNodes = 2 + teams.length + (teams.length - 1) * (teams.length - 2) / 2;
        String[] track = new String[numberOfNodes];
        int numberOfMatches = (teams.length - 1) * (teams.length - 2) / 2;
        track[0] = "s";
        int trackCount = 1;
        track[numberOfNodes - 1] = "t";
        Graph graph = new Graph(numberOfNodes);
        for (int i = 0; i < teams.length; i++)
            for (int j = i + 1; j < teams.length; j++) {
                if (i != currentTeam && j != currentTeam && i != j) {
                    track[trackCount] = i + "-" + j;
                    // System.out.println(track[trackCount]);
                    graph.addEdges(0, trackCount, g[i][j]);
                    graph.addEdges(trackCount, 1 + numberOfMatches + i, Float.MAX_VALUE);
                    graph.addEdges(trackCount, 1 + numberOfMatches + j, Float.MAX_VALUE);
                    trackCount++;
                }
            }
        for (int i = 0; i < teams.length; i++) {
            if (i != currentTeam)
                graph.addEdges(1 + numberOfMatches + i, numberOfNodes - 1,
                        wins[currentTeam] + remaining[currentTeam] - wins[i]);
            // System.out.println(wins[currentTeam] + remaining[currentTeam] - wins[i]);
        }
        return graph;
    }

    public static float maxFlow(Graph graph, int source, int target) {
        float flow = 0;
        float new_flow;
        int parents[] = new int[graph.getLength()];
        while (true) {
            new_flow = bfs(source, target, parents, graph);
            if (new_flow == 0)
                break;
            flow += new_flow;
            int current_node = target;
            while (current_node != source) {
                int previous_node = parents[current_node];
                graph.updateEdge(previous_node, current_node, new_flow);
                current_node = previous_node;
            }
        }
        return flow;
    }

    private static float bfs(int source, int target, int[] parents, Graph graph) {
        parents[source] = -2;
        for (int i = 1; i < parents.length; i++)
            parents[i] = -1;
        Queue<PairEdge> queue = new LinkedList<>();
        queue.add(new PairEdge(0, Integer.MAX_VALUE));
        while (!queue.isEmpty()) {
            PairEdge curPairEdge = queue.poll();
            for (Edge nextEdge : graph.getEdges()[curPairEdge.getNode()]) {
                if (parents[nextEdge.getDestination()] == -1 && nextEdge.getCapacity() != 0) {
                    parents[nextEdge.getDestination()] = nextEdge.getSource();
                    float new_flow = curPairEdge.getCost();
                    if (new_flow > nextEdge.getCapacity())
                        new_flow = nextEdge.getCapacity();
                    if (nextEdge.getDestination() == target)
                        return new_flow;
                    queue.add(new PairEdge(nextEdge.getDestination(), new_flow));
                }
            }
        }
        return 0;

    }

}
