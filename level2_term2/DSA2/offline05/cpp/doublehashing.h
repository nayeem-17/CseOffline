#include <bits/stdc++.h>
using namespace std;
#include "point.h"
#include "random.h"
// #define TABLE_SIZE 10000 + 7
class DoubleHashing
{
    Point *table;

public:
    DoubleHashing()
    {
        this->table = new Point[TABLE_SIZE];
    }
    unsigned int collission = 0;
    void insert_key(string);
    int search_key(string);
    void delete_key(string);
    void print();
};

void DoubleHashing::delete_key(string key)
{
    for (int i = 0; i < TABLE_SIZE; i++)
    {
        int value = (Random::double_hash(key, i)) % TABLE_SIZE;
        if (table[value].key == key)
        {
            table[value].setKey("");
            table[value].setValue(-1);
            break;
        }
    }
}

void DoubleHashing::insert_key(string key)
{
    for (int i = 0; i < TABLE_SIZE; i++)
    {
        int value = (Random::double_hash(key, i)) % TABLE_SIZE;
        // cout << value << endl;
        if (table[value].value == -1)
        {
            table[value].value = value;
            table[value].key = key;
            break;
        }
        this->collission++;
    }
}
int DoubleHashing::search_key(string key)
{
    int prob = 0;
    for (int i = 0; i < TABLE_SIZE; i++)
    {
        prob++;
        int value = (Random::double_hash(key, i)) % TABLE_SIZE;
        if (table[value].key == key)
            break;
    }
    return prob;
}
void DoubleHashing::print()
{
    for (int i = 0; i < TABLE_SIZE; i++)
        cout << i << table[i].toString() << endl;
}