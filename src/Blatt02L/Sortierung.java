package Blatt02L;

import java.util.Random;

public class Sortierung {

    private static void fillArrayWithRandom(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt();
        }
    }

    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length-1);
        printArray(array);
        assert isSorted(array);
    }

    public static void mergeSort(int[] array, int[]tmpArray, int left, int right) {
        if (left < right) {
            int q = (left + right) / 2;

            mergeSort(array, tmpArray, left, q);
            mergeSort(array, tmpArray, q+1, right);

            merge(array, tmpArray, left, q, right);
        }
    }

    private static void merge(int[] array, int[] tmpArray, int left, int q, int right) {
        int[] tmp = new int[array.length + tmpArray.length];

        for (int i = 0; i < array.length; ++i) {
            tmp[i] = array[i];
        }

        for (int i = 0; i < tmpArray.length; i++) {
            tmp[i+array.length] = tmpArray[i];
        }
        array = tmp;
    }


    private static boolean parameterIsInteger(String param) {
        try {
            Integer.parseInt(param);
            return true;
        }
        catch(NumberFormatException nfe){
            return false;
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Proper usage: arraylength typeOfContent");
            System.exit(1);
        }

        int[] arr = null;

        if (parameterIsInteger(args[0])) {
            arr = new int[Integer.valueOf(args[0])];
        }
        else {
            System.out.println("Proper usage: arraylength typeOfContent");
            System.exit(2);
        }

        if (args.length == 1) {
            fillArrayWithRandom(arr);
        }
        else {
            switch (args[1]) {
                case "rand":
                    fillArrayWithRandom(arr);
                    break;

                case "auf":
                    for (int i = 0; i < arr.length; ++i)
                        arr[i] = i;
                    break;

                case "ab":
                    for (int i = 0; i < arr.length; ++i)
                        arr[i] = arr.length - 1 - i;
                    break;

                default:
                    System.out.println("Proper usage: arraylength typeOfContent(rand, auf, ab)");
                    System.exit(2);
            }
        }

        // Calcualte duration of Sort
        long tStart, tEnd;
        tStart = System.currentTimeMillis();

        //insertionSort(arr);
        mergeSort(arr);

        tEnd = System.currentTimeMillis();
        System.out.println("Time used: " + (tEnd - tStart));

        String result = isSorted(arr) ? "Feld sortiert!" : "Feld NICHT sortiert!";
        System.out.println(result);

        if (arr.length <= 100)
            printArray(arr);
    }

    private static void insertionSort(int[] array) {
        for (int j = 1; j < array.length; ++j) {
            int key = array[j];
            int i = j - 1;

            while (i >= 0 && array[i] > key) {
                array[i+1] = array[i];
                --i;
            }
            array[i+1] = key;
        }
    }

    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] > array[i+1])
                return false;
        }
        return true;
    }

    private static void printArray(int[] array) {
        for (int i : array)
            System.out.print(i + "(" + Integer.toString(i).length() + ")" + " ");
        System.out.print("\n");
    }

}
