package Blatt04L.LongSolution;

public class Triangle extends Simplex {
    public Triangle(int d, Point... points){
        super(d, points); //konstrukter darüber
    }

    public boolean validate() {
        if((points.length != 3 && d != 2)){
            System.out.println("Dimension must be exactly 2 and length must be 3.");
            return false;
        }
        System.out.println(points[0]);
        System.out.println(points[1]);
        if ((points[0].getX()==points[1].getX() && points[0].getY()==points[1].getY())
                || (points[0].getX()==points[2].getX() && points[0].getY()==points[2].getY())
                || (points[1].getX()==points[2].getX() && points[1].getY()==points[2].getY())) {
            System.out.println("3 unique points could not be found for the triangle.");
            return false;
        }

        return true;
    }
}
