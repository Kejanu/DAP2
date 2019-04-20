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

        if (!InputValidation.parameterIsFloat(args[0])) {
            System.out.println("Error: Your input is no float-number. " + PROPER_USAGE_MESSAGE);
        }

        long maxTime = (long) (Float.parseFloat(args[0]) * 1000);

        int arrayLength = 500;
        int[] arr;

        long tStart, tEnd, tResult;
        do {
            arr = new int[arrayLength *= 2];
            ArrayHelper.fillIntArrayWithDescending(arr);

            tStart = System.currentTimeMillis();
            SortAlgorithms.bubbleSort(arr);
            tEnd = System.currentTimeMillis();
            tResult = tEnd - tStart;
            System.out.println("Current ArrayLength: " + arr.length + "\tTime used: " + tResult);
        } while (tResult < maxTime);

        int result = findFittingLengthWithBinarySearch(arr, maxTime, arr.length / 2, arr.length);
        System.out.println("Fitting length would be: " + result);
    }

    public static int findFittingLengthWithBinarySearch(int[] arr, long maxTime, int left, int right) {
        if (left == right)
            return left;

        int q = (left + right) / 2;

        long timeUsed = getTimeOfBubbleSortForArrayWithSpecifiedLength(q);

        if (timeUsed > maxTime + 100) {
            return findFittingLengthWithBinarySearch(arr, maxTime, left, q);
        }
        else if (timeUsed < maxTime - 100) {
            return findFittingLengthWithBinarySearch(arr, maxTime, q + 1, right);
        }
        else {
            return q;
        }
    }

    public static long getTimeOfBubbleSortForArrayWithSpecifiedLength(int length) {
        int[] arr = new int[length];
        ArrayHelper.fillIntArrayWithDescending(arr);
        long tStart, tEnd, tResult;

        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(arr);
        tEnd = System.currentTimeMillis();
        tResult = tEnd - tStart;
        System.out.println("Current array length: " + arr.length + "\tTime used: " + tResult);
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
