package Blatt08L.KevinSolution;

import Templates.InputValidation;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditDistance {

    private static final String PROPER_USAGE = "Proper Usage: StringA StringB [-o]";

    public static void main(String[] args) {
        if(!basicInputValidation(args))
            return;

        if (args.length == 3 && args[2].equals("-o")) {
            printEditOperations(args[0], args[1]);
        }
        else {
            System.out.println("Distance: " + distance(args[0], args[1]));
        }

    }

    /*

                t   e   s   t (a)
                0   1   2   3
      tst(b)0
            1
            2
            3
     */

    // https://stackoverflow.com/questions/10638597/minimum-edit-distance-reconstruction

    private static void printEditOperations(String a, String b) {
        System.out.println("Big Uff");
        int[][] memo = getMemo(a, b);
        ArrayList<String> getBackTrack =  backtrack(memo);

        // Right Movement: Insertion
        // Downward Movement: Deletion
        // Diagonal Movement: Substitution or Nothing
    }

    private static ArrayList<String> backtrack(int[][] memo) {
        ArrayList<String> operations = new ArrayList<>();
        int i = memo.length - 1; // Row
        int j = memo[0].length - 1;

        while (i >= 0 && j >= 0) {

            int upperLeft = 0;
            if (memo[i - 1][j - 1] != 0) {
                upperLeft = memo[i - 1][j - 1];
            }

            if (memo[i][j] == upperLeft) {
                operations.add("Equal");
                --j;
                --i;
            }
            else if (j - 1 < 0) {
                // We have no cell on the left
                for (int k = i; k >= 0; ++k) {
                    operations.add("Deletion");
                }
                break;
            }
            else if (i - 1 < 0) {
                // We have no cell on the upper
            }


        }
        return operations;
    }

    private static int[][] getMemo(String a, String b) {
        int aLength = a.length(); // Col
        int bLength = b.length(); // Row

        int memo[][] = new int[bLength + 1][aLength + 1];

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
        int memo[][] = getMemo(a, b);
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
