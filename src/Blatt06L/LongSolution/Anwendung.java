package Blatt06L.LongSolution;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.StringTokenizer;

public class Anwendung {

    public static void main(String[] args) {
        ArrayList<Interval> inputs = new ArrayList<Interval>();

        //reading file.. I KNOW IT'S UGLY
        try {
            //shitty textfiles https://stackoverflow.com/questions/15281428/java-relative-path-of-text-file-in-main
            BufferedReader file = new BufferedReader(new FileReader("src/Blatt06L/Vorlagen/datenBsp1.zahlen"));
            String zeile = file.readLine();
            while (zeile != null) {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                int start = Integer.parseInt(st.nextToken());
                int ende = Integer.parseInt(st.nextToken());
                Interval ivall = new Interval(start, ende);
                inputs.add(ivall);

                zeile = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Folgende Daten wurden eingefügt: " + inputs.toString());

        //Wichtige Annahme:
        //Eingabe nach Intervallendpunkten sortiert
        Collections.sort(inputs);
        System.out.println("Folgende Daten wurden (hoffentlich) sortiert" + inputs.toString());

        //calculation and output
        ArrayList<Interval> result = intervalScheduling(inputs);
        System.out.println("Berechnetes IntervalScheduling: " + result.toString());
    }

    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals) {
        //https://moodle.tu-dortmund.de/pluginfile.php/885532/mod_resource/content/0/Vorlesung06-GierigeAlgorithmen-I.pdf
        ArrayList<Interval> result = new ArrayList<Interval>();
        int n = intervals.size();
        result.add(intervals.get(0));

        //s = startarray, f= finisharray
        Interval j = intervals.get(0);
        for (int counter = 1; counter < n; counter++) {
            Interval i = intervals.get(counter);
            if (i.getStart() >= j.getEnd()) {
                result.add(i);
                j = i;
            }
        }
        return result;
    }
}
