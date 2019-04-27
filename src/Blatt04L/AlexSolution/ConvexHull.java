package Blatt04L.AlexSolution;

import Blatt04L.Interfaces.UniversalPoint;
import Blatt04L.KevinSolution.Visualization;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class ConvexHull {

    public static void main(String[] args){
        Point[] rndPoints= generatePoints(1000, 10, 100);
        LinkedList<UniversalPoint> hullPoints= simpleConvexHull(rndPoints);
        new Visualization(rndPoints,hullPoints,600,600,6);
    }

    public static LinkedList<UniversalPoint> simpleConvexHull(Point[] rndPoints){

        LinkedList<UniversalPoint> hullPoints = new LinkedList<>();


        //first pair of points (O(n^3))
        for (int i=0; i<rndPoints.length; i++){
            for(int j=0; j<rndPoints.length; j++){
                if(i!=j) {
                    boolean valid = allRight(rndPoints[i], rndPoints[j], rndPoints);

                    /*
                    for (int r = 0; r < rndPoints.length; r++) {
                        if (r!=i && r!=j){
                            if(isLeft(rndPoints[i], rndPoints[j], rndPoints[r])){
                                valid=false;
                            }
                        }
                    }
                    */

                    if(valid){
                        hullPoints.add(rndPoints[i]);
                        hullPoints.add(rndPoints[j]);
                    }

                    if(!hullPoints.isEmpty()){
                        break;
                    }
                }
            }
            if(!hullPoints.isEmpty()){
                break;
            }
        }



        while (!hullPoints.getFirst().equals(hullPoints.getLast())){
            //System.out.println(hullPoints.getFirst().equals(hullPoints.getLast()));
            Point reference= (Point) hullPoints.getLast();

            for (int i=0; i<rndPoints.length; i++){
                if(!reference.equals(rndPoints[i])){

                    Iterator<UniversalPoint> it= hullPoints.iterator();
                    if(hullPoints.size()>2 && it.hasNext()){
                        it.next();
                    }
                    boolean toCheck= true;

                    while (it.hasNext()){
                        if(it.next().equals(rndPoints[i])){
                            toCheck=false;
                        }
                    }

                    if(toCheck){

                        boolean valid=allRight(reference,rndPoints[i], rndPoints);

                        /*
                        for (int j=0; j<rndPoints.length; j++){
                            if(!reference.equals(rndPoints[j]) && j!=i){
                                if(isLeft(reference, rndPoints[i], rndPoints[j])){
                                    System.out.println("V");
                                    valid=false;
                                }
                            }
                        }

                       */
                        if(valid){
                            hullPoints.add(rndPoints[i]);

                        }
                    }
                }

                if(!hullPoints.getLast().equals(reference)){

                    break;
                }
            }
        }


        return hullPoints;





    }

    public static boolean allRight(Point a, Point b, Point[] points){

        boolean atLeastOneLeft= false;

        for (int i=0; i<points.length; i++){
            if(!a.equals(points[i]) && !b.equals(points[i])){

                if(isLeft(a,b,points[i])){
                    atLeastOneLeft=true;
                }
            }

        }

        return !atLeastOneLeft;


    }

    public static boolean isLeft(Point a, Point b, Point c){

        /*
        To determine which side of the line from A=(x1,y1) to B=(x2,y2) a point P=(x,y) falls on you need to compute
        the value:-
        d=(x−x1)(y2−y1)−(y−y1)(x2−x1)
        If d<0 then the point lies on one side of the line, and if d>0 then it lies on the other side. If d=0 then
        the point lies exactly line.
        To see whether points on the left side of the line are those with positive or negative values compute the
        value for d for a point you know is to the left of the line, such as (x1−1,y1) and then compare the sign
        with the point you are interested in.
        */

        double d=(c.getX()-a.getX())*(b.getY()-a.getY())-(c.getY()-a.getY())*(b.getX()-a.getX());
        return d<0;


    }

    public static boolean isColinear(Point a, Point b, Point c){
        double d=(c.getX()-a.getX())*(b.getY()-a.getY())-(c.getY()-a.getY())*(b.getX()-a.getX());
        return d==0;
    }

    public static boolean pointsColinear(Point ref, Point[] points){
        for (int i=0; i<points.length; i++){
            for(int j=0; j<points.length; j++){
                if(!ref.equals(points[i]) && !ref.equals(points[j]) && i!=j && isColinear(ref,points[i],points[j])){


                    return true;
                }
            }
        }


        return false;

    }

    public static Point[] generatePoints(int n, int lowerBound, int upperBound){

        Triangle t= new Triangle
                (2,new Point(2,10,10), new Point(2,10,100), new Point(2,100, 10));


        Point[] points= new Point[n];
        for(int i=0; i<points.length; i++){
            points[i]= new Point(2,randomNumber(lowerBound,upperBound), randomNumber(lowerBound,upperBound));

            while (isLeft(new Point(2,10,100),new Point(2,100,10),points[i])){
                points[i]= new Point(2,randomNumber(lowerBound,upperBound), randomNumber(lowerBound,upperBound));
            }
        }


        //starke Laufzeitverschlechterung

        /*
        for (int i=0; i<points.length; i++){
            while (
                    isLeft(new Point(2,10,100),new Point(2,100,10),points[i])
                            || pointsColinear(points[i],points)
                    ){

                points[i]= new Point(2,randomNumber(lowerBound,upperBound), randomNumber(lowerBound,upperBound));
            }
        }
        */





        //prevent Points from being colinear

        /*
        for (int i = 0; i<points.length; i++){
            for (int j = 0; j<points.length; j++){
                for (int k = 0; k<points.length; k++){
                    if(i!=j && j!=k && k!=i){
                        while (isColinear(points[i], points[k], points[j]))
                    }
                }
            }
        }
        */






        return  points;

    }

    public static double randomNumber(int lowerBound, int upperBound){
        Random rnd= new Random();
        return rnd.nextInt(upperBound-1) + lowerBound + rnd.nextDouble();
    }
}
