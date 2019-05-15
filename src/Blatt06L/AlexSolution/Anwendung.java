package Blatt06L.AlexSolution;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Anwendung {

    private static final String MESSAGE=" Please provide either 'Interval' or 'Lateness' as first and a valid path to an input file as second argument.";

    public static void main(String[] args) {

        //Fehlerbehandlung

        if (args.length != 2) {
            System.out.println(MESSAGE);
            return;
        }

        ArrayList<Job> jobs = new ArrayList<>();
        ArrayList<Interval> intervals = new ArrayList<>();
        String path = args[1];
        FileReader f;

        if (!args[0].equals("Interval") && !args[0].equals("Lateness")) {
            System.out.println(MESSAGE);
            return;
        }

        //Input

        try {
            f = new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be accessed." + MESSAGE);
            return;
        }

        BufferedReader file = new BufferedReader(f);
        String zeile;

        do {
            try {
                zeile = file.readLine();
            } catch (IOException e) {
                System.out.println("An error occured while reading.");
                return;
            }

            if (zeile != null) {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                int start, ende;

                try {
                    start = Integer.parseInt(st.nextToken());
                    ende = Integer.parseInt(st.nextToken());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. The file does not contain proper Intervals." + MESSAGE);
                    return;
                }

                if (args[0].equals("Interval")) {
                    intervals.add(new Interval(start, ende));
                } else {
                    jobs.add(new Job(start, ende));
                }
            }
        } while (zeile != null);

        //Ausgabe
        int n = 0;
        if(args[0].equals("Interval")){
            n = intervals.size();
        } else {
            n = jobs.size();
        }

        System.out.println("Bearbeite Datei " + "'" + path + "'");
        System.out.println(" ");
        System.out.println("Es wurden " + n +  " Zeilen mit folgendem Inhalt gelesen:");
        System.out.print("[");
        if(args[0].equals("Interval")){
            for (int i = 0; i < intervals.size(); ++i) {
                System.out.print(intervals.get(i).toString() + ", ");
            }
        } else {
            for (int i = 0; i < jobs.size(); ++i) {
                System.out.print(jobs.get(i).toString() + ", ");
            }
        }
        System.out.println("]");
        System.out.println(" ");
        System.out.println("Sortiert:");


        if (args[0].equals("Interval")) {
            Collections.sort(intervals);
            System.out.print("[");
            for (int i = 0; i < intervals.size(); ++i) {
                System.out.print(intervals.get(i).toString() + ", ");
            }
            System.out.println("]");
            System.out.println(" ");
            ArrayList<Interval> result = intervalScheduling(intervals);
            System.out.println("Berechnetes IntervalScheduling: ");
            System.out.print("[");
            for (Interval i : result) {
                System.out.print(i.toString() + ", ");
            }
            System.out.println("]");
        }else{
            System.out.print("[");
            Collections.sort(jobs);
            for (int i = 0; i < jobs.size(); ++i) {
                System.out.print(jobs.get(i).toString() + ", ");
            }
            System.out.println("]");
            System.out.println(" ");
            //Ausgabe: Startzeitpunkte der Aufgaben
            int[] start = latenessScheduling(jobs);
            System.out.println("Berechnetes LatenessScheduling: ");
            System.out.print("[");
            for (int i = 0; i < jobs.size(); ++i) {
                System.out.print(start[i] + ", ");
            }
            System.out.println("]");
            int maximumLateness = start[0] + jobs.get(0).getDuration() - jobs.get(0).getDeadline();
            for (int i = 1; i < jobs.size(); ++i){
                if(start[i] + jobs.get(i).getDuration() - jobs.get(i).getDeadline() > maximumLateness){
                    maximumLateness= start[i] + jobs.get(i).getDuration() - jobs.get(i).getDeadline();
                }
            }
            if(maximumLateness<0)
                maximumLateness = 0;
            System.out.println("Berechnete Maximale Verspätung: " + maximumLateness);

        }
    }

    public static ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals){

        //Annahme: Eingabe nach Intervallendpunkten sortiert


        ArrayList<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));
        int j=0;
        for(int i = 1; i< intervals.size(); i++){
            if(intervals.get(i).getStart() >= intervals.get(j).getEnd()){
                result.add(intervals.get(i));
                j=i;
            }
        }
        return result;
    }

    public  static  int[] latenessScheduling(ArrayList<Job> jobs){

        //Annahme: Eingabe nach Deadline Sortiert

        int[] result = new int[jobs.size()];
        int z = 0;
        for(int i = 0; i < jobs.size(); ++i){
            result[i] = z;
            z = z + jobs.get(i).getDuration();
        }
        return result;
    }
}
