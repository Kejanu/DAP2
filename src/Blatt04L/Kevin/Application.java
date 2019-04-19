package Blatt04L.Kevin;

import java.util.Arrays;
import java.util.Random;

public class Application {

    private static final String PROPER_USAGE_MESSAGE = "Proper Usage: [x1 y1 x2 y2 x3 y3] OR no arguments at all";

    public static void main(String[] args) {
        if (args.length != 0 && args.length != 6) {
            System.out.println("You provided the wrong number of arguments. " + PROPER_USAGE_MESSAGE);
        }

        if (args.length == 6) {
            // Convert arguments to ints
            try {

            }
            catch (NumberFormatException nfe) {
                System.out.println("Your inputs weren't double values. " + PROPER_USAGE_MESSAGE);
                return;
            }


            //Triangle tri = new Triangle(2, Arrays.stream(args).mapToInt(Integer::parseInt).toArray());
        }
    }
}
