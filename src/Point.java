/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (y == that.y && x != that.x) return +0.0;
        if (y == that.y) return Double.NEGATIVE_INFINITY;
        if (x == that.x) return Double.POSITIVE_INFINITY;
        return (double) (that.y - y) / (that.x - x);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        if (y < that.y) return -1;
        if (y > that.y) return 1;
        return Integer.compare(x, that.x);
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return (o1, o2) -> {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);
            if (slope1 < slope2) return -1;
            if (slope1 > slope2) return 1;
            return 0;
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 0);
        if (p1.slopeTo(p2) != 0.0) {
            throw new IllegalStateException("Should be vertical line");
        }

        Point p3 = new Point(0, 1);
        if (p1.slopeTo(p3) != Double.POSITIVE_INFINITY) {
            throw new IllegalStateException("Should be horizontal line");
        }

        Point p4 = new Point(1, 1);
        if (p1.slopeTo(p4) != Double.NEGATIVE_INFINITY) {
            throw new IllegalStateException("Should be the same point");
        }

        Point p5 = new Point(2, 2);
        Point p6 = new Point(1, 2);
        Comparator<Point> comparator = p1.slopeOrder();
        if (comparator.compare(p5, p6) > 0) {
            throw new IllegalStateException("Wrong comparator");
        }
        if (comparator.compare(p6, p5) < 0) {
            throw new IllegalStateException("Wrong comparator");
        }
        Point p7 = new Point(3, 3);
        if (comparator.compare(p5, p7) != 0) {
            throw new IllegalStateException("Wrong comparator");
        }

        StdOut.println("Test is successful");
    }
}
