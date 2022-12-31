#pragma once
#include <string>
#include <math.h>
#include <map>
#define TABLE_SIZE 40009

class Random
{
public:
    static string *generate_random(int, int);
    static unsigned int hash1(string);
    static unsigned int hash2(string);
    static unsigned int aux_hash(string);
    static unsigned int double_hash(string, int);
    static unsigned int custom_hash(string, int);
    static unsigned int hash(string key);
};

string *Random::generate_random(int array_size, int string_size)
{
    string *results = new string[array_size];
    map<string, int> map_result;
    char chars[] =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        "abcdefghijklmnopqrstuvwxyz";
    int j = 0;
    while (map_result.size() < array_size)
    {
        string tmp;
        tmp.reserve(string_size);

        for (int i = 0; i < string_size; ++i)
        {
            tmp += chars[rand() % (sizeof(chars) - 1)];
        }
        if (map_result[tmp] == 0)
        {
            map_result[tmp]++;
            results[j++] = tmp;
        }
        else
            cout << tmp << endl;
        // cout << tmp << " " << tmp.size() << endl;
    }
    // cout << map_result.size() << endl;
    return results;
}
unsigned int Random::hash(string key)
{
    return hash1(key);
}
unsigned int Random::hash1(string key)
{
    const int temp = 1783;
    unsigned int hash = 1;
    for (int i = 0; i < key.size(); i++)
        hash += (key[i] * (int)pow(temp, i));
    return hash;
}
unsigned int Random::hash2(string key)
{
    unsigned int hash = 101010113;
    int seed = 1783043;
    for (int i = 0; i < key.size(); ++i)
    {
        hash = ((hash << 5) ^ (hash >> 27)) ^ key[i];
        hash = (hash * seed) + key[i];
    }
    return hash;
}

unsigned int Random::aux_hash(string key)
{
    unsigned int hash = 101010113;
    int seed = 1783043;
    for (int i = 0; i < key.size(); ++i)
    {
        hash = ((hash << 7) ^ (hash >> 39)) ^ key[i];
        hash = (hash * seed) + key[i];
        hash += (key[i] * (int)pow(seed % hash, i));
        hash = (hash * seed) * (hash + seed) + key[i];
    }
    return hash;
}
unsigned int Random::double_hash(string key, int i)
{
    return hash(key) + i * aux_hash(key);
}
unsigned int Random::custom_hash(string key, int i)
{
    int C1 = 83;
    int C2 = 1783;
    return hash(key) + C1 * i * aux_hash(key) + C2 * i * i;
}
