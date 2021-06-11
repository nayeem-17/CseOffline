package level2_term1.DSA.offline07.java;

public class QuickSort {

    public static void sort(int[] Arr, int low, int high) {
        if (low < high) {
            int q = partition(Arr, low, high);
            if (q > low)
                sort(Arr, low, q - 1);
            if (q < high)
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
            // System.out.println("Loop 1");
            // System.out.println(i + " " + j + " " + Arr[i] + " " + Arr[j]);
            while (Arr[i] <= pivot) {
                // System.out.println("Loop 2");
                i++;
                if (i >= high)
                    break;
            }
            while (pivot <= Arr[j]) {
                j--;
                // System.out.println("Loop 3");

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

    // public static void main(String[] args) {
    // int[] arr = new int[1000000];
    // for (int i = 0; i < arr.length; i++)
    // arr[i] = (int) (Math.random() * 10000) * (int) (Math.random() * 100000) +
    // (int) (Math.random() * 100000);
    // // int arr[] = { 59, 20, 62, 41, 32, 60, 66, 30, 96, 83 };
    // // System.out.println(Arrays.toString(arr));
    // // System.out.println(partition(arr, 0 + 8, arr.length - 1));
    // // Arrays.sort(arr, Collections.reverseOrder());
    // int[] arrDesc =
    // Arrays.stream(arr).boxed().sorted().mapToInt(Integer::intValue).toArray();
    // double startTime2 = System.nanoTime();
    // QuickSort.sort(arrDesc, 0, arrDesc.length - 1);
    // double endTime2 = System.nanoTime();
    // System.out.println("Time : " + (endTime2 - startTime2) / 1000000000);
    // }
}
