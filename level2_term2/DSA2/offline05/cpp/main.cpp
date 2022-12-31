#include <iostream>
using namespace std;
#include "random.h"
#include "doublehashing.h"
#include "separatechaining.h"
#include "customprobing.h"

#define SIZE 10000

int main()
{
    DoubleHashing d_h_table;
    CustomProbing c_prob;
    SeparateChaining s_chain;

    int length = SIZE;
    string *keys = Random::generate_random(length, 7);
    for (int i = 0; i < length; i++)
    {
        s_chain.insert_key(keys[i]);
        d_h_table.insert_key(keys[i]);
        c_prob.insert_key(keys[i]);
    }
    float cs_prob = 0;
    float dh_prob = 0;
    float sc_prob = 0;
    for (int i = 0; i < length; i++)
    {
        dh_prob += d_h_table.search_key(keys[i]);
        sc_prob += s_chain.search_key(keys[i]);
        cs_prob += c_prob.search_key(keys[i]);
    }
    cout << "avg prob for separate chaining-> " << sc_prob / length << endl;
    cout << "collisions -> " << s_chain.collission << endl;
    cout << "avg prob for double hashing -> " << dh_prob / length << endl;
    cout << "collisions -> " << d_h_table.collission << endl;
    cout << "avg prob for custom probing -> " << cs_prob / length << endl;
    cout << "collisions -> " << c_prob.collission << endl;
    return 0;
}
