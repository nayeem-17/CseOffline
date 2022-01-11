package level2_term1.DSA.offline07.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Input the size of the array and the order. Input -1 to exit!");
            System.out.print("Enter The size of the array -> ");
            int size = scanner.nextInt();
            if (size == -1)
                break;
            int[] mainArray = new int[size];
            System.out.println("Enter The order of elements of the array ->");
            System.out.println("1. Ascending order\n2. Random order\n3. Descending order");
            int order = scanner.nextInt();
            int randNumber = (int) (Math.random() * 10000) % 100;
            if (order == 1) {
                for (int i = 0; i < size; i++) {
                    mainArray[i] = randNumber * randNumber + ((i + 1) + (i + 2) + (i + 3));
                }
            } else if (order == 2) {
                for (int i = 0; i < size; i++) {
                    mainArray[i] = (int) (Math.random() * 10000) * (int) (Math.random() * 100000)
                            + (int) (Math.random() * 100000);
                }
            } else if (order == 3) {
                for (int i = size - 1; i >= 0; i--) {
                    mainArray[i] = randNumber * randNumber + ((i + randNumber) + (i + 1) + (i + randNumber * 2));
                }
            } else
                continue;
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

            System.out.println("Time took for Merge sort -> " + (endTimeForMergeSort - startTimeForMergeSort) / 1000000
                    + " milliseconds");
            System.out.println("Time took for Quick sort -> " + (endTimeForQuickSort - startTimeForQuickSort) / 1000000
                    + " milliseconds");
            System.out.println("Sorted Arrays...");
            System.out.println("Merge sort Quick sort");
            for (int i = 0; i < size; i++) {
                System.out.println(copyForMergeSort[i] + " " + copyForQuickSort[i]);
            }
        }
        scanner.close();
    }
}