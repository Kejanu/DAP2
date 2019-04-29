package Blatt04L.AlexSolution;

import java.util.Random;

public class Application {

    public static void main(String[] args){


        if(args.length !=6 && args.length!=0){
            System.out.println("Either 0 or 6 arguments allowed.");
            return;
        }
        Triangle triangle;
        Point[] points= new Point[3];


        if(args.length==0){

            Random random= new Random();
            for(int i=0; i<points.length; i++){
                points[i]= new Point(2,random.nextInt(2000)-1000 + random.nextDouble(),random.nextInt(2000)-1000 + random.nextDouble());
            }

        }



        if(args.length==6){

            for(int i=0; i<args.length; i=i+2){

                try {
                    double a = Double.parseDouble(args[i]);
                    double b = Double.parseDouble(args[i + 1]);

                    points[i / 2] = new Point(2, a, b);
                }catch (Exception e){
                    System.out.println("Arguments not valid");
                    return;
                }
            }


        }


        triangle= new Triangle(2,points[0], points[1], points[2]);

        System.out.println("Point 1: ");
        points[0].printPoint();
        System.out.println("Point 2: ");
        points[1].printPoint();
        System.out.println("Point 3: ");
        points[2].printPoint();
        System.out.println("Perimeter: " + triangle.perimeter());




        /*
        Triangle t1= new Triangle(2,new Point(2,10,10), new Point(2,10,100), new Point(2,100,10));
        System.out.println(t1.enclosed(new Point(2,20,20)));
        System.out.println(t1.enclosed(new Point(2,2000,2000)));
        */
    }
}
