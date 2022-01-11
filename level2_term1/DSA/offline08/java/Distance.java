package level2_term1.DSA.offline08.java;

import java.util.Arrays;

public class Distance {
    public double distance;
    public Point[] pairOfPoints;

    public Distance() {
        distance = -1;
        pairOfPoints = new Point[2];
    }

    public Distance(Point firstPoint, Point secondPoint) {
        pairOfPoints = new Point[] { firstPoint, secondPoint };
        distance = pairOfPoints[0].distance(pairOfPoints[1]);
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the pairOfPoints
     */
    public Point[] getPairOfPoints() {
        return pairOfPoints;
    }

    /**
     * @param pairOfPoints the pairOfPoints to set
     */
    public void setFirstPoint(Point point) {
        this.pairOfPoints[1] = point;
    }

    public void setLastPoint(Point point) {
        this.pairOfPoints[1] = point;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Distance [distance=" + distance + ", pairOfPoints=" + Arrays.toString(pairOfPoints) + "]";
    }

}
