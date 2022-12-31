package offline06.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "input.txt";
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String command;
        MaxHeap max_heap = new MaxHeap();
        while (scanner.hasNext()) {
            command = scanner.next();
            if (command.equals("INC")) {
                int prev_value = scanner.nextInt();
                int new_value = scanner.nextInt();
                max_heap.increaseKey(prev_value, new_value);
            } else if (command.equals("INS")) {
                int value = scanner.nextInt();
                max_heap.insert(value);
                // max_heap.print();
            } else if (command.equals("FIN")) {
                int max = max_heap.findMax();
                System.out.println("FindMax returned " + max);
            } else if (command.equals("PRI")) {
                max_heap.print();
            } else if (command.equals("EXT")) {
                int max = max_heap.extractMax();
                // max_heap.print();
                System.out.println("ExtractMax returned " + max);
            }
        }
        scanner.close();
    }
}