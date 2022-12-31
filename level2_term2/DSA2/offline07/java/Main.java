package offline07.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String filename = "input.txt";
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        AVLTree tree = new AVLTree();
        String command;
        while (scanner.hasNext()) {
            command = scanner.next();
            if (command.equals("I")) {
                int value = scanner.nextInt();
                tree.insert(value);
            } else if (command.equals("F")) {
                int value = scanner.nextInt();
                System.out.println(tree.find(value));
            } else if (command.equals("D")) {
                int value = scanner.nextInt();
                tree.delete(value);
            }
        }
        scanner.close();
    }
}
