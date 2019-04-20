package Blatt04L.AlexSolution;

public abstract class Simplex {

     int dimension;
     Point[] points;

    public Simplex(int dim, Point...p){


        if(dim>0) {
            dimension = dim;
        }else{
            throw new IllegalArgumentException();
        }

        points=p;

        if(!validate()){
            throw new IllegalArgumentException();
        }
    }

    public Point get(int i){
        if(i>=0 && i<points.length){
            return points[i];
        }else{
            throw new IllegalArgumentException();
        }
    }

    public abstract boolean validate();

    public double perimeter(){
        double result=0;
        EuclidDistance ed= new EuclidDistance();

        for(int i=0; i<points.length; i++){

            for (int j=0; j<points.length; j++){

                result= result + ed.distance(get(i),get(j));
            }
        }

        return result/2;
    }

}
