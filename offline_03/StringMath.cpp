#include <iostream>
#include <string.h>
#include <cmath>
using namespace std;

class StringMath
{
    char *p;

public:
    StringMath()
    {
        p = NULL;
    };
    StringMath(int n)
    {
        int len = (int)ceil(log10(n));
        p = new char[len];
        int *temp = new int[len];
        for (int i = 0; i < len; i++)
        {
            temp[i] = n % 10;
            n = n / 10;
        }
        for (int i = 0; i < len; i++)
        {
            p[i] = '0' + temp[len - i - 1];
        }
    }
    StringMath(char *str)
    {
        int len = strlen(str);
        this->p = new char[len];
        for (int i = 0; i < len; i++)
        {
            p[i] = str[i];
        }
    };
    StringMath(const StringMath &obj)
    {

        // return this;
        int len1 = strlen(obj.p);
        delete[] this->p;
        this->p = new char[len1];
        for (int i = 0; i < len1; i++)
        {
            p[i] = obj.p[i];
        }
    };
    bool operator>(StringMath obj)
    {
        int len1 = this->getlen();
        int len2 = obj.getlen();
        if (len1 > len2)
        {
            return true;
        }
        else if (len1 == len2)
        {
            int temp = strcmp(this->p, obj.p);
            if (temp == 1)
                return true;
            return false;
        }
        return false;
    };

    int generateint()
    {
        int len = this->getlen();
        int res = 0;
        for (int i = 0; i < len; i++)
        {
            int temp = p[i] - '0';
            res *= 10;
            res += temp;
        }
        return res;
    }
    StringMath operator+(StringMath &obj)
    {
        int num1 = this->generateint();
        int num2 = obj.generateint();
        StringMath temp(num1 + num2);
        return temp;
    };
    StringMath operator+(int n)
    {
        int n1 = this->generateint();
        StringMath temp(n1 + n);
        return temp;
    };
    friend StringMath operator+(int n, StringMath &obj);
    friend bool operator>(StringMath obj, int n);
    void operator++(int unused)
    {
        int temp = this->generateint();
        temp++;
    }
    int getlen()
    {
        return strlen(p);
    }
    ~StringMath()
    {
        delete[] p;
    };
};
bool operator>(StringMath obj, int n)
{
    int temp = obj.generateint();
    return temp > n;
}
StringMath operator+(int n, StringMath &obj)
{
    int temp = obj.generateint();
    temp += n;
    StringMath temp1(temp);
    return temp1;
}
int main()
{
    StringMath S1(12121312);
    StringMath S2("123");
    // cout << S1.generateint() << endl;
    StringMath S3("757");
    StringMath S4("220");
    StringMath S5;
    int n = 345;
    S1 = S4;
    cout << S1.generateint() << endl;
    S1 = S2 + S3 + S4;
    //Print the character string of S1 and S4
    cout << S1.generateint() << endl;
    // cout << S1.generateint() << endl;
    //Print the character string of S1, S2, S3 and S4, where S1 contains the character string: “1100”
    // S5 = S4 = S3;
    // // Print the character string of S5, S4 and S3
    // if (S3 > n)
    // {
    //     S5 = S3 + n;
    // }
    // // Print the character string of S5, where S5 contains the character string: “1102”
    // S5 = n + S2;
    // // Print the character string of S5, where S5 contains the character string: “468”
    // if (S5 > S2)
    // {
    //     S5++; //Assume prefix increment
    // }
    // // Print the character string of S5, where S5 contains the character string: “469”
}