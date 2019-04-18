package Blatt04L.Kevin;

public class EuclidDistance implements Distance {

    @Override
    public double distance(Point p1, Point p2) {
        double result = 0;
        for (int i = 0; i < p1.dim(); ++i) {
            result += Math.pow(p2.get(i) - p1.get(i), 2);
        }
        return Math.sqrt(result);
    }
}
