#include <cstdlib>
#include "Container.cpp"

class ContainerOp
{
    Container frist;
    Container second;
    int op; //0 to +, 1 to -

public:
    void setFirst(Container f)
    {
        this->frist = f;
    };
    void setSecond(Container s)
    {
        this->second = s;
    };
    Container getFirst()
    {
        return this->frist;
    };
    Container getSecond()
    {
        return this->second;
    };
    void setOp(int o)
    {
        this->op = o;
    };
    int getOp()
    {
        return this->op;
    };
    Container performOp()
    {
        Container temp;
        int firststoretype = frist.getStoredType();
        int secondstoretype = second.getStoredType();
        if (firststoretype == 1)
        {
            if (secondstoretype == 1)
            {
                int val;
                if (op)
                    val = *((int *)frist.getItem()) - *((int *)second.getItem());
                else
                    val = *((int *)frist.getItem()) + *((int *)second.getItem());
                temp.setItem(val);
            }
            if (secondstoretype == 2)
            {
                int val1 = *((int *)frist.getItem());
                int dim = second.getFirstDim();
                int *arr = new int[dim];
                arr = (int *)second.getItem();
                if (op == 0)
                {
                    for (int i = 0; i < dim; i++)
                    {
                        arr[i] += val1;
                    }
                    temp.setItem(arr, dim);
                }
                else
                {
                    for (int i = 0; i < dim; i++)
                    {
                        arr[i] = val1 - arr[i];
                    }
                    temp.setItem(arr, dim);
                }
                return temp;
            }
            if (secondstoretype == 3)
            {
                int val1 = *((int *)frist.getItem());
                int firdim = second.getFirstDim();
                int secdim = second.getSecondDim();
                int **arr = new int *[firdim];
                for (int i = 0; i < firdim; i++)
                {
                    arr[i] = new int[secdim];
                }
                arr = (int **)second.getItem();
                if (op == 0)
                {
                    for (int i = 0; i < firdim; i++)
                    {
                        for (int j = 0; j < secdim; j++)
                        {
                            arr[i][j] += val1;
                        }
                    }
                    temp.setItem(arr, firdim, secdim);
                }
                else
                {
                    for (int i = 0; i < firdim; i++)
                    {
                        for (int j = 0; j < secdim; j++)
                        {
                            arr[i][j] = val1 - arr[i][j];
                        }
                    }
                    temp.setItem(arr, firdim, secdim);
                }
                return temp;
            }
        }
        else if (secondstoretype == 1)
        {
            if (firststoretype == 2)
            {
                int val1 = *((int *)second.getItem());
                int dim = frist.getFirstDim();
                int *arr = new int[dim];
                arr = (int *)frist.getItem();
                if (op == 0)
                {
                    for (int i = 0; i < dim; i++)
                    {
                        arr[i] += val1;
                    }
                    temp.setItem(arr, dim);
                }
                else
                {
                    for (int i = 0; i < dim; i++)
                    {
                        arr[i] -= val1;
                    }
                    temp.setItem(arr, dim);
                }
                return temp;
            }
            if (firststoretype == 3)
            {
                int val1 = *((int *)second.getItem());
                int firdim = frist.getFirstDim();
                int secdim = frist.getSecondDim();
                int **arr = new int *[firdim];
                for (int i = 0; i < firdim; i++)
                {
                    arr[i] = new int[secdim];
                }
                arr = (int **)frist.getItem();
                if (op == 0)
                {
                    for (int i = 0; i < firdim; i++)
                    {
                        for (int j = 0; j < secdim; j++)
                        {
                            arr[i][j] += val1;
                        }
                    }
                    temp.setItem(arr, firdim, secdim);
                }
                else
                {
                    for (int i = 0; i < firdim; i++)
                    {
                        for (int j = 0; j < secdim; j++)
                        {
                            arr[i][j] -= val1;
                        }
                    }
                    temp.setItem(arr, firdim, secdim);
                }
                return temp;
            }
        }
        else
        {
            return frist;
        }
    };
};

int main()
{
    Container a(100);
    Container p(100);
    int *arr = new int[3];
    arr[0] = 10;
    arr[1] = 20;
    arr[2] = 30;
    Container b(arr, 3);
    int **mat = new int *[2];
    mat[0] = new int[3];
    mat[0][0] = 1;
    mat[0][1] = 2;
    mat[0][2] = 3;
    mat[1] = new int[3];
    mat[1][0] = 4;
    mat[1][1] = 5;
    mat[1][2] = 6;
    Container c(mat, 2, 3);
    ContainerOp temp;
    temp.setFirst(a);
    temp.getFirst().print();
    temp.setOp(1);
    temp.setSecond(b);
    // a.print();
    // b.print();
    Container res1 = temp.performOp();
    // res1.print();
}

// Container(const Container &obj) *this

//     copy = obj