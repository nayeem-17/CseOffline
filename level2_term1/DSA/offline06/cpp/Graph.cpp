#include <iostream>
#include <vector>
using namespace std;

class Graph
{
    int len;
    vector<vector<int>> nodes;

public:
    Graph(int len = 0)
    {
        this->len = len;
    }
};