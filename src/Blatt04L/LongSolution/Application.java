package Blatt04L.LongSolution;
import Templates.InputValidation;

public class Application {
    public static void main(String[] args) {
        //randomGenerator
        java.util.Random generator = new java.util.Random();
        double randomLowerBound = -1000.0;
        double randomUpperBound = 1000.0;

        //X and Y points
        double[] inputs = new double[6];

        if(args.length == 0){
            for(int i = 0; i<6; i++){
                inputs[i] = randomLowerBound
                     + (randomUpperBound - randomLowerBound) * generator.nextDouble();
            }
        }
        else if(args.length == 6) {
            for (int i = 0; i < 6; i++) {
                if (!(InputValidation.parameterIsDouble(args[i]))) {
                    System.out.println("All parameters must be double.");
                    return;
                } else {
                    inputs[i] = Double.parseDouble(args[i]);
                }
            }
        }
        else{
            System.out.println("Atleast 6 Parameters must be given or none.");
            return;
        }

        //creating points for triangle
        Point[] trianglePoints = new Point[3];
        for(int i = 0; i < 6; i=i+2){
            double[] fill = {inputs[i], inputs[i+1]};
            trianglePoints[i/2] = new Point(2, fill);
        }

        Triangle dreieck = new Triangle(2, trianglePoints);
        if(!dreieck.validate()){
            System.out.println("Creating the triangle was not successful.\nSyntax: " +
                    "x1 y1 x2 y2 x3 y3. Exiting now.");
            return;
        }
        for (Point p : trianglePoints) {
            System.out.println("Point (X:"+p.getX()+", Y:"+p.getY()+")");
        }
        //calculate result
        System.out.println("Umfang des Dreiecks: "+dreieck.perimeter());

    }
}
