package Blatt04L.LongSolution;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConvexHull {
    public static void main(String[] args) {
        //--test
        double[] p1values = {0, 0};
        Point p1 = new Point(2, p1values);
        double[] p2values = {1, 0};
        Point p2 = new Point(2, p2values);
        double[] p3values = {1, 2};
        Point p3 = new Point(2, p3values);
        double[] p4values = {2, 1};
        Point p4 = new Point(2, p4values);
        Point[] P = new Point[4];
        P[0] = p1;
        P[1] = p2;
        P[2] = p3;
        P[3] = p4;
        LinkedList<Point> result = (LinkedList<Point>) simpleConvex(P);
        Iterator<Point> it = result.iterator();
        while(it.hasNext()) {
            Point p = it.next();
            System.out.println("Koordinate: " + p.get(0) + "," + p.get(1) + "\n");
        }
    }

    public static List<Point> simpleConvex(Point[] P){

        LinkedList<Point> Liste = new LinkedList<>();
        boolean valid;
        //Schleife: punkte p, q bestimmen
        for(int i = 0; i < P.length; i++){
            for(int j = 0; j < P.length; j++){
                //wenn p!=q
                //valid = true
                if(i != j) {
                    valid = checkIfNotSamePoint(i, j, P);
                    if (valid) {
                        //wenn r links von pq liegt = valid false
                        valid = checkIfNoOneOnLeftSide(i, j, P);
                    }
                    //wenn valid = true, pq zu E hinzufügen
                    if (valid == true) {
                        Liste.add(P[i]);
                        Liste.add(P[j]);
                    }
                }
            }
        }
        //Hinzufügen der Eckknoten in eine doppelt verkettete Liste
        //some code.. Kevin HELP


        System.out.println("Hallo");
        return Liste;

    }

    public static boolean checkIfNotSamePoint(int i, int j, Point[] P){
        if (P[i].get(0) != P[j].get(0) || P[i].get(1) != P[j].get(1)) {
            System.out.println("ello");
            return true;
        }
        System.out.println("Pwert1: " + P[i].get(0) + ", "+ P[i].get(1)
                        +" Pwert2: " + P[j].get(0) +","+P[j].get(1) );
        return false;
    }

    public static boolean checkIfNoOneOnLeftSide(int i, int j, Point[]P){
        double d;
        for(int l =0; l < P.length; l++) {
                if (i == l || j == l) {
                    continue;
                }
                else{
                    d = ((P[l].get(0) - P[i].get(0)) * (P[j].get(1) - P[i].get(1))) - ((P[l].get(1) - P[i].get(1)) * (P[j].get(0) - P[i].get(0)));
                    if(d < 0){
                        return false;
                    }
                }
        }
        return true;
    }
}
