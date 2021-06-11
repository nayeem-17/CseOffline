package level2_term1.DSA.offline07.java;

public class Eval {
    public static void main(String[] args) {
        int[] arr = { 10, 100, 1000, 10000, 100000, 1000000 };
        for (int j = 0; j < arr.length; j++) {
            int randNumber = (int) (Math.random() * 10000) % 100;
            int size = arr[j];
            int[] mainArray = new int[size];
            for (int i = 0; i < size; i++)
                mainArray[i] = randNumber * randNumber + ((i + 1) + (i + 2) + (i + 3));

            int[] copyForQuickSort = new int[size];
            int[] copyForMergeSort = new int[size];

            System.arraycopy(mainArray, 0, copyForMergeSort, 0, size);
            System.arraycopy(mainArray, 0, copyForQuickSort, 0, size);

            double startTimeForMergeSort = System.nanoTime();
            MergeSort.sort(copyForMergeSort, 0, copyForMergeSort.length - 1);
            double endTimeForMergeSort = System.nanoTime();

            double startTimeForQuickSort = System.nanoTime();
            QuickSort.sort(copyForQuickSort, 0, copyForQuickSort.length - 1);
            double endTimeForQuickSort = System.nanoTime();

            System.out.println("Time took for " + arr[j] + " input & ascending order Merge sort -> "
                    + (endTimeForMergeSort - startTimeForMergeSort) / 1000000 + " milliseconds");
            System.out.println("Time took for " + arr[j] + " input & ascending order Quick sort -> "
                    + (endTimeForQuickSort - startTimeForQuickSort) / 1000000 + " milliseconds");

            for (int i = 0; i < size; i++) {
                mainArray[i] = (int) (Math.random() * 10000) * (int) (Math.random() * 100000)
                        + (int) (Math.random() * 100000);
            }
            System.arraycopy(mainArray, 0, copyForMergeSort, 0, size);
            System.arraycopy(mainArray, 0, copyForQuickSort, 0, size);

            startTimeForMergeSort = System.nanoTime();
            MergeSort.sort(copyForMergeSort, 0, copyForMergeSort.length - 1);
            endTimeForMergeSort = System.nanoTime();

            startTimeForQuickSort = System.nanoTime();
            QuickSort.sort(copyForQuickSort, 0, copyForQuickSort.length - 1);
            endTimeForQuickSort = System.nanoTime();

            System.out.println("Time took for " + arr[j] + " input & random order Merge sort -> "
                    + (endTimeForMergeSort - startTimeForMergeSort) / 1000000 + " milliseconds");
            System.out.println("Time took for " + arr[j] + " input & random order Quick sort -> "
                    + (endTimeForQuickSort - startTimeForQuickSort) / 1000000 + " milliseconds");

            for (int i = size - 1; i >= 0; i--) {
                mainArray[i] = randNumber * randNumber + ((i + randNumber) + (i + 1) + (i + randNumber * 2));
            }

            System.arraycopy(mainArray, 0, copyForMergeSort, 0, size);
            System.arraycopy(mainArray, 0, copyForQuickSort, 0, size);

            startTimeForMergeSort = System.nanoTime();
            MergeSort.sort(copyForMergeSort, 0, copyForMergeSort.length - 1);
            endTimeForMergeSort = System.nanoTime();

            startTimeForQuickSort = System.nanoTime();
            QuickSort.sort(copyForQuickSort, 0, copyForQuickSort.length - 1);
            endTimeForQuickSort = System.nanoTime();

            System.out.println("Time took for " + arr[j] + " input & descending order Merge sort -> "
                    + (endTimeForMergeSort - startTimeForMergeSort) / 1000000 + " milliseconds");
            System.out.println("Time took for " + arr[j] + " input & descending order Quick sort -> "
                    + (endTimeForQuickSort - startTimeForQuickSort) / 1000000 + " milliseconds");

        }
    }
}
