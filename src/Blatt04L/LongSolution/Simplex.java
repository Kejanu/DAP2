package Blatt04L.LongSolution;

public abstract class Simplex {

    int d; //dimension
    Point[] points;

    public Simplex(int d, Point... points){
        if(d < 0){
            throw new IllegalArgumentException("Dimension must be 0 or higher");
        }
        this.d = d;

        if(points.length != d+1){
            throw new IllegalArgumentException("Length must be d+1");
        }
        this.points = points;
    }

    public Point[] get(){
        return points;
    }

    public double perimeter(){
        double sum = 0;
        double tempsum = 0;
        double testFromSubtraction;

        //O(n^(3)) btw.
        for(int i = 0; i <= d; i++){
            for(int k=0; k<=d;k++) {
                for (int j = 0; j < d; j++) {
                    System.out.println("Point A:" + this.points[i].get(j) + "," + "Point B: " + this.points[k].get(j));
                    testFromSubtraction = Math.pow(points[i].get(j) - this.points[k].get(j), 2);
                    tempsum = tempsum + testFromSubtraction;
                    //System.out.println("Test von Subtraktion: " + testFromSubtraction);
                }//
                tempsum = Math.sqrt(tempsum);
                System.out.println("tempsum: " + tempsum);
                sum = sum + tempsum;
                tempsum = 0;
                System.out.println("Zwischen Summe Punkte des" + i + "ten Summe:" + sum);
            }
        }
        System.out.println(sum);
        return sum/2.0;
    }
    public abstract boolean validate();
}
