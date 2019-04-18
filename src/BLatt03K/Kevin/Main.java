package BLatt03K.Kevin;

import Templates.ArrayHelper;
import Templates.SortAlgorithms;

public class Main {
    public static void main(String[] args) {

        int[] arr = new int[20];
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
