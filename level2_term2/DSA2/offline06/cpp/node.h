#pragma once
#include <iterator>
class Node
{
    int value;
    int children_count;
    Node *childern;

public:
    Node *parent;
    Node()
    {
        this->parent = nullptr;
        this->childern = nullptr;
        this->children_count = 0;
    }
    Node(int value)
    {
        this->value = value;
        this->parent = nullptr;
        this->childern = nullptr;
        this->children_count = 0;
    }
    // ~Node()
    // {
    //     std::cout << childern << std::endl;
    //     if (this->childern != nullptr)
    //         delete this->childern;
    //     std::cout << childern << std::endl;
    //     this->parent = nullptr;
    // }
    int getValue() { return this->value; }
    void setValue(int value) { this->value = value; }
    int getChildrenCount() { return this->children_count; }
    Node *getChildren() { return this->childern; }
    void addChild(Node *);
    bool replaceValue(int, int);
    void heapify();
};

void Node::addChild(Node *node)
{
    node->parent = this;
    Node *temp = this->childern;
    this->childern = new Node[this->children_count];
    std::copy(temp, temp + this->children_count, this->childern);
    this->childern[this->children_count] = *node;
    this->children_count++;
}
bool Node::replaceValue(int value, int new_value)
{

    if (this->value == value)
    {
        this->value = new_value;
        this->heapify();
        return true;
    }

    for (int i = 0; i < children_count; i++)
    {
        bool result = this->childern[i].replaceValue(value, new_value);
        if (result == false)
            continue;
        return result;
    }

    return false;
}
void Node::heapify()
{
    if (this->parent == nullptr)
        return;
    if (this->parent->value < this->value)
    {
        int temp = this->parent->value;
        this->parent->setValue(this->value);
        this->setValue(temp);
        this->parent->heapify();
    }
}