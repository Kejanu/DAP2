package Blatt03K.AlexSolution;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        long tStart, tEnd;
        final String MESSAGE = "No boundary provided";
        float boundary, result;
        int arraySize;
        int[] array;

        //3.1

        /*

        int[] testArray = new int[5000];
        fillDescending(testArray);
        tStart=System.currentTimeMillis();
        bubbleSort(testArray);
        tEnd=System.currentTimeMillis();
        System.out.println(tEnd-tStart);
        System.out.println(isSorted(testArray));
        //Result 82ms = 0,082sec

        */

        //3.2


        if (args.length != 1) {
            System.out.println(MESSAGE);
            return;
        }

        try {
            boundary = Float.parseFloat(args[0]);
        } catch (Exception e) {
            System.out.println(MESSAGE);
            return;
        }

        arraySize = 500;
        result = 0;

        System.out.println("Boundary: " + boundary + " sec");
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("BubbleSort");


        while (result <= boundary) {

            arraySize = arraySize * 2;
            array = new int[arraySize];
            fillDescending(array);

            tStart = System.currentTimeMillis();
            bubbleSort(array);
            tEnd = System.currentTimeMillis();

            result = (float) (tEnd - tStart) / 1000;

            System.out.println("Size: " + arraySize + ", Time: " + result + " sec");
            System.out.println("Sorted: " + isSorted(array));

            if (!(result <= boundary)) {
                System.out.println("(Termination)");
            }
            System.out.println("-------------------------------------------------------------------------------------");
        }
        System.out.println(" ");

        //Binary Search
        System.out.println("Binary Search");

        if (arraySize == 1000) {
            binarySearchExtended(0, 1000, boundary);
        } else {
            binarySearchExtended(arraySize / 2, arraySize, boundary);
        }
    }


    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j > i; j--) {
                if (arr[j - 1] > arr[j]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    public static void fillDescending(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr.length - 1 - i;
        }
    }

    public static boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void binarySearchExtended(int left, int right, float maxTime) {


        long tStart, tEnd;
        float seconds;

        int[] testArray = new int[(left + right) / 2];
        fillDescending(testArray);

        tStart = System.currentTimeMillis();
        bubbleSort(testArray);
        tEnd = System.currentTimeMillis();

        seconds = (float) (tEnd - tStart) / 1000;

        System.out.println("-------------------------------------------------------------------------------------");


        if (Math.abs(maxTime - seconds) < 0.1 || right == left) {
            System.out.println("Final size: " + testArray.length + " Time: " + seconds);

        } else {
            System.out.println("Size :" + testArray.length + " , Time needed to apply BubbleSort: " + seconds + " sec");
            //int middle=(left+right)/2;
            if (seconds > maxTime) {
                binarySearchExtended(left, testArray.length, maxTime);
            } else {
                binarySearchExtended(testArray.length + 1, right, maxTime);
            }
        }
    }
}
