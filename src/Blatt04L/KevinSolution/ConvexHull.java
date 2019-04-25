package Blatt04L.KevinSolution;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.concurrent.ThreadLocalRandom;

public class ConvexHull {

    public static void main(String[] args) {
//        Point[] rndPoints = IntStream.range(0, 4)
//                                     .mapToObj(i -> new Point(2, ThreadLocalRandom.current().nextInt(10, 100),
//                                                                    ThreadLocalRandom.current().nextInt(10, 100)))
//                                     .map(ConvexHull::getValidPoint)
//                                     //.peek(System.out::println)
//                                     .toArray(Point[]::new);

        Point[] rndPoints = new Point[]{
                new Point(2, 80, 10),
                new Point(2, 50, 20),
                new Point(2, 40, 30),
                new Point(2, 30, 50),
                new Point(2, 20, 40),
        };

        makePointsUnique(rndPoints);

        LinkedList<Point> ll = (LinkedList<Point>) simpleConvex(rndPoints);

        if (ll.size() % 2 != 0) {
            System.out.println("Something went wrong");
            return;
        }

        Point p1;
        Iterator it = ll.iterator();
        p1 = (Point) it.next();

        while (it.hasNext()) {
            System.out.println(p1 + "\tconnected to\t" + (p1 = (Point) it.next()));
        }

        new Visualization(rndPoints, ll, 1000, 1000);
    }

    private static void makePointsUnique(Point[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr.length; ++j) {
                if (i != j && arr[i].equals(arr[j])) {
                    System.out.println("A point that is not unique has been found. BIG YIKES");
                    arr[i] = getValidPoint(new Point(arr[j].dim(),
                            ThreadLocalRandom.current().nextDouble(10, 100),
                            ThreadLocalRandom.current().nextDouble(10, 100)));
                    --i;
                    break;
                }
            }
        }
    }

    private static Point getValidPoint(Point p) {
        // Line from p1=(x1,y1) to p2=(x2,y2) a point p3=(x,y)
        // d=(x−x1)(y2−y1)−(y−y1)(x2−x1)

        double d = (p.getX() - 100) * (100 - 10) - (p.getY() - 10) * (10 - 100);
        if (d > 0) {
            // Point is on the right side => Mirror point on the line
            // y = m*x + c
            // https://stackoverflow.com/a/3307181
            double m = -1;
            double c = 110;

            double mirror = (p.getX() + (p.getY() - c) * m) / (1 + Math.pow(m, 2));
            p.setX(2 * mirror - p.getX());
            p.setY(2 * mirror * m - p.getY() + 2 * c);
        }
        return p;
    }

    public static List<Point> simpleConvex(Point[] points) {
        // That points are in dimension = 2 has been checked before
        LinkedList<Point> pointsList = new LinkedList<>();

        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {

                boolean addToList = true;
                Point p1 = points[i];
                Point p2 = points[j];

                if (p1 == p2)
                    continue;

                for (int k = 0; k < points.length; ++k) {
                    if (k == i || k == j)
                        continue;

                    Point p3 = points[k];

                    //System.out.println("Point1: " + p1.toString() + "\t\tPoint2: " + p2.toString() + "\t\tPoint3: " + p3.toString());

                    //https://math.stackexchange.com/questions/274712/calculate-on-which-side-of-a-straight-line-is-a-given-point-located/274728#274728
                    // Line from p1=(x1,y1) to p2=(x2,y2) a point p3=(x,y)
                    // d=(x−x1)(y2−y1)−(y−y1)(x2−x1)
                    double position = (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()) - (p3.getY() - p1.getY()) * (p2.getX() - p1.getX());
                    if (position < 0) {
                        // Point is on the left / wrong side of the directional line
                        addToList = false;
                    }
                    //What to do, when point on the same directional line?

                }
                if (addToList) {
                    pointsList.add(p1);
                    pointsList.add(p2);
                }

            }
        }
        return pointsList;
    }
}
