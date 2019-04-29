package Blatt04L.KevinSolution;

import Blatt04L.Interfaces.UniversalPoint;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.concurrent.ThreadLocalRandom;

public class ConvexHull {

    public static void main(String[] args) {
        Point[] rndPoints = IntStream.range(0, 1001)
         .mapToObj(i -> new Point(2, ThreadLocalRandom.current().nextDouble(10, 100),
                                        ThreadLocalRandom.current().nextDouble(10, 100)))
         .map(ConvexHull::getValidPoint)
         //.peek(System.out::println)
         .toArray(Point[]::new);

//        Point[] rndPoints = new Point[]{
//                new Point(2, 58, 16),
//                new Point(2, 40, 13),
//                new Point(2, 19, 54),
//                new Point(2, 35, 15),
//                new Point(2, 50, 42),
//                new Point(2, 87, 15),
//                new Point(2, 30, 29),
//                new Point(2, 46, 52),
//                new Point(2, 76, 27),
//                new Point(2, 69, 15)
//        };

        //Arrays.stream(rndPoints).forEach(System.out::println);

        makePointsUnique(rndPoints);

        LinkedList<UniversalPoint> ll = (LinkedList<UniversalPoint>) simpleConvex(rndPoints);

        if (ll == null) {
            System.out.println("List is null fml");
            return;
        }

        Point p1;
        Iterator<UniversalPoint> it = ll.iterator();
        p1 = (Point) it.next();

        while (it.hasNext()) {
            System.out.println(p1 + "\tconnected to\t" + (p1 = (Point) it.next()));
        }

        new Blatt04L.KevinSolution.ConvexVisualization(rndPoints, ll, 1000, 1000,10);
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

    public static List<UniversalPoint> simpleConvex(Point[] points) {
        // That points are in dimension = 2 has been checked before
        LinkedList<UniversalPoint> ll = new LinkedList<>();

        int i = 0;
        while (ll.isEmpty()) {
            Point p1 = points[i];
            for (int j = 0; j < points.length; ++j) {
                if (i == j)
                    continue;
                Point p2 = points[j];
                if (noPointOnLeft(p1, p2, points)) {
                    ll.add(p1);
                    ll.add(p2);
                    break;
                }
            }
            ++i;
        }

        Point last = (Point) ll.getLast();
        int k = 0;
        while (!ll.getFirst().equals(ll.getLast()) || ll.size() == 1) {
            Point p = points[k];
            if (last.equals(p)) {
                ++k;
                continue;
            }
            ++k;
            if (noPointOnLeft(last, p, points)) {
                ll.add(p);
                if (ll.getLast().equals(ll.getFirst())) {
                    return ll;
                }
                last = (Point) ll.getLast();
                k = 0;
            }
            if (k >= points.length)
                return null;
        }
        System.out.println("Last return");
        return null;
    }

    private static boolean noPointOnLeft(Point p1, Point p2, Point[] comparePoints) {
        //https://math.stackexchange.com/questions/274712/calculate-on-which-side-of-a-straight-line-is-a-given-point-located/274728#274728
        // Line from p1=(x1,y1) to p2=(x2,y2) a point p3=(x,y)
        // d=(x−x1) * (y2−y1) − (y−y1) * (x2−x1)
        for (int i = 0; i < comparePoints.length; ++i) {
            Point p3 = comparePoints[i];

            if (p3.equals(p1) || p3.equals(p2))
                continue;

            double position = (p3.getX() - p1.getX()) *
                              (p2.getY() - p1.getY()) -
                              (p3.getY() - p1.getY()) *
                              (p2.getX() - p1.getX());

            if (position < 0) {
                // Point is on the left / wrong side of the directional line
                return false;
            }
            // What to do if positon = 0 bzw. when point is on the directional line
        }
        return true;
    }
}
