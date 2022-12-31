#include <vector>
#include "node.h"

using namespace std;

class BinomialTree
{
    Node *root;
    int order;

public:
    BinomialTree(int val)
    {
        this->root = new Node(val);
        this->order = 0;
    }
    BinomialTree(Node *root) { this->root = root; }
    Node *getRoot() { return this->root; }
    bool replaceValue(int, int);
    int getOrder() { return this->root->getChildrenCount(); }
    int getMaxValue() { return this->root->getValue(); }
    BinomialTree *merge(BinomialTree &);
    void print();
};

BinomialTree *BinomialTree::merge(BinomialTree &tree)
{
    cout << this->root->getValue() << " " << tree.root->getValue() << endl;
    if (this->root->getValue() > tree.root->getValue())
    {
        this->root->addChild(tree.root);
        tree.root->parent = this->root;
        this->order++;
        return this;
    }
    else
    {
        tree.root->addChild(this->root);
        this->root->parent = tree.root;
        tree.order++;
        return &tree;
    }
}
bool BinomialTree::replaceValue(int value, int new_value)
{
    this->root->replaceValue(value, new_value);
}

void BinomialTree::print()
{
    vector<Node> nodes[this->order];
    nodes[0].push_back(*this->root);
    for (int i = 1; i < this->order; i++)
    {
        for (int j = 0; j < nodes[i - 1].size(); j++)
        {
            Node *node = nodes[i - 1].at(i).getChildren();
            for (int k = 0; k < node->getChildrenCount(); k++)
            {
                nodes[i].push_back(node[k]);
            }
        }
    }
    for (int i = 0; i < this->order; i++)
    {
        cout << "Level " << i << " :";
        for (int j = 0; j < nodes[i].size(); j++)
        {
            cout << nodes[i][j].getValue() << " ";
        }
        cout << endl;
    }
}