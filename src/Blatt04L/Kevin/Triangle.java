package Blatt04L.Kevin;

public class Triangle extends Simplex {

    public Triangle(int d, Point... points) {
        super(d, points);
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
