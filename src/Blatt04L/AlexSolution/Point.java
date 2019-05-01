package Blatt04L.AlexSolution;

import Blatt04L.Interfaces.UniversalPoint;

public class Point implements UniversalPoint {

    private int dimension;
    private double[] coordinates;

    public Point(int dim, double... values) {

        if (dim > 0) {
            dimension = dim;
        } else {
            throw new IllegalArgumentException();
        }
        if (values.length != dimension) {
            throw new IllegalArgumentException();
        } else {
            coordinates = values;
        }
    }

    public int dim() {
        return dimension;
    }

    public double get(int i) {
        if (i >= 0 && i < coordinates.length) {
            return coordinates[i];
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void printPoint(){
        for(int i=0; i<coordinates.length; i++){
            System.out.print(coordinates[i] + " ");
        }
        System.out.println();
    }

    public double getX(){
        if(dimension>= 1){
            return get(0);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public double getY(){
        if(dimension>= 2){
            return get(1);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public boolean equals(Point p){
        if(dimension== p.dim()){
            for(int i=0; i<coordinates.length; i++){
                if(coordinates[i]!= p.get(i)){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public void setX(double n){
        coordinates[0]=n;
    }

    public void setY(double n){
        coordinates[0]=n;
    }






}
