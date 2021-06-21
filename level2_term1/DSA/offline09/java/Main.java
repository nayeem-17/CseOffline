package level2_term1.DSA.offline09.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int K; // number of friends
        int N;// number of plants
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        K = scanner.nextInt();
        List<Integer> prices = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int temp = scanner.nextInt();
            prices.add(temp);
        }
        Collections.sort(prices, (p1, p2) -> p2 - p1);
        int purchasePerFriend = 0;
        int minimumPrice = 0;
        for (int i = 0; i < N; i++) {
            if (i % K == 0 && i != 0)
                purchasePerFriend++;
            minimumPrice += ((purchasePerFriend + 1) * prices.get(i));
        }
        System.out.println(minimumPrice);
        scanner.close();
    }
}
