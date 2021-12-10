package Template;

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

    public boolean isBipartite() {
        int[] colors = new int[length];
        int[] visited = new int[length];
        int currentColor = 0;
        visited[0] = 1;
        colors[0] = currentColor;
        return isBipartite(0, currentColor, visited, colors);
    }

    private boolean isBipartite(int start, int currentColor, int[] visited, int[] colors) {
        visited[start] = 1;
        int nextColor = (currentColor == 0) ? 1 : 0;
        for (int i = 0; i < nodes.get(start).size(); i++) {
            int next = nodes.get(start).get(i);
            if (visited[next] == 0) {
                if (isBipartite(next, nextColor, visited, colors) == false)
                    return false;
            } else if (colors[next] == currentColor)
                return false;
        }
        return true;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[length];
        return hasCycle(0, visited, -1);
    }

    private boolean hasCycle(int start, boolean[] visited, int parent) {
        visited[start] = true;
        for (int i = 0; i < nodes.get(start).size(); i++) {
            int next = nodes.get(start).get(i);
            if (visited[next] == false) {
                if (hasCycle(next, visited, parent))
                    return true;
            } else if (next != parent)
                return true;

        }
        return false;
    }

    // topological sort
    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<Integer>();
        boolean[] visited = new boolean[length];
        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                topologicalSort(i, visited, result);
            }
        }
        return result;
    }

    private void topologicalSort(int i, boolean[] visited, List<Integer> result) {
        visited[i] = true;
        for (int j = 0; j < nodes.get(i).size(); j++) {
            int next = nodes.get(i).get(j);
            if (!visited[next]) {
                topologicalSort(next, visited, result);
            }
        }
        result.add(i);
    }

    // strongly connected components
    public List<List<Integer>> stronglyConnectedComponents() {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int[] visited = new int[length];
        for (int i = 0; i < length; i++) {
            if (visited[i] == 0) {
                List<Integer> component = new ArrayList<Integer>();
                stronglyConnectedComponents(i, visited, component);
                result.add(component);
            }
        }
        return result;
    }

    private void stronglyConnectedComponents(int i, int[] visited, List<Integer> component) {
        visited[i] = 1;
        component.add(i);
        for (int j = 0; j < nodes.get(i).size(); j++) {
            int next = nodes.get(i).get(j);
            if (visited[next] == 0) {
                stronglyConnectedComponents(next, visited, component);
            }
        }
    }
}
