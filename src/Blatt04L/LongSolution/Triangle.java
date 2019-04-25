package Blatt04L.LongSolution;

public class Triangle extends Simplex {

    public static void main(String[] args) {
    /*    double[] p1 = {1,2,0};
        double[] p2 = {1,4,0};
        double[] p3 = {2,4,0};
        double[] p4 = {0,0,0};

        Point point1 = new Point(3,p1);
        Point point2 = new Point(3, p2);
        Point point3 = new Point(3, p3);
        Point point4 = new Point(3, p4);
        Point[] allPoints = {point1,point2,point3,point4};
        Triangle test = new Triangle(3,allPoints);
        System.out.println("Soll Ergebnis: "+((2+2.23+2.23)+(2+1+4.123)+(2.23+1+4.472)+(2.23+4.123+4.47)));
        /*System.out.println("Soll Ergebnis: "+(Math.sqrt(Math.pow(2,2)+Math.pow(2.23,2)+Math.pow(2.23,2)) + Math.sqrt(Math.pow(2,2)+Math.pow(1,2)+Math.pow(4.123,2))+
                Math.sqrt(Math.pow(2.23,2)+Math.pow(1,2)+Math.pow(4.472,2)) + Math.sqrt(Math.pow(2.23,2)+Math.pow(4.123,2)+Math.pow(4.47,2))));
        System.out.println(test.perimeter());*/

    }
    public Triangle(int d, Point... points){
        super(d, points); //konstrukter darüber
    }
    public boolean validate() {
        if(points.length == 3 && d == 2){
            return true;
        }
        return false;
    }
}
