package Blatt04L.KevinSolution;

public abstract class Simplex {
    Point[] points;
    int dimension;

    public Simplex(int dimension, Point... points) {
        // Do we even check this? Not in Aufgabenstellung ZULUL
        if (dimension+1 != points.length)
            throw new IllegalArgumentException();

        for (Point p : points)
            if (p.dim() != dimension)
                throw new IllegalArgumentException();

        this.points = points;
        // src, srcStart, dest, destStart, length
        //System.arraycopy(points, 0, this.points, 0, points.length);
        this.d = dimension;
    }

    public double perimeter() {
        EuclidDistance ed = new EuclidDistance();

        double length = 0;
        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {
                length += ed.distance(points[i], points[j]);
            }
        }
        return length / 2;
    }

    public abstract boolean validate();

    public Point[] getPoints() {
        return points;
    }

    public int getDimension() {
        return dimension;
    }
}
