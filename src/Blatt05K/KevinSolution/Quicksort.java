package Blatt05K.KevinSolution;

import Templates.ArrayHelper;
import Templates.InputValidation;
import Templates.SortAlgorithms;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Quicksort {

    private static final String PROPER_USAGE_MESSAGE = "Input: One Positive Integer e.g. 10";

    public static void main(String[] args) {
        if(!basicInputValidationSuccess(args))
            return;

        int arrayLength = Integer.parseInt(args[0]);
        int[] arr = IntStream.range(0, arrayLength)
                             .map(e -> ThreadLocalRandom.current().nextInt())
                             .toArray();

        int[] copy = new int[arr.length];
        long tStart, tEnd;

        System.arraycopy(arr, 0, copy, 0, copy.length);
        System.gc();
        tStart = System.currentTimeMillis();
        quicksort(copy, 0, copy.length - 1);
        tEnd = System.currentTimeMillis();
        assert ArrayHelper.intArrayIsSorted(copy);
        System.out.println("QuickSort: Used Time: " + (tEnd - tStart));

        System.arraycopy(arr, 0, copy, 0, copy.length);
        System.gc();
        tStart = System.currentTimeMillis();
        SortAlgorithms.mergeSort(copy);
        tEnd = System.currentTimeMillis();
        assert ArrayHelper.intArrayIsSorted(copy);
        System.out.println("MergeSort: Used Time: " + (tEnd - tStart));

        System.arraycopy(arr, 0, copy, 0, copy.length);
        System.gc();
        tStart = System.currentTimeMillis();
        SortAlgorithms.insertionSort(copy);
        tEnd = System.currentTimeMillis();
        assert ArrayHelper.intArrayIsSorted(copy);
        System.out.println("InsertionSort: Used Time: " + (tEnd - tStart));

        System.arraycopy(arr, 0, copy, 0, copy.length);
        System.gc();
        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(copy);
        tEnd = System.currentTimeMillis();
        assert ArrayHelper.intArrayIsSorted(copy);
        System.out.println("BubbleSort: Used Time: " + (tEnd - tStart));
    }

    private static boolean basicInputValidationSuccess(String[] args) {
        if (args.length <= 0) {
            System.out.println("You provided no arguments. Program aborting... " + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (args.length > 1) {
            System.out.println("You provided too many arguments. Program aborting... " + PROPER_USAGE_MESSAGE);
        }

        if (!InputValidation.parameterIsInteger(args[0])) {
            System.out.println("Your first argument wasn't an Integer. Program aborting... " + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (Integer.parseInt(args[0]) < 0) {
            System.out.println("Your first argument was a negative Integer. Program aborting... " + PROPER_USAGE_MESSAGE);
            return false;
        }
        return true;
    }

    private static void quicksort(int[] arr, int low, int high) {
        if (low < high) {
            int i = low;
            int j = high;
            int pivot = arr[(low + high) / 2];

            while (i <= j) {
                while (arr[i] < pivot)
                    ++i;
                while (arr[j] > pivot)
                    --j;

                if (i <= j) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    ++i;
                    --j;
                }
            }
            quicksort(arr, low, j);
            quicksort(arr, i, high);
        }
    }
}
