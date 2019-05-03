package Templates;

import java.util.Random;

public class ArrayHelper {
    public static boolean intArrayIsSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i] > arr[i+1])
                return false;
        }
        return true;
    }

    public static void printIntArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.print("\n");
    }

    public static void printIntArrayWithIntLength(int[] arr) {
        for (int i : arr)
            System.out.print(i + "(" + Integer.toString(i).length() + ")" + " ");
        System.out.print("\n");
    }

    public static void fillIntArrayWithRandom(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = random.nextInt();
        }
    }

    public static void fillIntArrayWithAscending(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            arr[i] = i;
    }

    public static void fillIntArrayWithDescending(int[] arr) {
        for (int i = 0; i < arr.length; ++i)
            arr[i] = arr.length - 1 - i;
    }

    public static void printTwoIntArrays(int[] a, int[] b){
        if(a.length == b.length){
            for (int i = 0; i < a.length; i++){
                System.out.println(a[i] + " - " + b[i]);
            }
        }
    }
}
