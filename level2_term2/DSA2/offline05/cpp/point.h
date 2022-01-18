#pragma once
class Point
{
public:
    Point()
    {
        key = "";
        value = -1;
    }
    std::string key;
    int value;
    Point(std::string key, int value) : key(key), value(value) {}
    void setKey(std::string key) { this->key = key; }
    void setValue(int value) { this->value = value; }
    string toString()
    {
        return "(" + key + "," + std::to_string(value) + ")";
    }
};