package Blatt05K.LongSolution;

import Templates.ArrayHelper;
import Templates.InputValidation;
import Templates.SortAlgorithms;

import java.util.Arrays;

public class Quicksort {
    public static void main(String[] args) {
        if (args.length != 1 || !InputValidation.parameterIsInteger(args[0])) {
            System.out.println("Parameter was not Integer or more than 1 parameter was inputted. REEEEEEE");
            return;
        }
        long tStart, tEnd, msecs;
        int n = Integer.parseInt(args[0]);

        int[] arr = new int[n];
        int[] cpy = new int[n];

        ArrayHelper.fillIntArrayWithRandom(arr);
        System.arraycopy(arr, 0, cpy, 0, arr.length);

        //quickSortMessung
        tStart = System.currentTimeMillis();
        quickSortAlgorithm(arr, 0, arr.length - 1);
        tEnd = System.currentTimeMillis();
        msecs = tEnd - tStart;
        System.out.println("Quicksort brauchte: " + msecs + " Millisekunden.");
        assert ArrayHelper.intArrayIsSorted(arr);

        //recopy
        System.arraycopy(cpy, 0, arr, 0, cpy.length);

        //MergeSortMessung
        tStart = System.currentTimeMillis();
        SortAlgorithms.mergeSort(arr);
        tEnd = System.currentTimeMillis();
        msecs = tEnd - tStart;
        System.out.println("Mergesort brauchte: " + msecs + " Millisekunden.");
        assert ArrayHelper.intArrayIsSorted(arr);

        //recopy
        System.arraycopy(cpy, 0, arr, 0, cpy.length);

        //InsertionSortMessung
        tStart = System.currentTimeMillis();
        SortAlgorithms.insertionSort(arr);
        tEnd = System.currentTimeMillis();
        msecs = tEnd - tStart;
        System.out.println("InsertionSort brauchte: " + msecs + " Millisekunden.");
        assert ArrayHelper.intArrayIsSorted(arr);

        //recopy
        System.arraycopy(cpy, 0, arr, 0, cpy.length);

        //BubbleSortMessung
        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(arr);
        tEnd = System.currentTimeMillis();
        msecs = tEnd - tStart;
        System.out.println("BubbleSort brauchte: " + msecs + " Millisekunden.");
        assert ArrayHelper.intArrayIsSorted(arr);
    }

    public static void quickSortAlgorithm(int[] arr, int l, int r) {
        if (l < r) {
            int i = l;
            int j = r;
            int pivot = arr[(l + r) / 2];
            while (i <= j) {
                while (arr[i] < pivot) {
                    i = i + 1;
                }
                while (arr[j] > pivot) {
                    j = j - 1;
                }
                if (i <= j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    i = i + 1;
                    j = j - 1;
                }
            }
            quickSortAlgorithm(arr, l, j);
            quickSortAlgorithm(arr, i, r);
        }
    }
}
