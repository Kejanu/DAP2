package Blatt08L.AlexSolution;

import java.util.Arrays;

public class EditDistance {

    private  static String steps = "";
    private static final String MESSAGE = "Proper usage: [ String String [   | -o ] ]";

    public static void main(String[] args) {

        if (args.length < 2 || args.length > 3) {
            System.out.println("Too few or many arguments " + MESSAGE);
            return;
        }

        /*
        if(args.length == 2 && args[1].equals("-o")){
            System.out.println(MESSAGE);
            return;
        }
         */

        if (args.length == 3 && args[2].equals("-o")) {
            String output = "";
            int d = distance(args[0], args[1], true);

        } else if (args.length == 3) {
            System.out.println("Last argument is invalid " + MESSAGE);
            return;

        } else {
            int d = distance(args[0], args[1], false);
            System.out.println("Eingabe: " + args[0] + ", " + args[1] + "\nGesamtkosten: " + d);
        }
    }

    public static int distance(String a, String b, boolean output) {

        int[][] chart = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i < a.length() + 1; i++) {
            chart[i][0] = i;
        }

        for (int i = 1; i < b.length() + 1; i++) {
            chart[0][i] = i;
        }

        for (int i = 1; i < a.length() + 1; i++) {
            for (int j = 1; j < b.length() + 1; j++) {

                int value1;
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    value1 = chart[i - 1][j - 1];

                } else {
                    value1 = Integer.MAX_VALUE;
                }

                int value2 = chart[i - 1][j - 1] + 1;
                int value3 = chart[i][j - 1] + 1;
                int value4 = chart[i - 1][j] + 1;
                chart[i][j] = Math.min(Math.min(value1, value2), Math.min(value3, value4));
            }
        }

        for (int i = 0; i < chart.length; i++) {
            System.out.println(Arrays.toString(chart[i]));
        }

        System.out.print("\n");



        if(output){
            System.out.println(output(chart, a, b));
        }

        return chart[a.length()][b.length()];
    }

    public static String output(int[][] chart, String a, String b){

        StringBuilder tempString = new StringBuilder(new String(b));
        String o = output(chart, a, b, tempString, a.length(), b.length(), b.length(), steps);
        o= "\nLoesung fuer " + a + " --> " + b + " mit Gesamtkosten " + chart[a.length()][b.length()] +
                ":\n" + "=====================================================\n" + o;
        int count = 1;
        String t = o.replaceFirst("\0", Integer.toString(count++));

        while (!t.equals(o)){

           o = t;
           t = t.replaceFirst("\0",Integer.toString(count++));
        }

        int position = 1;

        for (int k = 0; k < steps.length() ; k++) {

            t = t.replaceFirst("\1", Integer.toString(position));
            if(steps.charAt(k) != 'L'){
                position++;
            }
        }

        return t;
    }

    private static String output(int[][] chart, String a, String b, StringBuilder tempString, int i, int j, int position, String s){

        if(i == 0 && j == 0){

            return "";

        } else {

            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            if(i > 0 && j > 0){
                value1 = chart[i - 1][j - 1];
            } else {
                value1 = Integer.MAX_VALUE;
            }
            if(i > 0){ //Löschung
                value3 = chart[i - 1][j];
            } else {
                value3 = Integer.MAX_VALUE;
            }
            if(j > 0){ //Einsetzung
                value2 = chart[i][j - 1];
            } else {
                value2 = Integer.MAX_VALUE;
            }

            int minValue = Math.min(value1, Math.min(value2, value3));

            if(minValue == value1 && a.charAt(i - 1) == b.charAt(j - 1)){

                StringBuilder copy = new StringBuilder(tempString);
                char oldChar = a.charAt(i - 1);
                steps = "E" + s;
                return  output(chart, a, b, tempString, i - 1, j - 1, position - 1, steps) + "\0" + ") Kosten 0: " + oldChar + " an Position " + "\1" + " --> " + copy + "\n";

            } else if(minValue == value1 && a.charAt(i - 1) != b.charAt(j - 1)){

                StringBuilder copy = new StringBuilder(new String(tempString));
                char oldChar = a.charAt(i - 1);
                char newChar = b.charAt(j - 1);
                tempString.setCharAt(position - 1, oldChar);
                steps = "E" + s;
                return output(chart, a, b, tempString, i - 1, j - 1, position - 1, steps) + "\0" + ") Kosten 1: Ersetze " + oldChar + " durch " + newChar + " an Position " + "\1"
                        + " --> " + copy + "\n";

            } else if(minValue == value2) {

                StringBuilder copy = new StringBuilder(new String (tempString));
                char newChar = b.charAt(j - 1);
                tempString.deleteCharAt(position - 1);
                steps = "F" + s;
                return output(chart, a, b, tempString, i, j - 1, position - 1 ,steps) + "\0" + ") Kosten 1: Fuege " + newChar + " an Position " + "\1" + " ein --> " + copy
                        + "\n";
            } else {

                StringBuilder copy = new StringBuilder(new String(tempString));
                char oldChar = a.charAt(i - 1);
                tempString.insert(position, oldChar);
                steps = "L" + s;
                return output(chart, a, b, tempString, i - 1, j, position, steps) + "\0" + ") Kosten 1: Loesche " + oldChar + " an Position " + "\1" + " --> " + copy
                        + "\n";
            }
        }
    }
}



