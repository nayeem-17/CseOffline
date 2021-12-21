#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
#include <fstream>
const int INF = 10e7;
using namespace std;

class Disjoint_Set
{
    vector<int> parent, rank;

public:
    Disjoint_Set(int n)
    {
        parent.resize(n);
        rank.resize(n);
    }

    void make_Set(int v)
    {
        parent[v] = v;
        rank[v] = 0;
    }

    void union_Set(int a, int b)
    {
        a = find(a);
        b = find(b);
        if (a != b)
        {
            if (rank[a] < rank[b])
            {
                swap(a, b);
            }
            parent[b] = a;
            if (rank[a] == rank[b])
            {
                rank[a]++;
            }
        }
    }

    int find(int i)
    {
        if (parent[i] == i)
            return i;
        else
        {
            int result = find(parent[i]);
            parent[i] = result;
            return result;
        }
    }
};

class Edge
{
    int source;
    int destination;
    double weight;

public:
    Edge()
    {
        source = -1;
        destination = -1;
        weight = 0.0;
    }
    Edge(int s, int d, double w)
    {
        source = s;
        destination = d;
        weight = w;
    }
    double getWeight()
    {
        return weight;
    }
    int getDest()
    {
        return destination;
    }
    int getSource()
    {
        return source;
    }
};

class Result
{
public:
    int parent;
    double weight;
};

class cmp
{
public:
    bool operator()(pair<double, int> e1, pair<double, int> e2)
    {
        return e1.first > e2.first;
    }
};

class Graph
{
    int vertices;
    int edges;
    vector<Edge> adjP[10000];
    vector<Edge> adjK;

public:
    Graph(int v, int e)
    {
        vertices = v;
        edges = e;
    }

    void addEdge(int s, int d, double w)
    {
        Edge edge(s, d, w);
        adjK.push_back(edge);
        adjP[s].push_back(edge);
        // Edge eee(d, s, w);
        // adjP[d].push_back(eee);
    }
    void bellmanford(int s, int d)
    {
        vector<double> cost(vertices, INF);
        vector<int> parents(vertices, -1);
        cost[s] = 0;
        for (int i = 0; i < vertices; i++)
        {
            for (int j = 0; j < adjK.size(); j++)
            {
                Edge e = adjK.at(j);

                if (cost[e.getDest()] > cost[e.getSource()] + e.getWeight())
                {
                    cost[e.getDest()] = cost[e.getSource()] + e.getWeight();
                    parents[e.getDest()] = e.getSource();
                }
            }
        }
        for (int j = 0; j < adjK.size(); j++)
        {
            Edge e = adjK.at(j);

            if (cost[e.getDest()] > cost[e.getSource()] + e.getWeight())
            {
                cout << "The graph contains a negative cycle" << endl;
                return;
            }
        }
    }
    void prim()
    {
        priority_queue<pair<double, int>, vector<pair<double, int>>, cmp> pq;
        double key[vertices];
        for (int i = 0; i < vertices; i++)
        {
            key[i] = INF * 1.0;
        }

        bool visited[vertices] = {false};
        Result r[vertices];

        key[0] = 0.0;
        r[0].parent = -1;
        pair<double, int> p0(key[0], 0);

        pq.push(p0);

        while (!pq.empty())
        {
            pair<double, int> extract = pq.top();
            pq.pop();
            int cur = extract.second;
            visited[cur] = true;

            for (int i = 0; i < adjP[cur].size(); i++)
            {
                Edge v = adjP[cur].at(i);
                if (!visited[v.getDest()])
                {
                    int destination = v.getDest();
                    double weight = v.getWeight();

                    if (key[destination] > weight)
                    {
                        key[destination] = weight;
                        pair<double, int> n(key[destination], destination);
                        pq.push(n);
                        r[destination].parent = cur;
                        r[destination].weight = weight;
                    }
                }
            }
        }
        printPrim(r);
    }

    void printPrim(Result r[])
    {

        double total = 0.0;
        for (int i = 1; i < vertices; i++)
        {
            total += r[i].weight;
        }
        cout << "Cost of the minimum spanning tree: " << total << endl;
        cout << "List of edges selected by Prim: {";

        for (int i = 1; i < vertices; i++)
        {
            cout << "(" << i << "," << r[i].parent << ")";
            if (i != vertices - 1)
                cout << ", ";
            else
                cout << "}" << endl;
        }
    }
    static bool comp(Edge &e1, Edge &e2)
    {
        return e1.getWeight() < e2.getWeight();
    }
    void kruskal()
    {
        Edge r[vertices];
        Disjoint_Set d(vertices);
        for (int i = 0; i < vertices; i++)
        {
            d.make_Set(i);
        }

        sort(adjK.begin(), adjK.end(), comp);
        int j = 0;
        for (int i = 0; i < edges; i++)
        {
            Edge e = adjK[i];
            if (d.find(e.getSource()) != d.find(e.getDest()))
            {
                r[j++] = e;
                d.union_Set(e.getSource(), e.getDest());
            }
        }
        printKruskal(r);
    }

    void printKruskal(Edge r[])
    {
        cout << "List of edges selected by Kruskal: {";
        double total = 0.0;

        for (int i = 0; i < vertices - 1; i++)
        {
            Edge e = r[i];
            cout << "(" << e.getSource() << "," << e.getDest() << ")";
            if (i != vertices - 2)
                cout << ", ";
            else
                cout << "}" << endl;
        }
    }
};

int main()
{
    ifstream myfile("mst.in");
    int v, e;
    myfile >> v >> e;
    int s, d;
    double w;
    Graph g(v, e);
    for (int i = 0; i < e; i++)
    {
        myfile >> s >> d >> w;
        g.addEdge(s, d, w);
    }
    g.prim();
    g.kruskal();
}