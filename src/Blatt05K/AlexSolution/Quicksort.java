package Blatt05K.AlexSolution;

import Templates.ArrayHelper;

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

        int[] quicksortArray= new int[arraySize];

        ArrayHelper.fillIntArrayWithRandom(quicksortArray);

        long tStart, tEnd;

        System.gc();

        tStart = System.currentTimeMillis();
        sortWithQuicksort(quicksortArray);
        tEnd = System.currentTimeMillis();

        System.out.println("Sorted: " + ArrayHelper.intArrayIsSorted(quicksortArray));
        System.out.println("Time: " + (tEnd-tStart) + " ms");

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
