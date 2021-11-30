package level2_term2.DSA2.Template;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private ArrayList<ArrayList<Integer>> nodes;
    private int length;

    public Graph(int n) {
        this.nodes = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            this.nodes.add(new ArrayList<Integer>());
        }
        this.length = n;
    }

    public void addEdges(int from, int to) {
        this.nodes.get(from).add(to);
        this.nodes.get(to).add(from);
    }

    public int getLength() {
        return length;
    }

    // dfs
    public void dfs(int start) {
        List<Integer> visited = new ArrayList<Integer>();
        visited.add(start);
        dfs(start, visited);
    }

    private void dfs(int start, List<Integer> visited) {
        for (int i = 0; i < nodes.get(start).size(); i++) {
            int next = nodes.get(start).get(i);
            if (!visited.contains(next)) {
                visited.add(next);
                dfs(next, visited);
            }
        }
    }

    // bfs
    public void bfs(int start) {
        List<Integer> visited = new ArrayList<Integer>();
        visited.add(start);
        bfs(start, visited);
    }

    private void bfs(int start, List<Integer> visited) {
        List<Integer> queue = new ArrayList<Integer>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int next = queue.remove(0);
            for (int i = 0; i < nodes.get(next).size(); i++) {
                int n = nodes.get(next).get(i);
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.add(n);
                }
            }
        }
    }

}
