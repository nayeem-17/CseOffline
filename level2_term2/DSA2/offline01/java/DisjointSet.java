package offline01.java;

import java.util.Arrays;

public class DisjointSet {
    int[] parent;
    int[] representative;

    public DisjointSet(int n) {
        this.parent = new int[n];
        this.representative = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }
    }

    void union(int a, int b) {
        int u = findRepresentative(a);
        int v = findRepresentative(b);
        if (this.findRepresentative(u) != this.findRepresentative(v))
            parent[u] = v;
    }

    int findRepresentative(int u) {
        if (parent[u] == u)
            return u;
        return findRepresentative(parent[u]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "DisjointSet [parent=" + Arrays.toString(parent) + "]";
    }

}
// find representative