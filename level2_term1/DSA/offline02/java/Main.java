package level2_term1.DSA.offline02.java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int N;
        Scanner scanner = new Scanner(System.in);
        LinkedList players = new LinkedList();
        N = scanner.nextInt();
        // System.out.println(N);
        for (int i = 0; i < N; i++) {
            int resTime = scanner.nextInt();
            players.insert(new Node(i + 1, resTime));
        }
        // players.print();
        PillowGame game = new PillowGame(players, N);
        while (true) {
            int time = scanner.nextInt();
            String command = scanner.next();
            // System.out.println(time + command);
            if (command.equals("F")) {
                game.finishGame(time);
                // System.out.println(game);
                scanner.close();
                break;
            } else if (command.equals("M")) {
                game.stopMusic(time);
            } else if (command.equals("P")) {
                game.printCurrentPlayer(time);
            } else if (command.equals("R")) {
                game.reverseDirection(time);
            } else if (command.equals("I")) {
                int resTime = scanner.nextInt();
                game.insertPlayer(time, resTime);
            }
            // players.print();
            // System.out.println(game);
        }
    }
}
// 3
// 4 5 2
// 1 M
// 2 M
// 3 I 5
// 5 M
// 10 F

// 3
// 4 5 2
// 5 R
// 6 F