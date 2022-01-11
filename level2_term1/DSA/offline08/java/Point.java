package level2_term1.DSA.offline08.java;

public class Point {
    public int x;
    public int y;
    public int index;

    public Point(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public double distance(Point a) {
        int x_distance = this.x - a.x;
        int y_distance = this.y - a.y;
        double distance = Math.sqrt(x_distance * x_distance + y_distance * y_distance);
        return distance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Point [index=" + index + ", x=" + x + ", y=" + y + "]";
    }

}
