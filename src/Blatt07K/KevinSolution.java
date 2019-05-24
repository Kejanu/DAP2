package Blatt07K;

import Templates.InputValidation;
import Templates.RandomGenerator;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class KevinSolution {

    private static final String PROPER_USAGE = "Proper Usage: One none negative Integer or graph";

    public static void main(String[] args) {

        InputValidation val = new InputValidation(PROPER_USAGE);
        val.setPattern(String.class);
        val.setAcceptedStrings(new String[][]{{"graph", "$int"}});
        val.setNoneNegativeNumbers(true);
        if(!val.validate(args))
            return;

        int stringLength = 1;
        if (args[0].equals("graph")) {
            int Iterations = 13;
            double[] xValues = new double[Iterations];
            double[] yValues = new double[Iterations];

            for (int i = 0; i < Iterations; ++i) {
                System.out.println("StringLength: " + stringLength);
                xValues[i] = stringLength;
                yValues[i] = getTimeForAlgo(stringLength);
                stringLength *= 2;
            }

            XYChart chart = QuickChart.getChart("Algo Time", "StringLength", "Time", "Time(StringLength)", xValues, yValues);
            new SwingWrapper(chart).displayChart();
        }
        else {
            System.out.println("The calculation took: " + getTimeForAlgo(Integer.parseInt(args[0])) + " Milliseconds");
        }
    }

    private static long getTimeForAlgo(int stringLength) {
        String randomA = RandomGenerator.generateString(stringLength);
        String randomB = RandomGenerator.generateString(stringLength);

//        System.out.println("A: " + randomA + "\tB: " + randomB);

        long tStart, tEnd;
        System.gc();
        tStart = System.currentTimeMillis();
        int[][] memo = longestCommonSubsequence(randomA, randomB);
        tEnd = System.currentTimeMillis();

        System.out.println("Longest Subsequence int: " + memo[memo.length - 1][memo.length - 1]);
        String subStr = longestCommonSubsequenceString(memo, randomA, randomB);
        System.out.println("Longest Subsequence String: " + subStr);

        return tEnd - tStart;
    }

    private static String longestCommonSubsequenceString(int[][] memo, String a, String b) {
        String sequence = "";
        int indexA = memo.length - 1;
        int indexB = memo[0].length - 1;

        while (indexA > 0 && indexB > 0) {
            if (a.charAt(indexA) == b.charAt(indexB)) {
                sequence = a.charAt(indexA) + sequence;
                --indexA;
                --indexB;
            }
            else {
                if (memo[indexA - 1][indexB] > memo[indexA][indexB - 1])
                    --indexA;
                else
                    --indexB;
            }
        }
        return sequence;
    }

    private static int[][] longestCommonSubsequence(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();

        int[][] memo = new int[lengthA][lengthB];

        // First row and coloumn 0
        for (int i = 0; i < lengthA; ++i)
            memo[i][0] = 0;
        for (int j = 0; j < lengthB; ++j)
            memo[0][j] = 0;

        for (int i = 1; i < lengthA; ++i) {
            for (int j = 1; j < lengthB; ++j) {
                if (a.charAt(i) == b.charAt(j)) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    memo[i][j] = Math.max(memo[i][j - 1], memo[i - 1][j]);
                }
            }
        }
        return memo;
    }
}
