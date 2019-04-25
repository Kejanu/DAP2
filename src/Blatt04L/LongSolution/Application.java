package Blatt04L.LongSolution;

import Templates.InputValidation;

public class Application {
    public static void main(String[] args) {

        //ParameterValidierung
        if(args.length != 6 && args.length != 0){
            System.out.println("Atleast 6 Parameters must be given or none");
        }

        //IntegerValidierung
        for(int i = 0; i<6; i++){
            if(InputValidation.parameterIsInteger(args[i])){
                System.out.println("Parameters must be Integer");
                return;
            }
        }

        Point[] trianglePoints = new Point[3];

        //creating points for Triangle
        for(int i = 0; i < 3; i++){
            double[] fill = {Double.parseDouble(args[i]), Double.parseDouble(args[i+1])};
            trianglePoints[i] = new Point(2, fill);
        }
        Triangle dreieck = new Triangle(2, trianglePoints);
        //create Umfang(?)
        System.out.println(dreieck.perimeter());

    }
}
