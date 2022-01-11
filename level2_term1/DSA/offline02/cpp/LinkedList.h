#include <iostream>
using namespace std;

class LinkedList
{
private:
    /* data */
    int *value;
    LinkedList *prev;
    LinkedList *next;

public:
    LinkedList();
    LinkedList(int rootElement);
    int getLength();
    void append(int element);
    void insertafter(int index, int element);
    void insert(int index, int element);
    int search(int value);
    void insterAtFirst(int value);
    int get(int index);
    void remove(int element);
    void deletelist();
    void print();
};

LinkedList::LinkedList()
{
    prev = NULL;
    next = NULL;
    value = NULL;
}
LinkedList::LinkedList(int rootElement)
{
    prev = NULL;
    next = NULL;
    value = new int(rootElement);
}

int LinkedList::getLength()
{
    if (this->value == NULL)
        return 0;
    if (this->next == NULL)
        return 1;
    int len = 0;
    LinkedList *currentNode = this;
    while (currentNode != NULL)
    {
        /* code */
        len++;
        currentNode = currentNode->next;
    }
    return len;
}
void LinkedList::append(int element)
{
    LinkedList *currentNode = this;
    while (true)
    {
        if (currentNode->next != NULL)
            currentNode = currentNode->next;
        else
            break;
    }
    if (currentNode->value == NULL)
    {
        value = new int(element);
    }
    else
    {
        currentNode->next = new LinkedList();
        currentNode->next->value = new int(element);
        currentNode->next->prev = currentNode;
    }
}
void LinkedList::insertafter(int index, int element)
{
    LinkedList *currentNode = this;

    for (int i = 0; i < index; i++)
    {
        if (currentNode->next == NULL)
        {
            throw invalid_argument("index out of bound");
        }
        else
        {
            currentNode = currentNode->next;
        }
    }
    if (currentNode->next == NULL)
    {
        currentNode->next = new LinkedList(element);
        currentNode->next->prev = currentNode;
    }
    else
    {
        LinkedList *temp = currentNode->next;
        currentNode->next = new LinkedList(element);
        currentNode->next->prev = currentNode;
        currentNode->next->next = temp;
    }
}
void LinkedList::insert(int index, int element)
{
    LinkedList *currentNode = this;
    if (index == 0)
        insterAtFirst(element);
    else
    {
        for (int i = 0; i < index; i++)
        {
            if (currentNode->next == NULL)
            {
                throw invalid_argument("index out of bound");
            }
            else
            {
                currentNode = currentNode->next;
            }
        }
        if (currentNode->next == NULL)
        {
            currentNode->next = new LinkedList(*currentNode->value);
            currentNode->next->prev = currentNode;
            currentNode->value = new int(element);
        }
        else
        {
            LinkedList *temp = currentNode->next;
            currentNode->next = new LinkedList(*currentNode->value);
            currentNode->next->prev = currentNode;
            currentNode->value = new int(element);
            currentNode->next->next = temp;
        }
    }
}
void LinkedList::insterAtFirst(int element)
{
    LinkedList *temp = this->next;
    this->next = new LinkedList(*this->value);
    this->next->next = temp;
    this->value = new int(element);
    this->prev = NULL;
}
void LinkedList::print()
{
    LinkedList *currentNode = this;
    if (this->value == NULL)
    {
        cout << "The list is empty!" << endl;
        return;
    }
    while (currentNode != NULL)
    {
        /* code */
        cout << *currentNode->value << endl;
        currentNode = currentNode->next;
    }
}
void LinkedList::remove(int element)
{
    if (this->value == NULL)
        throw invalid_argument("No element in the list");
    LinkedList *currentNode = this;
    while (currentNode != NULL)
    {
        /* code */
        if (*currentNode->value == element)
        {
            currentNode->prev->next = currentNode->next;
            delete currentNode;
            return;
        }
        currentNode = currentNode->next;
    }
    cout << "element not found" << endl;
}
int LinkedList::get(int index)
{
    LinkedList *currentNode = this;
    int i = 0;
    while (currentNode != NULL)
    {
        /* code */
        if (i == index)
            return *currentNode->value;
        i++;
        currentNode = currentNode->next;
    }
    throw invalid_argument("index out of bound");
}
int LinkedList::search(int value)
{
    int index = 0;
    LinkedList *currentNode = this;
    while (currentNode != NULL)
    {
        /* code */
        if (*currentNode->value == value)
            return index;
        index++;
        currentNode = currentNode->next;
    }
    return -1;
}
void LinkedList::deletelist()
{
    LinkedList *currentNode = this;
    while (true)
    {
        /* code */
        currentNode = this->next;
        if (currentNode == NULL)
            break;
        this->next = this->next->next;
        delete currentNode;
    }
    this->value = NULL;
}

// int main(int argc, char const *argv[])
// {
//     LinkedList *node = new LinkedList(10);
//     node->append(15);
//     node->append(20);
//     node->insert(1, 12);
//     node->insertafter(3, 1010);
//     cout << node->getLength() << endl;
//     node->deletelist();
//     // cout << node->search(100) << node->search(1010) << endl;
//     node->print();
//     return 0;
// }
