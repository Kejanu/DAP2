package Blatt04L.LongSolution;

import Templates.ConvexVisualization;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConvexHull {
    public static void main(String[] args) {


        Point[] points = randomlyGeneratedPointsInTriangleSolvedWithGeometry(1000, 10, 100);
        //Point[] points = new Point[4];
        LinkedList<Point> result = (LinkedList<Point>) simpleConvex(points);

        if(result == null){
            System.out.println("Creating of list failed.");
            return;
        }
        //not sure about this
        /*if(result.size() <= 3){
            System.out.println("Not enough unique Points to create a ConvexHull");
            return;
        }*/
        if(result.size() == 0){
            System.out.println("Only one or no unique points were found. No Convex Hull could be created.\n" +
                    "Please input at least 2 unique points next time.");
            return;
        }
        Iterator<Point> it = result.iterator();
        Point current = it.next();
        while(it.hasNext()) {
            //totally didn't steal that from kevin
            System.out.println("Point: ("+ current.get(0)+", "+current.get(1)+ ") is connected to " +
                    " Point: (" + (current=it.next()).get(0)+", "+current.get(1)+")");

        }

        new ConvexVisualization(points, result, 1000, 1000, 8);
    }


    public static List<Point> simpleConvex(Point[] points){

        LinkedList<Point> Liste = new LinkedList<>();
        boolean valid;
        boolean connectionFound = false;
        int lastPointIndex = 0;
        Point p1;
        Point p2;

        //not sure about this one
       /* if(points.length<=2){
            return null;
        }*/
        //Schleife: punkte p, q bestimmen
        for(int i = 0; i < points.length; i++){
            if(points[i] == null){
                System.out.println("Invalid point has been found. Skipping Point..");
                continue;
            }
            for(int j = 0; j < points.length; j++){
                if(points[j] == null){
                    System.out.println("Invalid point has been found. Skipping Point..");
                    continue;
                }
                //wenn p!=q
                //valid = true
                if(i != j) {
                    p1 = points[i];
                    p2 = points[j];
                    valid = checkIfNotSamePoint(p1, p2);
                    if (valid) {
                        //wenn r links von pq liegt = valid false
                        valid = checkIfNoOneOnLeftSide(p1, p2, points);
                    }
                    //wenn valid = true, pq zu E hinzufügen
                    if (valid == true) {
                        Liste.add(p1);
                        Liste.add(p2);
                        lastPointIndex = j;
                        connectionFound = true;
                        break;
                    }
                }
            }
            if(connectionFound == true){
                break;
            }
        }

        // Ab hier ab dem zweiten hinzugefügten Punkt starten
        while(!Liste.isEmpty()&&Liste.getFirst()!=Liste.getLast()){
            boolean foundPair = false;
            Point lastPoint = Liste.getLast();
            for(int i = 0; i<points.length; i++){
                //catching invalid points
                if(points[i] == null){
                    System.out.println("Invalid point has been found. Skipping Point..");
                    continue;
                }

                if(i!=lastPointIndex && checkIfNotSamePoint(lastPoint, points[i])){
                    if(checkIfNoOneOnLeftSide(lastPoint,points[i],points)){
                        Liste.add(points[i]);
                        lastPointIndex = i;
                        foundPair = true;
                        break;
                    }
                }

            }
            if(!foundPair){
                return null;
            }
        }
        return Liste;
    }

    public static boolean checkIfNotSamePoint(Point p1, Point p2){

        if (p1.get(0) != p2.get(0) || p1.get(1) != p2.get(1)) {
            return true;
        }
        /*System.out.println("Same Point was found!: "+ "Pwert1: " + p1.get(0) + ", "+ p1.get(1)
                        +" ist gleich mit Pwert2: " + p2.get(0) +", "+p2.get(1));*/
        return false;
    }

    public static boolean checkIfNoOneOnLeftSide(Point p1, Point p2, Point[]P){
        double d;
        for(int l =0; l < P.length; l++) {
                if (p1.equals(P[l]) || p2.equals(P[l])) {
                    continue;
                }
                else{
                    d = ((P[l].get(0) - p1.get(0)) * (p2.get(1) - p1.get(1))) - ((P[l].get(1) - p1.get(1)) * (p2.get(0) - p1.get(0)));
                    if(d < 0){
                        return false;
                    }
                }
        }
        return true;
    }

    public static Point[] randomlyGeneratedPointsInTriangleSolvedWithGeometry(int n, int lowerBound, int upperBound) {

        Triangle dreieck = new Triangle(2, new Point(2, 10,10), new Point(2, 100,10), new Point(2, 10,100));
        //createRandom
        java.util.Random generator = new java.util.Random();
        Point[] points = new Point[n];

        for(int i=0; i < n; i++) {
            //randomly generating a point and putting it into points[i]
            Point candidate = new Point(2, lowerBound
                    + (upperBound - lowerBound) * generator.nextDouble(),
                    lowerBound + (upperBound - lowerBound) * generator.nextDouble());
            points[i] = candidate;

            // d=(x−x1)(y2−y1)−(y−y1)(x2−x1)
            double d = (points[i].getX() -  dreieck.get()[1].get(0)) * (dreieck.get()[2].getY() - dreieck.get()[2].getX())
                    - (points[i].getY() - dreieck.get()[1].getY()) * (dreieck.get()[2].getX() - dreieck.get()[1].getX());

            //point is on wrong side(right Side)
            if(d > 0) {
                //f(x)=mx+b
                double m = -1;
                double b = 110;

                //calculate mirror
                //x+y-b*n
                double mirror = (points[i].getX() + (points[i].getY() - b) * m) / (1 + Math.pow(m, 2));
                points[i].setX(2 * mirror - points[i].getX()); //mirror on X Point
                points[i].setY(2 * mirror * m - points[i].getY() + 2 * b); //mirror on Y Point
            }
        }
        return points;
    }

    public static Point[] randomlyGeneratedPointsInTriangleSolvedWithTotalRandomness(int n, int lowerBound, int upperBound){

        //create Triangle
        Triangle dreieck = new Triangle(2, new Point(2, 10,10), new Point(2, 10,100), new Point(2, 100,10));

        //createRandom
        java.util.Random generator = new java.util.Random();
        Point[] points = new Point[n];
        int i = 0;
        int counter = 0;
        while(i < n){
            Point[] check = new Point[1];
            Point candidate = new Point(2, lowerBound
                    + (upperBound - lowerBound) * generator.nextDouble(),
                    lowerBound + (upperBound - lowerBound) * generator.nextDouble());
            check[0] = candidate;

            if(checkIfNoOneOnLeftSide(dreieck.get()[1],dreieck.get()[2], check) &&
                    checkIfNoOneOnLeftSide(dreieck.get()[2],dreieck.get()[0], check) &&
                    checkIfNoOneOnLeftSide(dreieck.get()[0],dreieck.get()[1], check)){
                points[i] = check[0];
                i++;
            } else{
                System.out.println("Failed Attempts: "+ ++counter);
            }
        }
        return points;
    }
}
