package level2_term1.DSA.offline2.java;

public class Node {
    int playerId;
    int responseTime;

    /**
     * @param playerId
     * @param responseTime
     */
    public Node(int playerId, int responseTime) {
        this.playerId = playerId;
        this.responseTime = responseTime;
    }

    /**
     * @return the playerId
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * @return the responseTime
     */
    public int getResponseTime() {
        return responseTime;
    }

    /**
     * @param responseTime the responseTime to set
     */
    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Node [playerId=" + playerId + ", responseTime=" + responseTime + "]";
    }

}