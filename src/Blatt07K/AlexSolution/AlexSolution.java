package Blatt07K.AlexSolution;

import java.util.Random;

public class AlexSolution {

    private static final String MESSAGE = "Please provide one positive integer as argument.";

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Too few or many arguments. "  + MESSAGE);
            return;
        }
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.out.println("The value you provided is not a valid Integer. " + MESSAGE);
            return;
        }
        if(n<0){
            System.out.println("Integer must be positive. " + MESSAGE);
            return;
        }

        Random random = new Random();
        String a = randStr(n, random);
        String b = randStr(n, random);

        //String x = "BDCABA";
        //String y = "ABCBDAB";

        System.out.println("\nString 1: " + a + "\nString 2: " + b);

        longestCommonSubsequence(a, b);
    }

    public static void longestCommonSubsequence(String a, String b){

        long tStart, tEnd;

        System.gc();
        tStart = System.currentTimeMillis();
        int[][] chart = longestCommonSubsequenceChart(a,b);

        String subsquence = "";

        int i = a.length();
        int j = b.length();

        while (i > 0 && j > 0){
            if(a.charAt(i - 1) == b.charAt(j - 1)) {
                subsquence = a.charAt(i - 1) + subsquence;
                --i;
                --j;
            } else {
                if(chart[i - 1][j] > chart[i][j - 1]){
                    --i;
                } else {
                    --j;
                }
            }
        }
        tEnd = System.currentTimeMillis();

        System.out.println("\nLongest Common Subsequence:\nLength: " + chart[a.length()][b.length()]);
        System.out.println("Sequence: " + subsquence + "\nTime: " + (tEnd - tStart) + " ms");
    }


    private static int[][] longestCommonSubsequenceChart(String a, String b){

        int[][] chart = new int[a.length()+1][b.length()+1];
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                laengenberechnung(a, b, chart, i, j);
            }
        }
        return chart;
    }

    private static void laengenberechnung(String a, String b, int[][] chart, int i, int j){

        if(a.charAt(i - 1) == b.charAt(j - 1)){
            chart[i][j] = chart[i-1][j-1] + 1;
        } else {
            if(chart[i-1][j] >= chart[i][j-1]){
                chart[i][j] = chart[i-1][j];
            } else {
                chart[i][j] = chart[i][j-1];
            }
        }
    }

    public static String randStr(int n, Random r) {

        String alphabet =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder(n);
        while (--n >= 0) {
            res.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return res.toString();
    }

    //todo "Implementieren Sie diesen Algorithmus und stellen Sie die Entwicklung der Laufzeit Ihrer Implementierung
    // für zwei Zufallsfolgen der Länge n in Abhängigkeit von n grafisch dar." ???
}
