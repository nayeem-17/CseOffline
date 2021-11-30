#include <bits/stdc++.h>
using namespace std;
#define EMPTY -1

int getMinStep(int n)
{
    if (n == 1)
        return 0;
    int minStep = getMinStep(n - 1);
    if (n % 2 == 0)
        minStep = min(minStep, getMinStep(n / 2));
    if (n % 3 == 0)
        minStep = min(minStep, getMinStep(n / 3));
    return minStep + 1;
}

int getMinStepWIthMomoization(int n, int memo[])
{
    if (n == 1)
        return 0;
    if (memo[n] != EMPTY)
        return memo[n];
    int minStep = getMinStepWIthMomoization(n - 1, memo);
    if (n % 2 == 0)
        minStep = min(minStep, getMinStepWIthMomoization(n / 2, memo));
    if (n % 3 == 0)
        minStep = min(minStep, getMinStepWIthMomoization(n / 3, memo));
    memo[n] = minStep + 1;
    return memo[n];
}
int getMinStepWithTabu(int n)
{
    int table[n + 1];
    memset(table, n, sizeof(table));
    table[1] = 0;
    for (int i = 1; i < n; i++)
    {
        table[i + 1] = min(table[i + 1], table[i] + 1);
        if (i * 2 <= n)
            table[i * 2] = min(table[i * 2], table[i] + 1);
        if (i * 3 <= n)
            table[i * 3] = min(table[i * 3], table[i] + 1);
    }
    return table[n];
}
int main(int argc, char const *argv[])
{
    int memo[100000];
    memset(memo, EMPTY, sizeof(memo));
    cout << getMinStepWIthMomoization(10000, memo) << endl;
    cout << getMinStepWithTabu(1000000) << endl;
    return 0;
}
