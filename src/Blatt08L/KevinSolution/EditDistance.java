package Blatt08L.KevinSolution;

import Templates.InputValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EditDistance {
    private static final String PROPER_USAGE = "Proper Usage: StringA StringB [-o]";

    public static void main(String[] args) {
        if(!basicInputValidation(args))
            return;

        if (args.length == 3 && args[2].equals("-o")) {
//            args = new String[]{"baacda", "abace"};
//            args = new String[]{"abcdef", "azced"};
            args = new String[]{"a", "fff"};
            System.out.println("Your Inputs: " + args[0] + "\t" + args[1]);
            printEditOperations(args[0], args[1]);
        }
        else {
            System.out.println("Distance: " + distance(args[0], args[1]));
        }

    }

    // https://stackoverflow.com/questions/10638597/minimum-edit-distance-reconstruction

    private static void printEditOperations(String a, String b) {
        // Right Movement: Insertion
        // Downward Movement: Deletion
        // Diagonal Movement: Substitution or Nothing

        // We try to keep track of the edits upfront

        int aLength = a.length(); // Col
        int bLength = b.length(); // Row

        Helper[][] memo = new Helper[bLength + 1][aLength + 1];
        for (int i = 0; i < memo.length; ++i) {
            for (int j = 0; j < memo[0].length; ++j) {
                memo[i][j] = new Helper();
            }
        }

        // Fill first coloumn. If one String is empty only Option is to add
        // the amount of character occuring in the other String
        for (int j = 0; j <= aLength; ++j) {
            memo[0][j].setValue(j);
            if (j != 0) {
                memo[0][j].getOperations().addAll(memo[0][j - 1].getOperations());
                memo[0][j].getOperations().add(Operation.DELETE);

                // String
                memo[0][j].setStringOutput(memo[0][j - 1].getStringOutput() + "," +
                        "Lösche " + a.charAt(j - 1) + " an Position " + j);
            }
        }

        for (int i = 0; i <= bLength; ++i) {
            memo[i][0].setValue(i);
            if (i != 0) {
                memo[i][0].getOperations().addAll(memo[i - 1][0].getOperations());
                memo[i][0].getOperations().add(Operation.DELETE);

                //String
                // String
                memo[i][0].setStringOutput(memo[i - 1][0].getStringOutput() + "," +
                        "Lösche " + a.charAt(i - 1) + " an Position " + i);
            }
        }

        for (int i = 1; i <= bLength; ++i) {
            for (int j = 1; j <= aLength; ++j) {

                if (a.charAt(j - 1) == b.charAt(i - 1)) {
                    memo[i][j].setValue(memo[i - 1][j - 1].getValue());
                    memo[i][j].getOperations().addAll(memo[i - 1][j - 1].getOperations());
                    memo[i][j].getOperations().add(Operation.EQUAL);

                    // String
                    memo[i][j].setStringOutput(memo[i - 1][j - 1].getStringOutput() + "," + a.charAt(j - 1) + " an Position " + j);
                }
                else {
                    // New Part here
                    if (memo[i - 1][j - 1].getValue() == minOfThree(memo[i - 1][j - 1].getValue(), memo[i - 1][j].getValue(), memo[i][j - 1].getValue())) {
                        // memo[i - 1][j - 1] contains lowest value
                        memo[i][j].setValue(memo[i - 1][j - 1].getValue() + 1);
                        memo[i][j].getOperations().addAll(memo[i - 1][j - 1].getOperations());
                        memo[i][j].getOperations().add(Operation.SUBSTITUTE);

                        //String
                        memo[i][j].setStringOutput(memo[i - 1][j - 1].getStringOutput() + "," +
                                "Ersetze " + a.charAt(j - 1) + " durch " + b.charAt(i - 1) + " an Position " + j);
                    }
                    else if (memo[i - 1][j].getValue() == minOfThree(memo[i - 1][j - 1].getValue(), memo[i - 1][j].getValue(), memo[i][j - 1].getValue())) {
                        // memo[i - 1][j] contains lowest value
                        memo[i][j].setValue(memo[i - 1][j].getValue() + 1);
                        memo[i][j].getOperations().addAll(memo[i - 1][j].getOperations());
                        memo[i][j].getOperations().add(Operation.INSERT);

                        // String
                        memo[i][j].setStringOutput(memo[i - 1][j].getStringOutput() + "," +
                                "Füge " + b.charAt(i - 1) + " an Position " + j + " ein");
                    }
                    else {
                        // memo[i][j - 1] contains lowest value
                        memo[i][j].setValue(memo[i][j - 1].getValue() + 1);
                        memo[i][j].getOperations().addAll(memo[i][j - 1].getOperations());
                        memo[i][j].getOperations().add(Operation.DELETE);

                        // String
                        memo[i][j].setStringOutput(memo[i][j - 1].getStringOutput() + "," +
                                "Lösche " + a.charAt(j - 1) + " an Position " + j);
                    }
                }
            }
        }

        for (Helper[] arr : memo) {
            for (Helper help : arr) {
                System.out.print(help.getValue() + " ");
            }
            System.out.println();
        }

        // Get last Value Operations
//        System.out.println(memo[memo.length - 1][memo[0].length - 1].getOperations());
//        System.out.println(memo[memo.length - 1][memo[0].length - 1].getStringOutput());

        StringBuilder builder = new StringBuilder();
        String header = "\nLösung für \"" + a + "\" --> \"" + b + "\" mit Gesamtkosten " + memo[memo.length - 1][memo[0].length - 1].getValue() + ":";
        builder.append(header);
        builder.append("\n");
        builder.append(IntStream.range(0, header.length()).mapToObj(e -> "=").collect(Collectors.joining("")));
        builder.append("\n");

        int index = 1;
        String output = memo[memo.length - 1][memo[0].length - 1].getStringOutput().replaceFirst(",", "");
        for (String s : output.split(",")) {
            builder.append(index);
            builder.append(") Kosten ");
            if (s.contains("Füge") || s.contains("Lösche") || s.contains("Ersetze")) {
                builder.append("1: ");
            }
            else {
                builder.append("0: ");
            }
            builder.append(s);
            builder.append("\n");
            ++index;
        }

        System.out.println(builder.toString());
    }

    private static int[][] getMemo(String a, String b) {
        int aLength = a.length(); // Col
        int bLength = b.length(); // Row

        int[][] memo = new int[bLength + 1][aLength + 1];

        // Fill first coloumn. If one String is empty only Option is to add
        // the amount of character occuring in the other String
        for (int j = 0; j <= aLength; ++j) {
            memo[0][j] = j;
        }

        for (int i = 0; i <= bLength; ++i) {
            memo[i][0] = i;
        }

        for (int i = 1; i <= bLength; ++i) {
            for (int j = 1; j <= aLength; ++j) {

                if (a.charAt(j - 1) == b.charAt(i - 1)) {
                    memo[i][j] = memo[i - 1][j - 1];
                }
                else {
                    memo[i][j] = minOfThree(
                            memo[i - 1][j - 1],
                            memo[i - 1][j],
                            memo[i][j - 1]
                    ) + 1;
                }
            }
        }
        return memo;
    }

    private static int distance(String a, String b) {
        int[][] memo = getMemo(a, b);
        return memo[memo.length - 1][memo[0].length - 1];
    }

    private static int minOfThree(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private static boolean basicInputValidation(String[] args) {
        if (args.length == 0) {
            System.out.println(InputValidation.NO_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        if (args.length <= 1) {
            System.out.println(InputValidation.NOT_ENOUGH_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        if (args.length >= 4) {
            System.out.println(InputValidation.TOO_MANY_ARGUMENTS + PROPER_USAGE);
            return false;
        }
        return true;
    }
}
