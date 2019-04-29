package Blatt04L.KevinSolution;

import Blatt04L.Interfaces.UniversalPoint;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Point implements UniversalPoint {
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
        DecimalFormat dc = new DecimalFormat("###.###############");
        dc.setMinimumFractionDigits(15);

        return "Point x = " + dc.format(corrdinates[0]) + ", y = " + dc.format(corrdinates[1]);
    }

    public double get(int i) {
        if (i < 0 || i >= this.corrdinates.length)
            throw new IllegalArgumentException();
        return corrdinates[i];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;
        return d == point.d && Arrays.equals(corrdinates, point.corrdinates);
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
