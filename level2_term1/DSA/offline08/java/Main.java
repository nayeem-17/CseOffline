package level2_term1.DSA.offline08.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Point[] temp;

    public static void main(String[] args) throws FileNotFoundException {
        int n;
        File file = new File("input.txt");
        Scanner scanner = new Scanner(file);
        n = scanner.nextInt();
        List<Point> pointsX = new ArrayList<>();
        temp = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            Point point = new Point(x, y, i);
            pointsX.add(point);
        }
        Collections.sort(pointsX, (p1, p2) -> p1.x - p2.x);

        // pointsX.forEach(System.out::println);
        // pointsY.forEach(System.out::println);

        List<Distance> result = SecondShortestDistance.secondClosestPoint(pointsX, 0, n - 1);
        int[] index = new int[2];
        index[0] = result.get(1).pairOfPoints[0].index;
        index[1] = result.get(1).pairOfPoints[1].index;
        Arrays.sort(index);
        System.out.println(index[0] + " " + index[1]);
        System.out.println(String.format("%.4f", result.get(1).distance));

        scanner.close();
    }
}
