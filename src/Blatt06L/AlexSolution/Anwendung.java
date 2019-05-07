package Blatt06L.AlexSolution;

import java.util.ArrayList;
import java.util.Collections;

public class Anwendung {

    public static void main(String[] args){






       // Collections.sort(intervals);
    }

    public ArrayList<Interval> intervalScheduling(ArrayList<Interval> intervals){

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
