package level2_term1.DSA.offline10.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        int n = scanner.nextInt();
        int sum = scanner.nextInt();
        List<Integer> faces = new ArrayList<>();
        long[][] dp = new long[n + 1][sum + 1];

        Arrays.stream(dp).forEach(a -> Arrays.fill(a, -1));

        for (int i = 0; i < n; i++) {
            int tempFace = scanner.nextInt();
            faces.add(tempFace);
        }
        System.out.println(DiceThrow.countDiceThrow(dp, faces, sum));

    }
}
