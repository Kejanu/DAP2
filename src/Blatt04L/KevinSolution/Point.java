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
        return "Point x: " + corrdinates[0] + " y: " + corrdinates[1];
    }

    public double get(int i) {
        if (i < 0 || i >= this.corrdinates.length)
            throw new IllegalArgumentException();
        return corrdinates[i];
    }

    public int dim() {
        return d;
    }
}
