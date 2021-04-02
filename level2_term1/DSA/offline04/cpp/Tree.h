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
    void deleteItem(Binary_node *, int);
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
                // if leaf
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

int Binary_node::getInOrderPredecessor(int item)
{
    if (*this->data == item)
        return this->leftChild->getMaxItem();

    Binary_node *temp = this;
    while (true)
    {
        if (temp == nullptr)
            return INVALID_NUMBER;
        if (item > *temp->data)
        {
            temp = temp->rightChild;
        }
        else if (item < *temp->data)
            temp = temp->leftChild;
        else
        {
            if (temp->leftChild == nullptr && temp == temp->parent->rightChild)
                return *temp->parent->data;
            // if leaf
            if (temp->rightChild == nullptr && temp->leftChild == nullptr)
            {
                // if right leaf
                if (temp == temp->parent->rightChild)
                    return *temp->parent->data;
                // if left leaf
                if (temp == temp->parent->leftChild)
                {
                    while (true)
                    {
                        if (temp->parent == nullptr)
                            return INVALID_NUMBER;
                        if (temp->parent->rightChild == temp)
                            return *temp->parent->data;
                        temp = temp->parent;
                    }
                }
            }

            return temp->leftChild->getMaxItem();
        }
    }
}
void Binary_node::deleteItem(Binary_node *root, int item)
{
    if (*root->data > item)
        return deleteItem(root->leftChild, item);
    if (*root->data < item)
        return deleteItem(root->rightChild, item);
    else
    {
        if (root->leftChild == nullptr && root->rightChild == nullptr)
        {
            if (root->parent->leftChild == root)
                root->parent->leftChild = nullptr;
            else
                root->parent->rightChild = nullptr;
            delete root->data;
            delete root;
            return;
        }
        if (root->leftChild == nullptr)
        {
            root->parent->rightChild = root->rightChild;
            root->rightChild->parent = root->parent;
            delete root->data;
            delete root;
            return;
        }
        else if (root->rightChild == nullptr)
        {
            root->parent->leftChild = root->leftChild;
            root->leftChild->parent = root->parent;
            delete root->data;
            delete root;
            return;
        }
        else
        {
            int min = root->rightChild->getMinItem();
            *root->data = min;
            deleteItem(root->rightChild, min);
        }
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
        cout << *node->data << "   ";
        return;
    }
    printInOrder(node->leftChild);
    cout << *node->data << "   ";
    printInOrder(node->rightChild);
}
void Binary_node::printPostOrder(Binary_node *node)
{
    if (node == nullptr)
        return;
    if (node->rightChild == nullptr && node->leftChild == nullptr)
    {
        cout << *node->data << "   ";
        return;
    }

    printPostOrder(node->leftChild);
    printPostOrder(node->rightChild);
    cout << *node->data << "   ";
}

void Binary_node::printPreOrder(Binary_node *node)
{
    if (node == nullptr)
        return;
    if (node->rightChild == nullptr && node->leftChild == nullptr)
    {
        cout << *node->data << "   ";
        return;
    }

    cout << *node->data << "   ";
    printPreOrder(node->leftChild);
    printPreOrder(node->rightChild);
}
