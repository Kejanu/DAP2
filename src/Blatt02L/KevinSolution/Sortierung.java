package Blatt02L.KevinSolution;

public class Sortierung {

    private final static String PROPER_USAGE_MESSAGE = "Proper usage: arraylength [insert|merge [auf|ab|rand]]";

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("You didn't provide any arguments " + PROPER_USAGE_MESSAGE);
            return;
        }

        if (args.length > 3) {
            System.out.println("You provided too many arguments " + PROPER_USAGE_MESSAGE);
            return;
        }

        if (!parameterIsInt(args[0]) || Integer.parseInt(args[0]) < 0) {
            System.out.println("You didn't provide a positive integer as your first argument. " + PROPER_USAGE_MESSAGE);
            return;
        }

        int[] arr = new int[Integer.valueOf(args[0])];

        // Initialize array with Integers
        // Also random, if only one argument (Integer) provided
        if (args.length < 3) {
            ArrayHelper.fillIntArrayWithRandom(arr);
        }
        else {
            switch (args[2]) {
                case "rand":
                    ArrayHelper.fillIntArrayWithRandom(arr);
                    break;

                case "auf":
                    ArrayHelper.fillIntArrayWithAscending(arr);
                    break;

                case "ab":
                    ArrayHelper.fillIntArrayWithDescending(arr);
                    break;

                default:
                    System.out.println("Your third argument isn't chosen correctly. " + PROPER_USAGE_MESSAGE);
                    System.exit(1);
            }
        }

        // Calcualte duration of Sort
        long tStart, tEnd;
        tStart = System.currentTimeMillis();

        if (args.length == 1) {
            SortAlgorithms.mergeSort(arr);
            assert ArrayHelper.intArrayIsSorted(arr);
        }
        else {
            if (args[1].equals("merge")) {
                SortAlgorithms.mergeSort(arr);
                assert ArrayHelper.intArrayIsSorted(arr);
            }
            else if(args[1].equals("insert")) {
                SortAlgorithms.insertionSort(arr);
                assert ArrayHelper.intArrayIsSorted(arr);
            }
            else {
                System.out.println("Your second argument isn't chosen correctly " + PROPER_USAGE_MESSAGE);
                return;
            }
        }
        tEnd = System.currentTimeMillis();
        System.out.println("Time used: " + (tEnd - tStart));


        String result = ArrayHelper.intArrayIsSorted(arr) ? "Feld sortiert!" : "Feld NICHT sortiert!";
        System.out.println(result);

        if (arr.length <= 100)
            ArrayHelper.printIntArray(arr);
    }

    private static boolean parameterIsInt(String param) {
        try {
            Integer.parseInt(param);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }
}
