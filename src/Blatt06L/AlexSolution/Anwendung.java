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

        if (args[0].equals("Interval")) {
            Collections.sort(intervals);
            ArrayList<Interval> result = intervalScheduling(intervals);
            for (Interval i : result) {
                System.out.println(i.toString());
            }
        }else{
            Collections.sort(jobs);
            //Ausgabe: Startzeitpunkte der Aufgaben
            int[] start = latenessScheduling(jobs);
            for (int i = 0; i < jobs.size(); ++i) {
                System.out.println(jobs.get(i).toString() + ", Started: " + start[i] );
            }
            int maximumLateness = start[0] + jobs.get(0).getDuration() - jobs.get(0).getDeadline();
            for (int i = 1; i < jobs.size(); ++i){
                if(start[i] + jobs.get(i).getDuration() - jobs.get(i).getDeadline() > maximumLateness){
                    maximumLateness= start[i] + jobs.get(i).getDuration() - jobs.get(i).getDeadline();
                }
            }
            System.out.println("Maximal Lateness: " + maximumLateness);

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
