package level2_term1.DSA.offline07.java;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] Arr, int low, int high) {
        if (low < high) {
            int q = partition(Arr, low, high);
            sort(Arr, low, q - 1);
            sort(Arr, q + 1, high);
        }
    }

    public static int partition(int[] Arr, int low, int high) {
        // int pivot = Arr[(high + low) / 2];
        // Arr[(high + low) / 2] = Arr[low];
        int pivot = Arr[low];
        // Arr[low] = pivot;

        int i = low + 1, j = high;
        int temp = 0;
        while (true) {
            while (Arr[i] <= pivot) {
                i++;
                if (i >= high)
                    break;
            }
            while (pivot <= Arr[j]) {
                j--;
                if (j <= low)
                    break;
            }

            if (i >= j)
                break;
            temp = Arr[i];
            Arr[i] = Arr[j];
            Arr[j] = temp;
        }
        Arr[low] = Arr[j];
        Arr[j] = pivot;
        return j;
    }

    public static void main(String[] args) {
        int arr[] = { 59, 20, 62, 41, 32, 60, 66, 30, 96, 83 };
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
