package Blatt06L.LongSolution;

import Templates.InputValidation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Anwendung {

    //code is inefficient will work on it once I have the time
    public static void main(String[] args) {
        ArrayList<Interval> inputs = new ArrayList<Interval>();
        ArrayList<Job> inputsJ = new ArrayList<Job>();
        //Komische Kevin validierung
        String[][] validStrings = new String[][]{
                {"Interval", "Lateness"},
                null
        };
        boolean isInterval;

        InputValidation validation = new InputValidation("Syntax: Interval|Lateness path");
        validation.setAcceptedStrings(validStrings);
        validation.setPattern(String.class, String.class);

        if (!validation.validate(args)) {
            return;
        }
        if(args[0] == "Interval"){
            isInterval = true;
        }
        else{
            isInterval = false;
        }
        //reading file.. I KNOW IT'S UGLY
        try {
            //shitty textfiles https://stackoverflow.com/questions/15281428/java-relative-path-of-text-file-in-main
            String example = "src/Blatt06L/Vorlagen/datenBsp1.zahlen";

            BufferedReader file = new BufferedReader(new FileReader(args[1]));

            String zeile = file.readLine();
            while (zeile != null) {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                int para1 = Integer.parseInt(st.nextToken());
                int para2 = Integer.parseInt(st.nextToken());
                if(isInterval) {
                    Interval ivall = new Interval(para1, para2);
                    inputs.add(ivall);
                }
                else{
                    Job lateSchedule = new Job(para1, para2);
                    inputsJ.add(lateSchedule);
                }

                zeile = file.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Wichtige Annahme:
        //Eingabe nach Intervallendpunkten sortiert

        //calculation and output
        if(args[0] == "Interval") {
            System.out.println("Folgende Daten wurden eingefügt: " + inputs.toString());
            ArrayList<Interval> result;
            Collections.sort(inputs);
            System.out.println("Folgende Daten wurden (hoffentlich) sortiert" + inputs.toString());
            result = intervalScheduling(inputs);
            System.out.println("Berechnetes IntervalScheduling: " + result.toString());
        }
        else {
            int[] resultJ;
            System.out.println("Folgende Daten wurden eingefügt: " + inputsJ.toString());
            Collections.sort(inputsJ);
            resultJ = latenessScheduling(inputsJ);
            System.out.println("Berechnetes IntervalScheduling: " + Arrays.toString(resultJ));
        }
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

    public static int[] latenessScheduling(ArrayList<Job> jobs) {
        int[] result = new int[jobs.size()];
        int z =0;
        for(int i = 0; i < jobs.size(); i++){
            result[i] = z;
            z = z + jobs.get(i).getDauer();
        }
        return result;
    }
}
