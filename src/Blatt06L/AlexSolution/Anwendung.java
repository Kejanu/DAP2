package Blatt06L.AlexSolution;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class Anwendung {

    private static final String MESSAGE=" Please provide a valid path to an input file.";

    public static void main(String[] args){

        if(args.length != 1){
            System.out.println(MESSAGE);
            return;
        }

        ArrayList<Interval> intervals = new ArrayList<>();
        String path = args[0];
        FileReader f;

        try {
            f = new FileReader(path);
        }catch (FileNotFoundException e){
            System.out.println("File could not be accessed." + MESSAGE);
            return;
        }

        BufferedReader file = new BufferedReader( f );

        String zeile;


        do {
            try {
                zeile = file.readLine();
            }catch (IOException e){
                System.out.println("An error occured while reading.");
                return;
            }

            if(zeile != null) {
                StringTokenizer st = new StringTokenizer(zeile, ",");
                int start, ende;

                try {
                    start = Integer.parseInt(st.nextToken());
                    ende = Integer.parseInt(st.nextToken());
                }catch (NumberFormatException e){
                    System.out.println("Invalid input. The file does not contain proper Intervals." + MESSAGE);
                    return;
                }

                intervals.add(new Interval(start, ende));
            }
        } while (zeile != null);


       Collections.sort(intervals);
       ArrayList<Interval> result = intervalScheduling(intervals);

       for(Interval i : result){
           System.out.println(i.toString());
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
}
