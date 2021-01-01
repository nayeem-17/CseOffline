#include <iostream>
#include <string.h>
#include <cmath>
using namespace std;
char *toString(int num)
{
    int len = (int)ceil(log10(num)) + 1;
    char *p = new char[len];
    int *temp = new int[len];
    for (int i = 0; i < len; i++)
    {
        temp[i] = num % 10;
        num = num / 10;
    }
    for (int i = 0; i < len; i++)
    {
        p[i] = '0' + temp[len - i - 1];
    }
    p[len] = '\0';
    if (p[0] == '0')
        p++;
    return p;
}

class StringMath
{
    char *p;

public:
    StringMath();
    StringMath(char *str);
    StringMath &operator=(const StringMath &obj);
    StringMath operator+(StringMath obj);
    StringMath(const StringMath &obj);
    StringMath operator+(int n);
    bool operator>(StringMath);
    friend StringMath operator+(int n, StringMath obj);
    friend bool operator>(StringMath obj, int n);
    void operator++(int unused);
    void show();
    char *add(char *, char *);
    ~StringMath();
};
StringMath::StringMath()
{
    p = NULL;
}
StringMath::~StringMath()
{
    delete[] p;
}
StringMath::StringMath(char *str)
{
    int len = strlen(str);
    this->p = new char[len];
    for (int i = 0; i < len; i++)
    {
        p[i] = str[i];
    }
    p[len] = '\0';
}
StringMath::StringMath(const StringMath &obj)
{
    // cout << "copy constructor calling" << endl;
    int len = strlen(obj.p);
    p = new char[len];
    for (int i = 0; i < len; i++)
    {
        p[i] = obj.p[i];
    }
    p[len] = '\0';
}
StringMath &StringMath::operator=(const StringMath &obj)
{
    // cout << "assignment overloaded method calling" << endl;
    if (p != NULL)
        delete[] p;
    int len = strlen(obj.p);
    p = new char[len];
    for (int i = 0; i < len; i++)
    {
        p[i] = obj.p[i];
    }
    p[len] = '\0';
    return *this;
}
void StringMath::operator++(int unused)
{
    char *num = this->add(this->p, "1");
    StringMath temp(num);
    *this = temp;
}
StringMath StringMath::operator+(int n)
{
    char *num = toString(n);
    char *a = add(this->p, num);
    StringMath temp(a);
    return temp;
};
StringMath StringMath::operator+(StringMath obj)
{
    // cout << " + overloaded method calling" << endl;
    char *a = add(this->p, obj.p);
    StringMath temp(a);
    return temp;
}
bool StringMath::operator>(StringMath obj)
{
    int len1 = strlen(obj.p);
    int len2 = strlen(this->p);
    if (len2 > len1)
        return true;
    if (len2 < len1)
        return false;
    if (len1 == len2)
    {
        int cmp = strcmp(this->p, obj.p);
        if (cmp > 0)
            return true;
    }
    return false;
}
bool operator>(StringMath obj, int n)
{
    char *num = toString(n);
    int len1 = strlen(num);
    int len2 = strlen(obj.p);
    if (len2 > len1)
        return true;
    if (len2 < len1)
        return false;
    if (len1 == len2)
    {
        int cmp = strcmp(obj.p, num);
        if (cmp > 0)
            return true;
    }
    return false;
}
StringMath operator+(int n, StringMath obj)
{
    char *num = toString(n);
    char *a = obj.add(num, obj.p);
    StringMath temp(a);
    return temp;
};

char *StringMath::add(char *num1, char *num2)
{
    char *result;
    int len = 0;
    int len1 = strlen(num1);
    int len2 = strlen(num2);
    len = len2 > len1 ? len2 + 1 : len1 + 1;
    result = new char[len];
    int carry = 0, n1, n2;
    for (int i = 0; i < len; i++)
    {
        if (i < len1)
            n1 = num1[len1 - i - 1] - '0';
        else
            n1 = 0;
        if (i < len2)
            n2 = num2[len2 - i - 1] - '0';
        else
            n2 = 0;
        int temp = carry + n1 + n2;
        carry = temp / 10;
        result[len - i - 1] = '0' + (temp % 10);
    }
    result[len] = '\0';
    if (result[0] == '0')
        result++;
    return result;
}

void StringMath::show()
{
    cout << "This string contains ";
    int len = strlen(p);
    for (int i = 0; i < len; i++)
    {
        cout << p[i];
    }
    cout << endl;
}

int main()
{
    StringMath S1;
    StringMath S2("123");
    StringMath S3("757");
    StringMath S4("220");
    StringMath S5;
    int n = 345;
    S1 = S4;
    S1.show();
    //Print the character string of S1 and S4
    S1 = S2 + S3 + S4;
    S1.show();
    // //Print the character string of S1, S2, S3 and S4, where S1 contains the character string: “1100”
    S5 = S4 = S3;
    S5.show();
    S4.show();
    S3.show();
    S5 = S3 + 100;
    S5.show();
    S2 = 100 + S5;
    S2.show();
    // // Print the character string of S5, S4 and S3
    if (S3 > n)
    {
        S5 = S3 + n;
        S5.show();
    }
    // Print the character string of S5, where S5 contains the character string: “1102”
    S5 = n + S2;
    S5.show();
    // Print the character string of S5, where S5 contains the character string: “468”
    if (S5 > S2)
    {
        S5++; //Assume prefix increment
    }
    S5.show();
    // Print
    // // Print the character string of S5, where S5 contains the character string: “469”
}