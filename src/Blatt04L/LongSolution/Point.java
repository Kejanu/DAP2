package Blatt04L.LongSolution;

import Blatt04L.Interfaces.UniversalPoint;


public class Point implements UniversalPoint {

    private int d; //dimension
    private double[] pointValues;

    public Point(int d, double... values){
        if(d < 0){
            throw new IllegalArgumentException("Dimension muss >=0 sein");
        }
        if(values.length != d){
            throw new IllegalArgumentException("yo, Arguments.length muss mit der Dimension übereinstimmen.");
        }
        this.d = d;
        this.pointValues = values;
    }

    public double get(int i){
         if(i < 0 || i >= this.pointValues.length){
             throw new IllegalArgumentException("Axis must within bounds");
         }
         return pointValues[i];
    }
    public int dim(){
        return d;
    }

    //visualisationPurposes/Interface
    public double getX(){
        return this.pointValues[0];
    }
    public double getY(){
        return this.pointValues[1];
    }

    //randomGeneratingPurposes
    public void setX(double d) {
        this.pointValues[0] = d;
    }
    public void setY(double d) {
        this.pointValues[1] = d;
    }
}
