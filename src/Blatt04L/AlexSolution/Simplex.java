package Blatt04L.AlexSolution;

public abstract class Simplex {

    private int dimension;
    private Point[] points;

    public Simplex(int dim, Point...p){


        if(dim>0) {
            dimension = dim;
        }else{
            throw new IllegalArgumentException();
        }

        /*
        if(p.length-1 != dimension){
            throw new IllegalArgumentException();
        }

        for (Point value : p){
            if(value.dim() != dimension){
                throw new IllegalArgumentException();
            }
        }

        points=p;

        */








    }

    public Point get(int i){
        if(i>=0 && i<points.length){
            return points[i];
        }else{
            throw new IllegalArgumentException();
        }
    }

    publ
}
