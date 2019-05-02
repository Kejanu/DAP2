package Blatt04L.LongSolution;

public abstract class Simplex{

    int d; //dimension
    Point[] points;

    public Simplex(int d, Point... points){
        if(d < 0){
            throw new IllegalArgumentException("ERROR: Dimension must be 0 or higher.");
        }
        this.d = d;

        if(points.length != d+1){
            throw new IllegalArgumentException("ERROR: Points Length must be d+1.");
        }
        this.points = points;
    }

    public Point[] get(){
        return points;
    }

    public double perimeter(){
        double sum = 0;
        EuclidDistance edistance = new EuclidDistance();

        for(int i = 0; i <= d; i++){
            for(int k=0; k<=d;k++) {
                sum = sum + edistance.distance(this.points[i], this.points[k]);
            }
            //System.out.println("Zwischen Summe Punkte des " + i + "ten Durchlaufs:" + sum);
        }
        return sum/2.0;
    }
    public abstract boolean validate();
}
