#include <iostream>
#include "LinkedList.h"
using namespace std;

// int main(int argc, char const *argv[])
// {
//     int N;
//     int *reflextimes = new int[N];
//     cin >> N;
//     while (N--)
//     {
//         int temp;
//         cin >> temp;
//     }

//     return 0;
// }
int main(int argc, char const *argv[])
{
    LinkedList *node = new LinkedList(10);
    node->append(15);
    node->append(20);
    node->insert(1, 12);
    node->insertafter(3, 1010);
    cout << node->getLength() << endl;
    node->deletelist();
    // cout << node->search(100) << node->search(1010) << endl;
    node->print();
    return 0;
}
