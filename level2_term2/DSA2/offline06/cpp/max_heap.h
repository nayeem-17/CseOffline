#include "node.h"
#include "binomial_tree.h"

class MaxHeap
{
    vector<BinomialTree> heap;
    int size;

public:
    MaxHeap() { this->size = 0; }
    void setRoot(int);
    void merge(MaxHeap &);
    int findMax();
    int extractMax();
    void deleteMax();
    void insert(int);
    void increase_Key(int, int);
    void print();
    void addBTree(BinomialTree *tree) { this->heap.push_back(*tree); };
};
void MaxHeap::insert(int value)
{
    MaxHeap h;
    h.setRoot(value);
    this->merge(h);
    cout << "Inserted " << value << endl;
}
void MaxHeap::setRoot(int value)
{
    BinomialTree *tree = new BinomialTree(value);
    this->heap.push_back(*tree);
    this->size = 1;
    // delete tree;
}
void MaxHeap::merge(MaxHeap &h)
{
    if (this->size == 0 && h.size != 0)
    {
        *this = h;
        return;
    }
    // things to do
    int this_count = 0;
    int i = 0;
    BinomialTree *carry = nullptr;
    while (true)
    {
        if (i >= h.size)
        {
            if (carry == nullptr)
                break;
            else
            {
                this->heap[this_count] = *carry;
                this->size++;
                break;
            }
        }
        int current_heap_count = h.heap[i].getOrder();
        // cout << h.heap.size() << endl;
        if (current_heap_count == this->heap[this_count].getOrder())
        {
            if (carry == nullptr)
            {
                carry = h.heap[i].merge(this->heap[this_count]);
                this->heap.erase(this->heap.begin() + this_count);
                this->size--;
            }
            else
            {
                carry = carry->merge(h.heap[i]);
            }
            i++;
            this_count++;
        }
        else
        {
            if (carry != nullptr)
            {
                if (this->heap[this_count].getOrder() == carry->getOrder())
                {
                    this->heap[this_count].merge(*carry);
                }
                else if (h.heap[this_count].getOrder() == carry->getOrder())
                {
                    this->heap.insert(this->heap.begin() + this_count, *h.heap[i++].merge(*carry));
                    this->size++;
                }
                carry = nullptr;
            }
            else
            {
                this->heap[this_count] = h.heap[i++];
            }
            this_count++;
        }
    }
    cout << "merged" << endl;
}

int MaxHeap::findMax()
{
    int max = INT8_MIN;
    for (int i = 0; i < this->size; i++)
        if (max < this->heap[i].getMaxValue())
            max = this->heap[i].getMaxValue();

    return max;
}
int MaxHeap::extractMax()
{
    this->deleteMax();
    return this->findMax();
}
void MaxHeap::deleteMax()
{
    int max = INT8_MIN;
    int index = 0;
    for (int i = 0; i < this->size; i++)
        if (max < this->heap[i].getMaxValue())
        {
            max = this->heap[i].getMaxValue();
            index = i;
        }
    // have to delete this->heap[index].getMaxValue();
    BinomialTree *temp;
    temp = &this->heap[index];
    this->heap.erase(this->heap.begin() + index);
    MaxHeap m;
    for (int i = 0; i < temp->getRoot()->getChildrenCount(); i++)
    {
        BinomialTree tree(&temp->getRoot()->getChildren()[i]);
        m.addBTree(&tree);
    }
    this->merge(m);
}

void MaxHeap::increase_Key(int prev_value, int new_value)
{
    for (int i = 0; i < heap.size(); i++)
    {
        if (this->heap[i].replaceValue(prev_value, new_value))
            break;
    }
    cout << "Increased " << prev_value << ". The updated value is " << new_value << "." << endl;
}
void MaxHeap::print()
{
    std::cout << "Printing Binomial Heap..." << std::endl;
    std::cout << "—-------------------------" << std::endl;
    for (int i = 0; i < this->size; i++)
    {
        std::cout << "Binomial Tree, B" << this->heap[i].getOrder() << std::endl;
        this->heap[i].print();
    }
}