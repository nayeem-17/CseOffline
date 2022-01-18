#include <bits/stdc++.h>
using namespace std;
#include "point.h"
#include "random.h"

class SeparateChaining
{
public:
    vector<Point> *table;
    unsigned int collission = 0;
    SeparateChaining()
    {
        this->table = new vector<Point>[TABLE_SIZE];
    }
    void insert_key(string);
    int search_key(string);
    void delete_key(string);
    void print();
};

void SeparateChaining::insert_key(string key)
{
    int value = (Random::hash(key)) % TABLE_SIZE;
    if (table[value].size() != 0)
    {
        this->collission++;
        // cout << key << " " << value << endl;
    }
    table[value].push_back(Point(key, value));
    // cout << table[value][0].key << endl;
}

void SeparateChaining::delete_key(string key)
{
    int value = (Random::hash(key)) % TABLE_SIZE;
    if (table[value].size() == 0)
        cout << "key does not exist" << endl;
    for (int i = 0; i < table[value].size(); i++)
    {
        if (table[value][i].key == key)
        {
            table[value].erase(table[value].begin() + i);
            break;
        }
    }
}

int SeparateChaining::search_key(string key)
{
    int prob = 0;
    int value = (Random::hash(key)) % TABLE_SIZE;
    if (table[value].size() == 0)
        cout << "key does not exist" << endl;
    for (int i = 0; i < table[value].size(); i++)
    {
        prob++;
        if (table[value][i].key == key)
        {
            break;
        }
    }
    return prob;
}

void SeparateChaining::print()
{
    for (int i = 0; i < TABLE_SIZE; i++)
    {
        cout << i << " -> ";
        for (int j = 0; j < table[i].size(); j++)
        {
            cout << table[i][j].toString() << "     ";
        }
        cout << endl;
    }
}