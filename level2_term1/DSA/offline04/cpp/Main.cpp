#include <iostream>
#include "Tree.h"
using namespace std;

void printMenu()
{
    cout << "select one option , type -1 to exit" << endl;
    cout << "1. Insert Item" << endl;
    cout << "2. Search Item" << endl;
    cout << "3. Get In Order Successor" << endl;
    cout << "4. Get In Order Predecessor" << endl;
    cout << "5. Delete item" << endl;
    cout << "6. Get item depth" << endl;
    cout << "7. Get Max Item" << endl;
    cout << "8. Get Min Item" << endl;
    cout << "9. Get Height" << endl;
    cout << "10. Print InOrder" << endl;
    cout << "11. Print PreOrder" << endl;
    cout << "12. Print PostOrder" << endl;
    cout << "13. Get Size" << endl;
}

int main(int argc, char const *argv[])
{
    Binary_node a;
    while (true)
    {
        printMenu();
        int option;
        int val = 0;
        cin >> option;
        switch (option)
        {
        case 1:
            cout << "Enter the value:" << endl;
            cin >> val;
            a.insertItem(val);
            break;
        case 2:
            cout << "Enter the value:" << endl;
            cin >> val;
            a.searchItem(val);
            break;

        case 3:
            cout << "Enter the value:" << endl;
            cin >> val;
            cout << "The inorder successor is of " << val << " is " << a.getInOrderSuccessor(val) << endl;
            break;
        case 4:
            cout << "Enter the value:" << endl;
            cin >> val;
            cout << "The inorder Predecessor is of " << val << " is " << a.getInOrderPredecessor(val) << endl;
            break;

        case 5:
            cout << "Enter the value:" << endl;
            cin >> val;
            a.deleteItem(&a, val);
            cout << "Successful" << endl;
            break;

        case 6:
            cout << "Enter the value:" << endl;
            cin >> val;
            cout << "The depth of " << val << "is " << a.getItemDepth(val) << endl;
            break;
        case 7:
            cout << "The Max Item is " << a.getMaxItem() << endl;
            break;

        case 8:
            cout << "The Min item is " << a.getMinItem() << endl;
            break;

        case 9:
            cout << " The Height of the tree is " << a.getHeight(&a) << endl;
            break;

        case 10:
            cout << "The inorder traversel of the tree is " << endl;
            a.printInOrder(&a);
            cout << endl;
            break;
        case 11:
            cout << "The Preorder traversel of the tree is " << endl;
            a.printPreOrder(&a);
            cout << endl;
            break;
        case 12:
            cout << "The Postorder traversel of the tree is " << endl;
            a.printPostOrder(&a);
            cout << endl;
            break;
        case 13:
            cout << "The size of the tree is " << a.getSize(&a) << endl;
            break;
        case -1:
            return 0;
        default:
            break;
        }
    }
}
