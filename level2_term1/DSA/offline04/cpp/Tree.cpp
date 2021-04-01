#include <iostream>
using namespace std;

#define INVALID_NUMBER -1

class Binary_node
{
private:
    int *data;
    Binary_node *leftChild;
    Binary_node *rightChild;
    Binary_node *parent;

public:
    Binary_node();
    void insertItem(int);
    void searchItem(int);
    int getInOrderSuccessor(int);
    int getInOrderPredecessor(int);
    bool deleteItem(Binary_node *, int);
    int getItemDepth(int);
    int getMaxItem();
    int getMinItem();
    int getHeight(Binary_node *);
    void printInOrder(Binary_node *);
    void printPostOrder(Binary_node *);
    void printPreOrder(Binary_node *);
    int getSize(Binary_node *);
};

void Binary_node::searchItem(int item)
{
    if (this->data == nullptr)
    {
        data = new int(item);
    }
    else if (*this->data == item)
    {
        cout << item << " is found" << endl;
        return;
    }
    else
    {
        Binary_node *temp = this;
        while (true)
        {
            if (*temp->data == item)
            {
                cout << item << " is found" << endl;
                return;
            }

            if (item > *temp->data)
            {
                if (temp->rightChild == nullptr)
                    break;
                else
                {
                    temp = temp->rightChild;
                }
            }
            else if (item < *temp->data)
            {
                if (temp->leftChild == nullptr)
                    break;
                else
                {
                    temp = temp->leftChild;
                }
            }
        }
    }
    cout << item << " is not found" << endl;
}
int Binary_node::getMaxItem()
{
    Binary_node *temp = this;
    while (true)
    {
        if (temp->rightChild == nullptr)
            return *temp->data;
        temp = temp->rightChild;
    }
}
int Binary_node::getMinItem()
{
    Binary_node *temp = this;
    while (true)
    {
        if (temp->leftChild == nullptr)
            return *temp->data;
        temp = temp->leftChild;
    }
}
void Binary_node::insertItem(int item)
{
    // for root node
    if (this->data == nullptr)
    {
        data = new int(item);
    }
    else
    {
        Binary_node *temp = this;
        while (true)
        {
            if (item > *temp->data)
            {
                if (temp->rightChild == nullptr)
                {
                    temp->rightChild = new Binary_node();
                    temp->rightChild->parent = temp;
                    temp->rightChild->data = new int(item);
                    break;
                }
                else
                {
                    temp = temp->rightChild;
                }
            }
            else if (item < *temp->data)
            {
                if (temp->leftChild == nullptr)
                {
                    temp->leftChild = new Binary_node();
                    temp->leftChild->parent = temp;
                    temp->leftChild->data = new int(item);
                    break;
                }
                else
                {
                    temp = temp->leftChild;
                }
            }
        }
    }
}
Binary_node::Binary_node()
{
    data = nullptr;
    leftChild = rightChild = parent = nullptr;
}

int Binary_node::getInOrderSuccessor(int item)
{

    Binary_node *temp = this;

    while (true)
    {

        if (temp == nullptr)
            return INVALID_NUMBER;
        if (*temp->data == item)
        {

            if (temp->parent != nullptr)
            {
                if (temp->rightChild == nullptr && temp == temp->parent->leftChild)
                    return *temp->parent->data;

                if (temp->rightChild == nullptr && temp->leftChild == nullptr)
                {
                    // if left leaf
                    if (temp == temp->parent->leftChild)
                        return *temp->parent->data;
                    // if right leaf
                    if (temp == temp->parent->rightChild)
                    {
                        while (true)
                        {
                            if (temp == nullptr)
                                return INVALID_NUMBER;
                            if (temp->parent->leftChild == temp)
                                return *temp->parent->data;
                            temp = temp->parent;
                        }
                    }
                }

                // if no right child
                if (temp->rightChild == nullptr)
                {
                    while (true)
                    {
                        if (temp->parent == nullptr)
                            return INVALID_NUMBER;
                        if (temp->parent->leftChild == temp)
                            return *temp->parent->data;
                        temp = temp->parent;
                    }
                }
            }

            if (temp->rightChild != nullptr)
                temp = temp->rightChild;
            while (true)
            {
                if (temp->leftChild == nullptr)
                    return *temp->data;
                temp = temp->leftChild;
            }
        }
        if (item > *temp->data)
        {
            temp = temp->rightChild;
        }
        else
            temp = temp->leftChild;
    }
}

int Binary_node::getInOrderPredecessor(int)
{
}
bool Binary_node::deleteItem(Binary_node *root, int item)
{
    if (*root->data > item)
        return deleteItem(root->leftChild, item);
    if (*root->data < item)
        return deleteItem(root->rightChild, item);
    else
    {
    }
}
int Binary_node::getItemDepth(int item)
{
    Binary_node *temp = this;
    int count = 0;
    while (true)
    {
        if (temp == nullptr)
            return INVALID_NUMBER;
        if (*temp->data == item)
            break;
        if (*temp->data > item)
        {
            count++;
            temp = temp->leftChild;
        }
        else
        {
            count++;
            temp = temp->rightChild;
        }
    }
    return count;
}
int Binary_node::getHeight(Binary_node *node)
{
    if (node == nullptr)
        return 0;
    int leftMax = getHeight(node->leftChild);
    int rightMax = getHeight(node->rightChild);
    // cout << leftMax << " " << rightMax << endl;
    return 1 + (leftMax < rightMax ? rightMax : leftMax);
}

int Binary_node::getSize(Binary_node *binary_node)
{
    if (binary_node == nullptr)
        return 0;
    if (binary_node->rightChild == nullptr)
        return 1 + getSize(binary_node->leftChild);
    if (binary_node->leftChild == nullptr)
        return 1 + getSize(binary_node->rightChild);
    return 1 + getSize(binary_node->leftChild) + getSize(binary_node->rightChild);
}

void Binary_node::printInOrder(Binary_node *node)
{
    if (node == nullptr)
        return;
    if (node->rightChild == nullptr && node->leftChild == nullptr)
    {
        cout << *node->data << endl;
        return;
    }

    printInOrder(node->leftChild);
    cout << *node->data << endl;
    printInOrder(node->rightChild);
}
void Binary_node::printPostOrder(Binary_node *node)
{
    if (node == nullptr)
        return;
    if (node->rightChild == nullptr && node->leftChild == nullptr)
    {
        cout << *node->data << endl;
        return;
    }

    printPostOrder(node->leftChild);
    printPostOrder(node->rightChild);
    cout << *node->data << endl;
}

void Binary_node::printPreOrder(Binary_node *node)
{
    if (node == nullptr)
        return;
    if (node->rightChild == nullptr && node->leftChild == nullptr)
    {
        cout << *node->data << endl;
        return;
    }

    cout << *node->data << endl;
    printPreOrder(node->leftChild);
    printPreOrder(node->rightChild);
}

int main(int argc, char const *argv[])
{
    int arr[] = {33, 50, 51, 52, 53, 54, 55, 56, 60, 100, 110, 150};
    Binary_node a, *b;
    a.insertItem(100);
    a.insertItem(50);
    a.insertItem(150);
    a.insertItem(60);
    a.insertItem(55);
    a.insertItem(110);
    a.insertItem(33);
    a.insertItem(56);
    a.insertItem(54);
    a.insertItem(53);
    a.insertItem(52);
    a.insertItem(51);
    cout << a.getSize(&a) << endl;
    cout << a.getMaxItem() << endl;
    cout << a.getMinItem() << endl;
    cout << "````````````````````````````````````````````````````````````````````````````````````" << endl;
    a.printInOrder(&a);
    // cout << "````````````````````````````````````````````````````````````````````````````````````" << endl;
    // a.printPostOrder(&a);
    for (int i : arr)
        cout << "Inorder successor of " << i << " is " << a.getInOrderSuccessor(i) << endl;
    cout << "Height " << a.getHeight(&a) << endl;

    cout << "DEPTH of 100 is " << a.getItemDepth(100) << endl;
}
