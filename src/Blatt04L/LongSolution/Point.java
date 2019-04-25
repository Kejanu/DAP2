package Blatt04L.LongSolution;

public class Point {

    int d; //dimension
    double[] pointValues;

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

}
