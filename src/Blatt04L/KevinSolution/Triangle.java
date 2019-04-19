package Blatt04L.KevinSolution;

public class Triangle extends Simplex {

    public Triangle(int dimension, Point... points) {
        super(dimension, points);
    }

    @Override
    public boolean validate() {
        if (points.length != 3)
            return false;

        for (Point p : points)
            if (p.dim() != 2)
                return false;
        return true;
    }
}
