package Blatt02L;

import java.util.Random;

public class Sortierung {

    private final static String PROPER_USAGE_MESSAGE = "Wrong Parameter. Proper usage: arraylength [insert|merge [auf|ab|rand]]";

    private static void fillArrayWithRandom(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt();
        }
    }

    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
        assert isSorted(array);
    }


    public static void mergeSort(int[] array, int[] tmpArray, int left, int right) {
        if (left < right) {
            int q = (left + right) / 2;
            mergeSort(array, tmpArray, left, q);
            mergeSort(array, tmpArray, q + 1, right);
            merge(array, tmpArray, left, q, right);
        }
    }

    public static void merge(int[] array, int[] tmpArray, int lIndex, int mIndex, int hIndex) {
        for (int i = lIndex; i <= hIndex; ++i)
            tmpArray[i] = array[i];

        int i = lIndex, j = mIndex + 1, k = lIndex;

        while (i <= mIndex && j <= hIndex) {
            if (tmpArray[i] <= tmpArray[j]) {
                array[k] = tmpArray[i];
                ++i;
            }
            else {
                array[k] = tmpArray[j];
                ++j;
            }
            ++k;
        }
        // Copy rest of left side of tmpArray to array
        while (i <= mIndex) {
            array[k] = tmpArray[i];
            ++k;
            ++i;
        }
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
        if (args.length == 0 || !parameterIsInteger(args[0])) {
            System.out.println(PROPER_USAGE_MESSAGE);
            System.exit(1);
        }

        int[] arr = new int[Integer.valueOf(args[0])];

        // Initialize array with Integers
        if (args.length <= 2) {
            fillArrayWithRandom(arr);
        }
        else {
            switch (args[2]) {
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
                    System.out.println(PROPER_USAGE_MESSAGE);
                    System.exit(1);
            }
        }

        // Calcualte duration of Sort
        long tStart, tEnd;
        tStart = System.currentTimeMillis();

        // Which Sort-Algorith should be used
        if (args.length <= 1 || args[1].equals("merge")) {
            mergeSort(arr);
        }
        else if(args[1].equals("insert")) {
            insertionSort(arr);
            assert isSorted(arr);
        }
        else {
            System.out.println(PROPER_USAGE_MESSAGE);
            System.exit(1);
        }

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
