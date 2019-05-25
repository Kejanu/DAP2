package Blatt08L.AlexSolution;

import java.util.Arrays;

public class EditDistance {

    private static final String MESSAGE = "Proper usage: [ String String [   | -o ] ]";

    public static void main(String[] args) {

        if (args.length < 2 || args.length > 3) {
            System.out.println("Too few or many arguments " + MESSAGE);
            return;
        }


        if (args.length == 3 && args[2].equals("-o")) {

            String output = "";
            int d = distance(args[0], args[1], true);

        } else if (args.length == 3) {
            System.out.println("Last argument is invalid " + MESSAGE);
            return;
        } else {
            int d = distance(args[0], args[1], false);
            System.out.println("Gesamtkosten: " + d);
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

        if(output){

            System.out.println(output(chart, a, b));
        }

        /*if (output) {
            int i = a.length();
            int j = b.length();
            int count = a.length() + 1;
            String o = "";
            StringBuilder tempString = new StringBuilder(new String(b));


            while ( i > 1 || j > 1 ) {

                System.out.println("i = " + i + ", j = " + j);

                int value1 = 0;
                //int value2 = 0;
                int value3 = 0;
                int value4 = 0;

                if (i > 0 && j > 0 && a.charAt(i - 1) == b.charAt(j - 1)) {
                    value1 = chart[i - 1][j - 1];
                } else {
                    value1 = Integer.MAX_VALUE;
                }


               *//* if(i > 0 && j > 0) {
                    value2 = chart[i - 1][j - 1];
                } else {
                    value2 = Integer.MAX_VALUE;
                }

                *//*

                if(j > 0) {
                    value3 = chart[i][j - 1] + 1;
                } else {
                    value3 = Integer.MAX_VALUE;
                }

                if(i > 0) {
                    value4 = chart[i - 1][j];
                } else {
                    value4 = Integer.MAX_VALUE;
                }


                int value = Math.min(Math.min(value1, Integer.MAX_VALUE), Math.min(value3, value4));
                System.out.println("min "+ value);

                char oldChar = a.charAt(i - 1);
                char newChar = b.charAt(j - 1);

                if (value == value1) {

                    o = count + ") Kosten 0: " + oldChar + " an Position " + i + " --> " + tempString + "\n" + o;
                    --i;
                    --j;

                } else if (value == value4) {


                    o = count + ") Kosten 1: Loesche " + oldChar + " an Position " + i + " --> " + tempString
                            + "\n" + o;
                    tempString.insert(i - 1, oldChar);
                    --i;




                } else if (value == value3) {
                    o = count + ") Kosten 1: Fuege " + newChar + " an Position " + i + "  ein --> " + tempString
                            + "\n" + o;

                    tempString.deleteCharAt(i - 1);
                    --j;

                } else {

                    o = count + ") Kosten 1: Ersetze " + oldChar + " durch " + newChar + " an Position " + i
                            + " --> " + tempString + "\n" + o;
                    tempString.setCharAt(i - 1, oldChar);
                    --i;
                    --j;


                }
                --count;
            }


                o = "Loesung fuer " + a + " --> " + b + " mit Gesamtkosten " + chart[a.length()][b.length()] +
                        ":\n" + "=====================================================\n" + o;


                System.out.println(o);

        }*/




        /*

        if (output) {
            int i = 1;
            int j = 1;
            int count = 1;
            String o = "Loesung fuer " + a + " --> " + b + " mit Gesamtkosten " + chart[a.length()][b.length()] +
                    ":\n" + "=====================================================\n";
            int position = 1;

            StringBuilder tempString = new StringBuilder(new String(a));


            while ( //i < a.length() + 1|| j < b.length() + 1) {
                    position <= a.length()) {

                System.out.println("i = " + i + ", j = " + j);

                if (i == a.length() && j == b.length()) {
                    break;
                }

                int value1 = 0;
                int value3 = 0;
                int value4 = 0;

                if (i < a.length() && j < b.length()) {
                    value1 = chart[i + 1][j + 1];
                } else {
                    value1 = Integer.MAX_VALUE;
                }


                if (j < b.length()) {
                    value3 = chart[i][j + 1];
                } else {
                    value3 = Integer.MAX_VALUE;
                }

                if (i < a.length()) {
                    value4 = chart[i + 1][j];
                } else {
                    value4 = Integer.MAX_VALUE;
                }


                int value = Math.min(value1, Math.min(value3, value4));


                System.out.println("min "+ value);
                System.out.println("value " + value);
                System.out.println("value4 " + value4 );
                System.out.println("value3 " + value3 );
                System.out.println("value1 " + value1 );







                if (value == value1 && b.charAt(j - 1) == a.charAt(i - 1)) {

                    char oldChar = a.charAt(i - 1);


                    o = o + count + ") Kosten 0: " + oldChar + " an Position " + position + " --> " + tempString + "\n";

                    ++j;
                    ++i;
                    ++position;

                } else if (value == value1 && b.charAt(j - 1) != a.charAt(i - 1)) {

                    char oldChar = a.charAt(i - 1);
                    char newChar = b.charAt(j - 1);


                    tempString.setCharAt(i - 1, newChar);
                    o = o + count + ") Kosten 1: Ersetze " + oldChar + " durch " + newChar + " an Position " + position
                            + " --> " + tempString + "\n";

                    ++j;
                    ++i;
                    ++position;

                } else if (value == value3) {

                    char newChar = b.charAt(j - 1);

                    tempString.insert(i - 1, newChar);
                    o = o + count + ") Kosten 1: Fuege " + newChar + " an Position " + position + "  ein --> " + tempString
                            + "\n";

                    ++j;
                    ++position;


                } else {

                    char oldChar = a.charAt(i - 1);



                    tempString.deleteCharAt(i - 1);
                    o = o + count + ") Kosten 1: Loesche " + oldChar + " an Position " + position + " --> " + tempString
                            + "\n";

                    ++i;


                }
                ++count;
            }


            System.out.println(o);
        }
        */





        /*


        if (output) {
            int i = a.length();
            int j = b.length();
            int count = a.length() + 1;
            String o = "";
            StringBuilder tempString = new StringBuilder(new String(b));
            int position = a.length();


            while (i >= 1 || j >= 1) {

                if (i <= 0 && j <= 0) {
                    break;
                }

                System.out.println("i = " + i + ", j = " + j);

                int value1 = 0;
                //int value2 = 0;
                int value3 = 0;
                int value4 = 0;

                if (i > 0 && j > 0) {
                    value1 = chart[i - 1][j - 1];
                } else {
                    value1 = Integer.MAX_VALUE;
                }


               /* if(i > 0 && j > 0) {
                    value2 = chart[i - 1][j - 1];
                } else {
                    value2 = Integer.MAX_VALUE;
                }



                if (j > 0) {
                    value3 = chart[i][j - 1];
                } else {
                    value3 = Integer.MAX_VALUE;
                }

                if (i > 0) {
                    value4 = chart[i - 1][j];
                } else {
                    value4 = Integer.MAX_VALUE;
                }




                int value = Math.min(value1, Math.min(value3, value4));


                System.out.println("min "+ value);
                System.out.println("value " + value);
                System.out.println("value4 " + value4 );
                System.out.println("value3 " + value3 );
                System.out.println("value1 " + value1 );


                if (value == value1 && b.charAt(j - 1) == a.charAt(i - 1)) {

                    System.out.println("HEY LISTEN");

                    char oldChar = a.charAt(i - 1);

                    o = count + ") Kosten 0: " + oldChar + " an Position " + position + " --> " + tempString + "\n" + o;

                    --j;
                    --i;
                    --position;

                } else if (value == value1 && b.charAt(j - 1) != a.charAt(i - 1)) {

                    char oldChar = a.charAt(i - 1);
                    char newChar = b.charAt(j - 1);


                    o = count + ") Kosten 1: Ersetze " + oldChar + " durch " + newChar + " an Position " + position
                            + " --> " + tempString + "\n" + o;

                    tempString.setCharAt(i - 1, oldChar);
                    --j;
                    --i;
                    --position;


                } else if (value == value4) {

                    char oldChar = a.charAt(i - 1);


                    o = count + ") Kosten 1: Loesche " + oldChar + " an Position " + i + " --> " + tempString
                            + "\n" + o;
                    tempString.insert(i - 1, oldChar);
                    --i;
                    --position;


                } else if (value == value3) {

                    char newChar = b.charAt(j - 1);
                    o = count + ") Kosten 1: Fuege " + newChar + " an Position " + i + "  ein --> " + tempString
                            + "\n" + o;

                    tempString.deleteCharAt(i - 1);
                    --j;

                }
                --count;
            }


            o = "Loesung fuer " + a + " --> " + b + " mit Gesamtkosten " + chart[a.length()][b.length()] +
                    ":\n" + "=====================================================\n" + o;


            System.out.println(o);

        }
        */


        return chart[a.length()][b.length()];

    }



    public static String output(int[][] chart, String a, String b){


        StringBuilder tempString = new StringBuilder(new String(b));

        String o = output(chart, a, b, tempString, a.length(), b.length(), b.length());

        o= "Loesung fuer " + a + " --> " + b + " mit Gesamtkosten " + chart[a.length()][b.length()] +
                ":\n" + "=====================================================\n" + o;
        int count = 1;

        String t = o.replaceFirst("\0", Integer.toString(count++));

        /*

        do{
            String tmp = new String(t);

            t = o.replaceFirst("\0", Integer.toString(count++));

            o = tmp;


        } while (!t.equals(o));


         */

        return o;
    }

    private static String output(int[][] chart, String a, String b, StringBuilder tempString, int i, int j, int position){

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

                return  output(chart, a, b, tempString, i - 1, j - 1, position -1) + "\0" + ") Kosten 0: " + oldChar + " an Position " + position + " --> " + copy + "\n";

            } else if(minValue == value1 && a.charAt(i - 1) != b.charAt(j - 1)){

                StringBuilder copy = new StringBuilder(new String(tempString));
                char oldChar = a.charAt(i - 1);
                char newChar = b.charAt(j - 1);

                tempString.setCharAt(position - 1, oldChar);

                return output(chart, a, b, tempString, i - 1, j - 1, position -1) + "\0" + ") Kosten 1: Ersetze " + oldChar + " durch " + newChar + " an Position " + position
                        + " --> " + copy + "\n";

            } else if(minValue == value2) {

                StringBuilder copy = new StringBuilder(new String (tempString));
                char newChar = b.charAt(j - 1);

                tempString.deleteCharAt(position - 1);

                return output(chart, a, b, tempString, i, j - 1, position - 1) + "\0" + ") Kosten 1: Fuege " + newChar + " an Position " + position + "  ein --> " + copy
                        + "\n";
            } else {

                StringBuilder copy = new StringBuilder(new String(tempString));
                char oldChar = a.charAt(i - 1);

                tempString.insert(position - 1, oldChar);


                return output(chart, a, b, tempString, i - 1, j, position) + "\0" + ") Kosten 1: Loesche " + oldChar + " an Position " + position + " --> " + copy
                        + "\n";
            }







        }


    }
}






