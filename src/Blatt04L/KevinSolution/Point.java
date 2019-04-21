package Blatt04L.KevinSolution;

public class Point {
    private double[] corrdinates;
    // d = dimension
    private int d;

    public Point(int d, double... values) {
        if (d < 1)
            throw new IllegalArgumentException();

        this.d = values.length;
        this.corrdinates = values;
    }

    @Override
    public String toString() {
        return "Point x = " + corrdinates[0] + ", y = " + corrdinates[1];
    }

    public double get(int i) {
        if (i < 0 || i >= this.corrdinates.length)
            throw new IllegalArgumentException();
        return corrdinates[i];
    }

    public double getX() {
        return this.corrdinates[0];
    }

    public void setX(double d) {
        this.corrdinates[0] = d;
    }

    public void setY(double d) {
        this.corrdinates[1] = d;
    }

    public double getY() {
        return this.corrdinates[1];
    }

    public int dim() {
        return d;
    }
}
