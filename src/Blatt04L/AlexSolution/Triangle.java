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

    public boolean enclosed(Point p){
        //does not work
        /*
                  B(10,30)
                   / \
                  /   \
                 /     \
                /   P   \      P'
               /         \
        A(0,0) ----------- C(20,0)
        Solution:
        Let the coordinates of three corners be (x1, y1), (x2, y2) and (x3, y3). And coordinates of the given point P be (x, y)

        1) Calculate area of the given triangle, i.e., area of the triangle ABC in the above diagram.
            Area A = [ x1(y2 – y3) + x2(y3 – y1) + x3(y1-y2)]/2
        2) Calculate area of the triangle PAB. We can use the same formula for this. Let this area be A1.
        3) Calculate area of the triangle PBC. Let this area be A2.
        4) Calculate area of the triangle PAC. Let this area be A3.
        5) If P lies inside the triangle, then A1 + A2 + A3 must be equal to A
        https://www.geeksforgeeks.org/check-whether-a-given-point-lies-inside-a-triangle-or-not/
        */
        Point a= points[0];
        Point b= points[1];
        Point c= points[2];

        //APC
        double t1= (a.getX()*(p.getY()-c.getY()) + p.getX()*(c.getY()-a.getY()) + c.getX()*(a.getY()-p.getY()))/2;

        //APB
        double t2= (a.getX()*(p.getY()-b.getY()) + p.getX()*(b.getY()-a.getY()) + b.getX()*(a.getY()-p.getY()))/2;

        //BPC
        double t3= (b.getX()*(p.getY()-c.getY()) + p.getX()*(c.getY()-b.getY()) + c.getX()*(b.getY()-p.getY()))/2;

        //ABC
        double t= (a.getX()*(b.getY()-c.getY()) + b.getX()*(c.getY()-a.getY()) + c.getX()*(a.getY()-b.getY()))/2;


        return (t1+t2+t3)== t;







    }


}
