package level2_term1.DSA.offline06.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    private ArrayList<ArrayList<Integer>> nodes;
    private int hiddenPieces[];
    private boolean visited[];
    private int length;

    public Graph(int n) {
        this.nodes = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            this.nodes.add(new ArrayList<Integer>());
        }
        this.visited = new boolean[n];
        this.hiddenPieces = new int[n];
        this.length = n;
    }

    /**
     * @param from the city connected to road
     * @param to   another city connected to road
     */
    public void addEdges(int from, int to) {
        this.nodes.get(from).add(to);
        this.nodes.get(to).add(from);
    }

    /**
     * @param hiddenPieces the hiddenPieces to set
     * @param city         at where the hiddenPieces are hidden
     */
    public void setHiddenPieces(int city, int hiddenPieces) {
        this.hiddenPieces[city] = hiddenPieces;
    }

    /**
     * @param startingCity from where the BFS will start
     * @param visitedCity
     */
    public void doBFS(int startingCity, boolean[] visitedCity) {
        visited[startingCity] = true;
        visitedCity[startingCity] = true;
        List<Integer> nonVisitedCity = new ArrayList<>();
        for (int city : nodes.get(startingCity)) {
            if (visited[city] == false) {
                visited[city] = true;
                visitedCity[city] = true;
                nonVisitedCity.add(city);
            }
        }
        for (int city : nonVisitedCity) {
            doBFS(city, visitedCity);
        }
    }

    /**
     * @param startingCity from where the DFS will start
     * @return the number of collected pieces
     */
    public int doDFS(int startingCity) {

        int count = hiddenPieces[startingCity];
        visited[startingCity] = true;
        for (int city : nodes.get(startingCity)) {
            if (visited[city] == false)
                count += doDFS(city);
        }
        return count;
    }

    /**
     * @return the total hiddenPieces
     */
    public int getTotalHiddenPieces() {
        int count = 0;
        for (int c : this.hiddenPieces)
            count += c;
        return count;
    }

    /**
     * @return the hiddenPieces
     */
    public int[] getHiddenPieces() {
        return hiddenPieces;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Graph [hiddenPieces=" + Arrays.toString(hiddenPieces) + ", nodes=" + nodes + "]";
    }

}
