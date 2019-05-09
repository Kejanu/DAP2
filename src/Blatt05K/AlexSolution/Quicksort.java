package Blatt05K.AlexSolution;

import Templates.ArrayHelper;
import Templates.SortAlgorithms;

public class Quicksort {

    static final String MESSAGE="Please provide one positive Integer as argument. ";

    public static void main(String[] args){

        if(args.length != 1){
            System.out.println("Number of arguments not sufficient. " + MESSAGE);
            return;
        }

        int arraySize = 0;

        try{
            arraySize = Integer.parseInt(args[0]);
        }catch(NumberFormatException e){
            System.out.println("Invalid input. " + MESSAGE);
            return;
        }

        if(arraySize<0){
            System.out.println("Negative input. " + MESSAGE);
            return;
        }



        int[] template= new int[arraySize];
        ArrayHelper.fillIntArrayWithRandom(template);

        long tStart, tEnd;



        //QuickSort
        int[] quickSortArray = ArrayHelper.copy(template);

        System.gc();

        tStart = System.currentTimeMillis();
        sortWithQuicksort(quickSortArray);
        tEnd = System.currentTimeMillis();

        assert ArrayHelper.intArrayIsSorted(quickSortArray);

        System.out.println("QuickSort");
        System.out.println("Time: " + (tEnd-tStart) + " ms");
        System.out.println("");


        //MergeSort
        int[] mergeSortArray = ArrayHelper.copy(template);

        System.gc();

        tStart = System.currentTimeMillis();
        SortAlgorithms.mergeSort(mergeSortArray);
        tEnd = System.currentTimeMillis();

        assert ArrayHelper.intArrayIsSorted(mergeSortArray);

        System.out.println("MergeSort");
        System.out.println("Time: " + (tEnd-tStart) + " ms");
        System.out.println("");


        //InsertionSort
        int[] insertionSortArray = ArrayHelper.copy(template);

        System.gc();

        tStart = System.currentTimeMillis();
        SortAlgorithms.insertionSort(insertionSortArray);
        tEnd = System.currentTimeMillis();

        assert ArrayHelper.intArrayIsSorted(insertionSortArray);

        System.out.println("InsertionSort");
        System.out.println("Time: " + (tEnd-tStart) + " ms");
        System.out.println("");


        //BubbleSort
        int[] bubbleSortArray = ArrayHelper.copy(template);

        System.gc();

        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(bubbleSortArray);
        tEnd = System.currentTimeMillis();

        assert ArrayHelper.intArrayIsSorted(bubbleSortArray);

        System.out.println("BubbleSort");
        System.out.println("Time: " + (tEnd-tStart) + " ms");
        System.out.println("");

    }


    public static void sortWithQuicksort(int[] arr){
        sortWithQuicksort(arr, 0, arr.length-1);
    }

    private static void sortWithQuicksort(int[] arr, int lowerBound, int upperBound){

        if(lowerBound < upperBound){
            int i = lowerBound;
            int j = upperBound;
            int pivot = arr[(lowerBound + upperBound) / 2];
            while (i <= j){
                while (arr[i] < pivot){
                    ++i;
                }
                while (arr[j] > pivot){
                    --j;
                }
                if(i <= j){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    ++i;
                    --j;
                }
            }
            sortWithQuicksort(arr, lowerBound, j);
            sortWithQuicksort(arr, i, upperBound );
        }
    }
}
