#include <bits/stdc++.h>
using namespace std;
class Job
{
public:
    int profit;
    int deadline;
    string title;
    Job(string t, int d, int p)
    {
        profit = p;
        deadline = d;
        title = t;
    }
    bool operator<(const Job &j) const
    {
        return profit < j.profit;
    }
    bool operator>(const Job &j) const
    {
        return profit > j.profit;
    }
};
void print_job_sequencing(vector<Job> jobs)
{
    sort(jobs.begin(), jobs.end(), greater<Job>());

    // for (int i = 0; i < jobs.size(); i++)
    //     cout << jobs[i].title << " " << jobs[i].profit << " " << jobs[i].deadline << endl;
}

int main(int argc, char const *argv[])
{
    Job arr[] = {
        Job("c", 2, 60),
        Job("e", 3, 15),
        Job("a", 5, 100),
        Job("b", 3, 70),
        Job("d", 1, 25)};
    vector<Job> jobs(arr, arr + 5);
    print_job_sequencing(jobs);
    return 0;
}
