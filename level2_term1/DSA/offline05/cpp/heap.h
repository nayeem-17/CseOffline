#include <iostream>
#include <vector>

using namespace std;

class Heap
{
private:
    int heap_size;
    int heap_capacity;
    int *heap_arr;

public:
    Heap(int);
    void insert(int);
    int size();
    int getMax();
    void deleteKey();
    void max_heapify(int);
    void print();
    ~Heap();
};

Heap::Heap(int size)
{
    heap_arr = new int[size];
    heap_capacity = size;
    heap_size = 0;
}
void Heap::insert(int element)
{
    if (this->heap_size == this->heap_capacity)
        return;
    this->heap_arr[this->heap_size++] = element;
    int indx = this->heap_size - 1;
    while (true)
    {
        if (indx == 0)
            break;
        else if (this->heap_arr[indx / 2] < this->heap_arr[indx])
        {
            int temp = heap_arr[indx];
            heap_arr[indx] = this->heap_arr[indx / 2];
            heap_arr[indx / 2] = temp;
            indx /= 2;
        }
        else
        {
            break;
        }
    }
}
void Heap::deleteKey()
{
    this->heap_size--;
    this->heap_arr[0] = this->heap_arr[this->heap_size];
    this->heap_arr[this->heap_size] = -1;
    this->max_heapify(1);
}
int Heap::getMax()
{
    return this->heap_arr[0];
}
int Heap::size()
{
    return heap_size;
}

void Heap::max_heapify(int index)
{
    int left = index * 2;
    int right = index * 2 + 1;

    if ((index - 1) * 2 < this->heap_size)
    {
        int leftnode = this->heap_arr[left - 1];
        int rightNode = this->heap_arr[right - 1];
        int currentNode = this->heap_arr[index - 1];
        if (leftnode > currentNode || rightNode > currentNode)
        {
            bool isRight = this->heap_arr[left - 1] < this->heap_arr[right - 1];
            if (isRight)
            {
                int temp = heap_arr[index - 1];
                heap_arr[index - 1] = this->heap_arr[right - 1];
                heap_arr[right - 1] = temp;
                this->max_heapify(right);
            }
            else
            {
                int temp = heap_arr[index - 1];
                heap_arr[index - 1] = this->heap_arr[left - 1];
                heap_arr[left - 1] = temp;
                this->max_heapify(left);
            }
        }

        else
            return;
    }
    else
    {
        return;
    }
}

Heap::~Heap()
{
    free(heap_arr);
}

void Heap::print()
{
    cout << "The heap is " << endl;
    for (int i = 0; i < heap_size; i++)
    {
        cout << this->heap_arr[i] << " ";
    }
    cout << endl;
}

void heapsort(vector<int> &v)
{
    int size = v.size();
    Heap heap(size);
    for (int i = 0; i < size; i++)
    {
        heap.insert(v.back());
        v.pop_back();
    }
    for (int i = 0; i < size; i++)
    {
        v[i] = heap.getMax();
        heap.deleteKey();
    }
}