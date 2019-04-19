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

    public double get(int i) {
        return corrdinates[i];
    }

    public int dim() {
        return d;
    }
}
