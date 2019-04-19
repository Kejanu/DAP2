package Blatt03K.KevinSolution;

import Templates.ArrayHelper;
import Templates.SortAlgorithms;
import Templates.InputValidation;

public class Main {

    private static final String PROPER_USAGE_MESSAGE = "Proper Usage: Time(float)";

    public static void main(String[] args) {

        if (args.length == 0) {
            calculateTimeFor50000();
        }

        if (!InputValidation.parameterIsFloat(args[0])) {
            System.out.println("Error: Your input is no float-number. " + PROPER_USAGE_MESSAGE);
        }

        float enteredTime = Float.parseFloat(args[0]);

        int arrayLength = 500;

        long tStart, tEnd;
        do {
            int[] arr = new int[arrayLength *= 2];
            ArrayHelper.fillIntArrayWithDescending(arr);

            tStart = System.currentTimeMillis();
            SortAlgorithms.bubbleSort(arr);
            tEnd = System.currentTimeMillis();
        } while ((tEnd - tStart) < enteredTime);
    }

    private static void calculateTimeFor50000() {
        int[] arr = new int[50000];
        ArrayHelper.fillIntArrayWithDescending(arr);

        long tStart, tEnd, tResult;

        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(arr);
        tEnd = System.currentTimeMillis();

        ArrayHelper.printIntArray(arr);

        tResult = tEnd - tStart;
        System.out.println(tResult);
    }
}
