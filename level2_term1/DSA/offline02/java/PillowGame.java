package level2_term1.DSA.offline02.java;

public class PillowGame {
    private Node currentPlayer;
    private LinkedList players;
    private int totalPlayer;
    private int currentTime;
    private int direction;
    private int restResTime;
    private int TOTAL;

    /**
     * @param players
     */
    public PillowGame(LinkedList players, int totalPlayer) {
        this.players = players;
        this.totalPlayer = totalPlayer;
        direction = 1;
        this.TOTAL = totalPlayer;
        this.currentPlayer = null;
    }

    /**
     * @return the currentPlayer
     */
    public Node getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the currentPlayer to set
     */
    public void setCurrentPlayer(int time) {
        int position = 0;

        if (this.currentPlayer == null) {
            this.currentTime = time;
            if (time < players.getNode(0).responseTime) {
                this.currentPlayer = players.getNode(0);
                this.restResTime = currentPlayer.responseTime - time;
                return;
            } else {
                time -= players.getNode(0).responseTime;
                this.currentPlayer = players.getNode(0);
                // this.restResTime = 0;
                position = 0;
            }
        }
        if (time >= currentTime) {
            time -= this.currentTime;
            this.currentTime += time;
            position = players.get(currentPlayer.playerId);
        }
        if (time == 0)
            return;

        if (restResTime != 0) {
            if (restResTime >= time) {
                restResTime -= time;
                return;
            } else {
                time -= restResTime;
            }
        }
        int totalResTime = 0;
        for (int i = 0; i < totalPlayer; i++) {
            totalResTime += (players.getNode(i).responseTime);
        }
        time = time % totalResTime;
        if (time == 0) {
            currentPlayer = players.getNode(position);
            return;
        }
        if (direction == -1) {
            position += totalPlayer;
            for (int i = 0; i < totalPlayer; i++) {
                int currentPos = (position - i - 1) % totalPlayer;
                int currentResTime = players.getNode(currentPos).responseTime;
                if (time <= currentResTime) {
                    currentPlayer = players.getNode(currentPos);
                    this.restResTime = currentPlayer.responseTime - time;
                    return;
                } else {
                    time -= currentResTime;
                }
            }
        } else if (direction == 1) {
            for (int i = 0; i < totalPlayer; i++) {
                int currentPos = (position + i + 1) % totalPlayer;
                int currentResTime = players.getNode(currentPos).responseTime;
                if (time <= currentResTime) {
                    currentPlayer = players.getNode(currentPos);
                    this.restResTime = currentPlayer.responseTime - time;
                    return;
                } else {
                    time -= currentResTime;
                }
            }
        }
    }

    public void stopMusic(int time) {
        this.setCurrentPlayer(time);
        System.out.println("Player " + currentPlayer.playerId + " has been eliminated at t= " + this.currentTime);
        players.remove(currentPlayer.playerId);
        // players.print();
        this.currentPlayer = players.getNode((players.get(currentPlayer.playerId - 1) + direction) % totalPlayer);
        this.restResTime = currentPlayer.responseTime;
        totalPlayer--;
        if (totalPlayer == 1) {
            System.out.println("Game over : Player " + currentPlayer.playerId + " wins!!");
            System.exit(0);
        }
    }

    public void printCurrentPlayer(int time) {
        this.setCurrentPlayer(time);
        System.out.println("Player " + currentPlayer.playerId + " is holding the pillow at t= " + this.currentTime);
    }

    public void reverseDirection(int time) {
        this.setCurrentPlayer(time);
        if (this.direction == -1)
            this.direction = 1;
        else
            this.direction = -1;
    }

    public void insertPlayer(int time, int refTime) {
        this.setCurrentPlayer(time);
        if (this.direction == 1) {
            players.insert((players.get((currentPlayer.playerId) + 1 + totalPlayer) % totalPlayer),
                    new Node(++this.TOTAL, refTime));
        } else {
            players.insert((players.get((currentPlayer.playerId) - 1 + totalPlayer) % totalPlayer),
                    new Node(++this.TOTAL, refTime));
        }
        this.totalPlayer++;

    }

    public void finishGame(int time) {
        this.setCurrentPlayer(time);
        String message = "Game over : Player " + currentPlayer.playerId + " is holding the pillow at t="
                + this.currentTime + ", pillow passing sequence = Player ";
        String temp = "";
        int index = players.get(currentPlayer.playerId);

        for (int i = 0; i < totalPlayer; i++) {
            temp += (players.getNode((totalPlayer + (this.direction) * i + index) % totalPlayer).playerId + ",");
        }
        message += temp;
        System.out.println(message);
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "PillowGame [TOTAL=" + TOTAL + ", currentPlayer=" + currentPlayer + ", currentTime=" + currentTime
                + ", direction=" + direction + ", restResTime=" + restResTime + ", totalPlayer=" + totalPlayer + "]";
    }

}
