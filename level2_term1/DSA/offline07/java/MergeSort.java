package level2_term1.DSA.offline07.java;

public class MergeSort {
    private static void merge(int[] arr, int low, int mid, int high) {
        int i = low, j = mid + 1;
        int temp[] = new int[high - low + 1];
        // for (int k = low; k <= high; k++)
        // temp[k - low] = arr[k];
        System.arraycopy(arr, low, temp, 0, high - low + 1);
        for (int k = low; k <= high; k++) {
            if (i > mid)
                arr[k] = temp[j++ - low];
            else if (j > high)
                arr[k] = temp[i++ - low];
            else if (temp[j - low] < temp[i - low])
                arr[k] = temp[j++ - low];
            else
                arr[k] = temp[i++ - low];
        }
    }

    public static void sort(int[] arr, int low, int high) {
        if (low == high)
            return;
        int mid = (low + high) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    // public static void main(String[] args) {
    // int[] arr = new int[1000000];
    // for (int i = 0; i < arr.length; i++)
    // arr[i] = i + (int) (Math.random() * 100000);

    // int[] arrDesc =
    // Arrays.stream(arr).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue)
    // .toArray();

    // double startTime2 = System.nanoTime();
    // MergeSort.sort(arrDesc, 0, arrDesc.length - 1);
    // double endTime2 = System.nanoTime();
    // System.out.println("Time : " + (endTime2 - startTime2) / 1000000);
    // }
}
