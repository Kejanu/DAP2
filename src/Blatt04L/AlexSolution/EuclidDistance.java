package Blatt04L.AlexSolution;

public class EuclidDistance implements Distance{


    public double distance(Point p1, Point p2){

        if (p1.dim() != p2.dim()) {
            throw new IllegalArgumentException();
        }

        double result=0;

        for(int i=0; i<p1.dim(); i++){
            result= result + Math.pow(p2.get(i)-p1.get(i), 2);
        }

        return Math.sqrt(result);
    }
}
