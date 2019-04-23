package Blatt03K.KevinSolution;

import Templates.ArrayHelper;
import Templates.SortAlgorithms;
import Templates.InputValidation;

public class Main {

    private static final String PROPER_USAGE_MESSAGE = "Proper Usage: Time(float)";

    public static void main(String[] args) {

        if (args.length == 0) {
            calculateTimeFor50000();
            return;
        }

        if (args.length > 1) {
            System.out.println("Error: You provided too many arguments. " + PROPER_USAGE_MESSAGE);
            return;
        }

        if (!InputValidation.parameterIsFloat(args[0])) {
            System.out.println("Error: Your input is no float-number. " + PROPER_USAGE_MESSAGE);
            return;
        }

        long maxTime = (long) (Float.parseFloat(args[0]) * 1000);

        int arrayLength = 500;
        long timeUsed;

        do {
            System.gc();
            timeUsed = getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(arrayLength *= 2);
        } while (timeUsed < maxTime);

        System.out.println("\nStarting binary search...");
        int result = findFittingLengthWithBinarySearch(maxTime, arrayLength / 2, arrayLength);
        System.out.println("Fitting length would be: " + result);
    }

    public static int findFittingLengthWithBinarySearch(long maxTime, int left, int right) {
        if (left == right)
            return left;

        int q = (left + right) / 2;

        long timeUsed = getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(q);

        if (timeUsed > maxTime + 100) {
            return findFittingLengthWithBinarySearch(maxTime, left, q);
        }
        else if (timeUsed < maxTime - 100) {
            return findFittingLengthWithBinarySearch(maxTime, q + 1, right);
        }
        else {
            return q;
        }
    }

    public static long getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(int length) {
        System.gc();
        int[] arr = new int[length];
        ArrayHelper.fillIntArrayWithDescending(arr);
        long tStart, tEnd, tResult;

        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(arr);
        assert ArrayHelper.intArrayIsSorted(arr);
        tEnd = System.currentTimeMillis();
        tResult = tEnd - tStart;
        System.out.println("Current array length: " + arr.length + "\tTime used: " + tResult / 1000.0f);
        return tResult;
    }

    private static void calculateTimeFor50000() {
        int[] arr = new int[50000];
        ArrayHelper.fillIntArrayWithDescending(arr);

        long tStart, tEnd, tResult;

        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(arr);
        tEnd = System.currentTimeMillis();

        //ArrayHelper.printIntArray(arr);

        tResult = tEnd - tStart;
        System.out.println(tResult);
    }
}
