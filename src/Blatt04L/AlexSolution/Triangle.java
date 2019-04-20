package Blatt04L.AlexSolution;

public class Triangle extends Simplex {

    public Triangle(int dim, Point... p){
        super(dim,p);
    }

    @Override
    public boolean validate(){
        if(dimension!= 2 || points.length!=3){
            return false;
        }

        for(int i=0; i<points.length; i++){
            if(points[i].dim()!=2){
                return false;
            }
        }

        return true;
    }


}
