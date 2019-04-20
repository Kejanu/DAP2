package Blatt04L.KevinSolution;
import java.util.stream.IntStream;
import java.util.concurrent.ThreadLocalRandom;

public class Application {

    private static final String PROPER_USAGE_MESSAGE = "Proper Usage: [x1 y1 x2 y2 x3 y3] OR no arguments at all";

    public static void main(String[] args) {



        if (args.length != 0 && args.length != 6) {
            System.out.println("You provided the wrong number of arguments. " + PROPER_USAGE_MESSAGE);
        }
        assert (args.length == 0 || args.length == 6);

        Point[] points = null;
        if (args.length == 6) {
            try {
                points = IntStream.range(0, args.length)
                        .filter(i -> i % 2 == 0)
                        .mapToObj(i -> new Point(2, Double.parseDouble(args[i]), Double.parseDouble(args[i+1])))
                        .toArray(Point[]::new);
            }
            catch (NumberFormatException nfe) {
                System.out.println("Your inputs weren't double values. " + PROPER_USAGE_MESSAGE);
                return;
            }
        }
        else {
            // Returns a pseudorandom double value between the specified origin (inclusive) and bound (exclusive).
            // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ThreadLocalRandom.html
            points = IntStream.range(0, 3)
                    .mapToObj(i -> new Point(2, ThreadLocalRandom.current().nextDouble(-1000, 1000),
                            ThreadLocalRandom.current().nextDouble(-1000, 1000)))
                    .toArray(Point[]::new);
        }

        Triangle tri = new Triangle(2, points);
        System.out.println(tri.perimeter());

        for (Point p : points) {
            System.out.println(p);
        }
    }
}
