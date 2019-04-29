package Blatt04L.LongSolution;

import Templates.ConvexVisualization;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConvexHull {
    public static void main(String[] args) {
        //--test
    /*    double[] p1values = {0, 0};
        Point p1 = new Point(2, p1values);
        double[] p2values = {1, 1};
        Point p2 = new Point(2, p2values);
        double[] p3values = {0.5, 0.5};
        Point p3 = new Point(2, p3values);
        double[] p4values = {1, 3};
        Point p4 = new Point(2, p4values);
        double[] p5values = {2, 0};
        Point p5 = new Point(2, p5values);
        Point[] points = new Point[3];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        points[4] = p3;
        points[3] = p1;
        points[2] = p5;*/


        Point[] points = randomlyGeneratedPointsInTriangle(1000, 0, 200);
        //Point[] points = justPoints(10);
        LinkedList<Point> result = (LinkedList<Point>) simpleConvex(points);
        if(result == null){
            System.out.println("didn't work");
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

    public static Point[] randomlyGeneratedPointsInTriangle(int n, int lowerBound, int upperBound){

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
    public static List<Point> simpleConvex(Point[] points){

        LinkedList<Point> Liste = new LinkedList<>();
        boolean valid;
        boolean connectionFound = false;
        int lastPointIndex = 0;
        Point p1;
        Point p2;

        if(points.length<=2){
            return null;
        }
        //Schleife: punkte p, q bestimmen
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < points.length; j++){
                //wenn p!=q
                //valid = true
                if(i != j) {
                    p1 = points[i];
                    p2 = points[j];
                    valid = checkIfNotSamePoint(p1, p2, points);
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
        while(Liste.getFirst()!=Liste.getLast() && !Liste.isEmpty()){
            boolean foundPair = false;
            Point lastPoint = Liste.getLast();
            for(int i = 0; i<points.length; i++){
                if(i!=lastPointIndex){
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

    public static boolean checkIfNotSamePoint(Point p1, Point p2, Point[] P){
        if (p1.get(0) != p2.get(0) || p1.get(1) != p2.get(1)) {
            return true;
        }
        System.out.println("Same Point was found!: "+ "Pwert1: " + p1.get(0) + ", "+ p1.get(1)
                        +" ist gleich mit Pwert2: " + p2.get(0) +", "+p2.get(1));
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
}
