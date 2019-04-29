package Blatt04L.LongSolution;

public class EuclidDistance implements Distance {
    public double distance(Point p1, Point p2){

        double endSum;
        double partialSum = 0;

        for(int i=0; i<p1.dim();i++) {
            partialSum = partialSum + Math.pow(p1.get(i) - p2.get(i), 2);
        }
        endSum = Math.sqrt(partialSum);
        return endSum;
    }
}
