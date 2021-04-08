package level2_term1.DSA.offline03.java;

import java.util.Scanner;

public class Problem2 {
    public static Queue queue = new Queue();

    public static String format(String input) {
        String output = "";
        int[] charCounter = new int[26];
        for (int i = 0; i < 26; i++)
            charCounter[i] = 0;
        char[] arr = input.toCharArray();
        for (char i : arr) {
            int pos = i - 'a';
            charCounter[pos]++;
            if (charCounter[pos] == 1)
                queue.enqueue(i);
        }
        char head = queue.dequeue();
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (head == arr[i])
                count++;
            if (count > 1) {
                if (queue.isEmpty())
                    output += '#';
                else {
                    head = queue.dequeue();
                    count = 1;
                    output += head;
                }
            } else {
                output += head;
            }
        }
        return output;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sOld = scanner.nextLine();
        String sNew = Problem2.format(sOld);
        System.out.println(sNew);
        scanner.close();
    }
}
