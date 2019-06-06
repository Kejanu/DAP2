package Blatt09K.AlexSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Rucksackproblem {

    //todo Comments

    public static final String MESSAGE = "Correct Usage : [ [ number of acticles ] [ boundary ] [ weight constant] ]";

    public static void main(String[] args){

        //Bearbeitung Eingabeparameter

        if(args.length != 3){
            System.out.println("Too few or many arguments. " + MESSAGE);
            return;
        }

        int n = 0;
        int w = 0;
        double p = 0;

        try{
            n = Integer.parseInt(args[0]);
            w = Integer.parseInt(args[1]);
            p = Double.parseDouble(args[2]);
        } catch (NumberFormatException e){
            System.out.println(MESSAGE);
            return;
        }

        Article[] articles = new Article[n];
        Random random = new Random();

        double upper = 1.25 * p;
        double lower = 0.8 * p;

        //Artikelerstellung

        for (int i = 0; i < articles.length ; i++) {
            double randomValue = lower + (upper - lower) * random.nextDouble();
            int weight = (int)  Math.round(randomValue);

            if (weight < lower){
                ++weight;
            }

            if (weight > upper){
                --weight;
            }
            articles[i] = new Article( weight, random.nextInt(900) + 100);
        }

        //Ausagabe

        long startDynamic, startGreedy, endDynamic, endGreedy;

        System.out.println("Articles:\n");
        for (int i = 0; i < articles.length; i++) {
            System.out.print(i +") " + articles[i].toString());
        }

        System.out.println("\nDynamic\n");

        System.gc();
        startDynamic = System.currentTimeMillis();
        dynamic(articles, w);
        endDynamic = System.currentTimeMillis();

        System.out.println("\n--------------------------------------------------------------\n\nGreedy:\n");

        startGreedy = System.currentTimeMillis();
        greedy(articles, w);
        endGreedy = System.currentTimeMillis();

        System.out.println("\n--------------------------------------------------------------\n\nLaufzeit:\nDynamic: "
        + (endDynamic - startDynamic) + " ms\nGreedy: " + (endGreedy - startGreedy) + " ms");

    }


    public static void dynamic(Article[] articles, int weight) {

        //Tabelle

        int[][] opt = new int[articles.length + 1][weight + 1];

        /*
        for (int j = 0; j < weight + 1; j++) {
            opt[0][j] = 0;
        }

         */
        for (int i = 0; i < articles.length + 1; i++) {
            for (int j = 0; j < weight + 1; j++) {
                opt[i][j] = optValue(opt, articles, i, j);
            }
        }

        for (int i = 0; i < opt.length; i++) {
            System.out.println(Arrays.toString(opt[i]));
        }

        //Ausgabe

        System.out.println(recursiveOutput(opt, articles));

    }


    private static int optValue(int[][] opt, Article[] articles, int i, int j) {

        if(i == 0){
            return 0;
        } else if (j < articles[i - 1].getWeight()){
            return opt[i - 1][j];
        } else {
            return Math.max(opt[i - 1][j] , articles[i - 1].getValue() + opt[i - 1][j - articles[i - 1].getWeight()]);
        }
    }

    public static String recursiveOutput(int[][] opt, Article[] articles){
        ArrayList<Integer> r = new ArrayList<>();
        recursiveOutput(opt,opt.length - 1, opt[1].length - 1, articles, r);
        Collections.sort(r);
        return  "\nOptimaler Wert: " + opt[opt.length - 1][opt[1].length - 1] + "\nIndizes:\n" + r.toString();
    }

    private static void recursiveOutput(int[][] opt, int i, int j, Article[] articles, ArrayList<Integer> list){

        if(i == 0){
            return;
        } else if(articles[i - 1].getWeight() > j){
            recursiveOutput(opt, i - 1, j, articles, list);
        } else if(opt[i][j] == articles[i - 1].getValue() + opt[i - 1][j - articles[i - 1].getWeight()]){
            list.add(i - 1);
            recursiveOutput(opt, i - 1, j - articles[i - 1].getWeight(), articles, list);
        } else {
            recursiveOutput(opt, i - 1, j, articles, list);
        }
    }

    public static void greedy(Article[] articles, int weight){
        //nicht optimal

        //Klasse RatioPair zum Verwalten der Verhältnisse der Waren

        class RatioPair implements Comparable<RatioPair>{
            private double ratio;
            private int index;

            private RatioPair(double r, int i){
                ratio = r;
                index = i;
            }

            public double getRatio() {
                return ratio;
            }
            public int getIndex() {
                return index;
            }

            public int compareTo(RatioPair other){

                if(ratio == other.ratio){
                    return 0;
                } else if(ratio > other.ratio){
                    return 1;
                } else
                    return -1;
            }
        }

        //Erstellen der Verhältnisse

        ArrayList<RatioPair> ratio = new ArrayList<RatioPair>();

        for (int i = 0; i < articles.length ; i++) {
            ratio.add(new RatioPair(articles[i].getValue() / articles[i].getWeight(), i));
        }

        //Auswahl der Elemente

        Collections.sort(ratio);

        int sumWeight = 0;
        int sumValue = 0;
        int i = articles.length - 1;
        ArrayList<Integer> r= new ArrayList<>();
        while (sumWeight <= weight){
            sumWeight = sumWeight + articles[ratio.get(i).getIndex()].getWeight();
            if(sumWeight > weight){
                break;
            }
            sumValue = sumValue + articles[ratio.get(i).getIndex()].getValue();

            r.add(ratio.get(i).getIndex());
            --i;
        }

        //Ausgabe

        Collections.sort(r);
        System.out.println("Optimaler Wert: " + sumValue + "\nIndizes:\n" + r.toString());
    }

}
