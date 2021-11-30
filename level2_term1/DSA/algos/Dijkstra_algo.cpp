#include <bits/stdc++.h>
using namespace std;

#define MAX_N 5
#define EMPTY -1
int edges[MAX_N][MAX_N];
int dist[MAX_N];

class Node
{
public:
    int v, distance;
    Node(int v, int distance)
    {
        this->v = v;
        this->distance = distance;
    }
    bool operator<(Node node) const //https://stackoverflow.com/questions/19237411/const-and-non-const-operator-overloading
    {
        return distance > node.distance;
    }
};

int shortest_path(Node current_node, Node end_node)
{
    priority_queue<Node> q;
    q.push(current_node);
    while (!q.empty())
    {
        Node temp_node = q.top();
        q.pop();
        for (int i = 0; i < MAX_N; i++)
        {
            if (edges[temp_node.v][i] + dist[temp_node.v] < dist[i])
            {
                dist[i] = edges[temp_node.v][i] + dist[temp_node.v];
                q.push(Node(i, dist[i]));
            }
        }
    }
}
int main()
{
    priority_queue<Node> yo;
    for (int i = 0; i < MAX_N; i++)
    {
        yo.push(Node(i, i * i));
    }
    while (!yo.empty())
    {
        Node temp_node = yo.top();
        yo.pop();
        cout << temp_node.v << " " << temp_node.distance << endl;
    }
    return 0;
}