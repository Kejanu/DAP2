package Blatt04L.AlexSolution;

public class Point {

    private int dimension;
    private double[] coordinates;

    public Point(int dim, double... values) {

        if (dim > 0) {
            dimension = dim;
        } else {
            throw new IllegalArgumentException();
        }

        /*
        if(values== null){
            coordinates= new double[dimension];
            for (int i=0; i<coordinates.length; i++){
                coordinates[i]=0;
            }
        }else
            */
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






}
