#include <iostream>
#include <fstream>
#include "max_heap.h"

using namespace std;

int main(int argc, char const *argv[])
{
    ifstream file;
    file.open("input.txt");
    MaxHeap max_heap;
    string command;
    while (!file.eof())
    {
        file >> command;
        if (command == "INC")
        {
            int prev_node;
            int new_node;
            cin >> prev_node >> new_node;
            max_heap.increase_Key(prev_node, new_node);
        }
        else if (command == "INS")
        {
            int node;
            file >> node;
            max_heap.insert(node);
            // max_heap.print();
        }
        else if (command == "FIN")
        {
            int max = max_heap.findMax();
            cout << "FindMax returned " << max << endl;
        }
        else if (command == "PRI")
        {
            max_heap.print();
        }
        else if (command == "EXT")
        {
            int max = max_heap.extractMax();
            cout << "FindMax returned " << max << endl;
        }
    }

    return 0;
}
