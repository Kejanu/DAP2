package Blatt08L.KevinSolution;

import Templates.InputValidation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EditDistance {
    private static final String PROPER_USAGE = "Proper Usage: StringA StringB [-o]";

    enum OPERATIONS {
        INSERT,
        DELETE,
        SUBSTITUTE,
        EQUAL
    }

    public static void main(String[] args) {
        if(!basicInputValidation(args))
            return;

        if (args.length == 3 && args[2].equals("-o")) {
            args = new String[]{"baacda", "abace"};
//            args = new String[]{"abcdef", "azced"};
//            args = new String[]{"ab", "ab"};
            //System.out.println("Your Inputs: " + args[0] + "\t" + args[1]);

            printEditOperations3(args[0], args[1]);

        }
        else {
            System.out.println("Distance: " + distance(args[0], args[1]));
        }

    }

    private static void printEditOperations3(String a, String b) {
        a = "abcdef";
        b = "azced";

        ArrayList<OPERATIONS> operations = new ArrayList<>();
        int[][] memo = getMemo(a, b);
        System.out.println("Your Inputs: " + a + "\t" + b);

        for (int[] i : memo)
            System.out.println(Arrays.toString(i));

        int i = memo.length - 1;    // Row
        int j = memo[0].length - 1; // Col

        // Move to Right -> Delete
        // Move diagonal to lower right -> Substitute
        // Move down -> Insert

        // Algo completes, when we are in the upper-left corner
        while (i != 0 || j != 0) {

            // Check the upper-left
            if (i - 1 != - 1 && j - 1 != - 1) {
                if (memo[i][j] == memo[i - 1][j - 1]) {
                    operations.add(OPERATIONS.EQUAL);
                    --i;
                    --j;
                    continue;
                }
            }

            // Check if there is cell on the left
            if (j - 1 == - 1) {
                operations.add(OPERATIONS.INSERT);
                --i;
                continue;
            }

            // Check if there is cell on the upper
            if (i - 1 == - 1) {
                operations.add(OPERATIONS.DELETE);
                --j;
                continue;
            }

            int smallestVal = Math.min(Math.min(memo[i - 1][j - 1], memo[i - 1][j]), memo[i][j - 1] + 1);

            if (memo[i][j - 1] == smallestVal) {
                operations.add(OPERATIONS.DELETE);
                --j;
            }
            else if (memo[i - 1][j - 1] == smallestVal) {
                operations.add(OPERATIONS.SUBSTITUTE);
                --i;
                --j;
            }
            else if (memo[i - 1][j] == smallestVal) {
                operations.add(OPERATIONS.INSERT);
                --i;
            }
        }

        Collections.reverse(operations);
        for (OPERATIONS op : operations)
            System.out.println(op);

        int cost = memo[memo.length - 1][memo[0].length - 1];
        String header = "\nLösung für \"" + a + "\"" + " --> " + "\"" + b + "\" mit Gesamtkosten " + cost + ":";
        StringBuilder builder = new StringBuilder();

        builder.append(header).append("\n");
        builder.append(IntStream.range(0, header.length()).mapToObj(e -> "=").collect(Collectors.joining("")));
        builder.append("\n");

        int index = 0;
        String currentString = a;

        for (OPERATIONS op : operations) {
            builder.append(index + 1);
            builder.append(") Kosten ");

            switch (op) {
                case EQUAL:
                    builder.append(" 0: ");
                    builder.append(a.charAt(index)).append(" an Position ").append(index + 1);
                    break;

                case DELETE:
                    builder.append(" 1: ");
                    builder.append("Lösche ").append(a.charAt(index)).append(" an Positon ").append(index + 1);
                    break;

                case INSERT:
                    builder.append(" 1: ");
                    builder.append("Füge ").append(a.charAt(index))
                           .append(" an Position ").append(index + 1).append(" ein");
                    break;

                case SUBSTITUTE:
                    builder.append(" 1: ");
//                    builder.append("Ersetze ").append(a.charAt(index)).append(" durch ").append(b.charAt(index))
//                           .append(" an Position ").append(index + 1);
                    break;
            }
            ++index;
            builder.append(" --> ").append(currentString).append("\n");
        }


        System.out.println(builder.toString());
    }

    // https://stackoverflow.com/questions/10638597/minimum-edit-distance-reconstruction

    private static void printEditOperations2(String a, String b) {
        a = "abcdef";
        b = "azced";

        String position = "{POSITION}";

        ArrayList<String> operations = new ArrayList<>();
        int[][] memo = getMemo(a, b);
        System.out.println("Your Inputs: " + a + "\t" + b);

        for (int[] i : memo)
            System.out.println(Arrays.toString(i));

        int i = memo.length - 1;    // Row
        int j = memo[0].length - 1; // Col

        // Move to Right -> Delete
        // Move diagonal to lower right -> Substitute
        // Move down -> Insert

        // Algo completes, when we are in the upper-left corner
        while (i != 0 || j != 0) {

            // Check the upper-left
            if (i - 1 != - 1 && j - 1 != - 1) {
                if (memo[i][j] == memo[i - 1][j - 1]) {
//                    operations.add("Equal: " + a.charAt(j - 1) + " und " + b.charAt(i - 1) + " an Position " + (j + 1));
                    operations.add("Equal: " + a.charAt(j - 1) + " und " + b.charAt(i - 1) + " an Position " + (position));
                    --i;
                    --j;
                    continue;
                }
            }

            // Check if there is cell on the left
            if (j - 1 == - 1) {
//                operations.add("Insertion von " + b.charAt(i - 1) + " an Position " + (j + 1));
                operations.add("Insertion von " + b.charAt(i - 1) + " an Position " + (position));
                --i;
                continue;
            }

            // Check if there is cell on the upper
            if (i - 1 == - 1) {
//                operations.add("Deletion von " + a.charAt(j - 1) + " an Position " + (j + 1));
                operations.add("Deletion von " + a.charAt(j - 1) + " an Position " + (position));
                --j;
                continue;
            }

            int smallestVal = Math.min(Math.min(memo[i - 1][j - 1], memo[i - 1][j]), memo[i][j - 1] + 1);

            if (memo[i][j - 1] == smallestVal) {
//                operations.add("Deletion von " + a.charAt(j - 1) + " an Position " + (j + 1));
                operations.add("Deletion von " + a.charAt(j - 1) + " an Position " + (position));
                --j;
            }
            else if (memo[i - 1][j - 1] == smallestVal) {
//                operations.add("Substitute " + a.charAt(j - 1) + " durch " + b.charAt(i - 1) + " an Position " + (j + 1));
                operations.add("Substitute " + a.charAt(j - 1) + " durch " + b.charAt(i - 1) + " an Position " + (position));
                --i;
                --j;
            }
            else if (memo[i - 1][j] == smallestVal) {
//                operations.add("Insertion von " + b.charAt(i - 1) + " an Position " + (j + 1));
                operations.add("Insertion von " + b.charAt(i - 1) + " an Position " + position);
                --i;
            }
        }

        Collections.reverse(operations);
        for (String s : operations)
            System.out.println(s);

        int cost = memo[memo.length - 1][memo[0].length - 1];
        String header = "\nLösung für \"" + a + "\"" + " --> " + "\"" + b + "\" mit Gesamtkosten " + cost + ":";
        StringBuilder builder = new StringBuilder();

        builder.append(header).append("\n");
        builder.append(IntStream.range(0, header.length()).mapToObj(e -> "=").collect(Collectors.joining("")));
        builder.append("\n");

        int index = 1;
        int replacePos = 1;
        String doDirtyWork = a;

        for (String s : operations) {
            builder.append(index).append(") Kosten ");
            if (s.contains("Insertion") || s.contains("Deletion") || s.contains("Substitute")) {
                builder.append("1: ");
            }
            else {
                builder.append("0: ");
            }
            builder.append(s.replace("{POSITION}", Integer.toString(replacePos)));

            if (s.contains("Deletion")) {
                --replacePos;
            }

            builder.append(" --> ");

            if (s.contains("Insertion")) {
                if (replacePos == 1) {
                    // Add at the beginning of String
                    doDirtyWork = b.charAt(replacePos - 1) + doDirtyWork;
                    builder.append(doDirtyWork);
                }
            }

            if (s.contains("Substitute")) {
                doDirtyWork = doDirtyWork.substring(0, replacePos - 1) + b.charAt(replacePos - 1) + doDirtyWork.substring(replacePos);
                builder.append(doDirtyWork);
            }

            if (s.contains("Deletion")) {
                doDirtyWork = doDirtyWork.substring(0, replacePos) + doDirtyWork.substring(replacePos + 1);
                builder.append(doDirtyWork);
            }

            if (s.contains("Equal")) {
                builder.append(doDirtyWork);
            }


            builder.append("\n");
            ++index;
            ++replacePos;
        }

        System.out.println(builder.toString());
    }

    private static void printEditOperations(String a, String b) {
        // Right Movement: Insertion
        // Downward Movement: Deletion
        // Diagonal Movement: Substitution or Nothing

        // We try to keep track of the edits upfront

        int[][] memo = getMemo(a, b);

        for (int[] i : memo) {
            System.out.println(Arrays.toString(i));
        }

        int aLength = a.length(); // Col
        int bLength = b.length(); // Row

        // check till the end
        while (aLength != 0 && bLength != 0) {

            // if characters are same
            if (a.charAt(aLength - 1) == b.charAt(bLength - 1)) {
                System.out.println("Equal at Positon " + aLength + " " + a.charAt(aLength - 1));
                aLength--;
                bLength--;
            }

            // Replace
            else if (memo[aLength][bLength] == memo[aLength - 1][bLength - 1] + 1) {
                System.out.println("Change " + a.charAt(aLength - 1) + " to " + b.charAt(bLength - 1) +
                        " at Position: " + aLength);
                aLength--;
                bLength--;
            }

            // Delete the character
            else if (memo[aLength][bLength] == memo[bLength - 1][aLength] + 1) {
                System.out.println("Delete " + a.charAt(aLength - 1));
                aLength--;
            }

            // Add the character
            else if (memo[aLength][bLength] == memo[bLength][aLength - 1] + 1) {
                System.out.println("Add " + b.charAt(bLength - 1));
                bLength--;
            }
        }

//        System.out.println(builder.toString());
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
