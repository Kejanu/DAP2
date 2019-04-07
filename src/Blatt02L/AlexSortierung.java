package Blatt02L;

import java.util.Random;

public class AlexSortierung {


    public static void main(String[] args) {
        /*
        int[] a= {1,6,3,9,7,3,7,4,2};
        int[] b= {1,6,3,9,7,3,7,4,2};
        insertionSort(a);
        for(int i=0; i<a.length; i++){
            System.out.print(" " + a[i]);
        }
        System.out.println("");
        System.out.println(isSorted(a));
        System.out.println(isSorted(b));
        */

        int a = 0;
        try {
            a = Integer.parseInt(args[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        Random rng = new Random();

        int[] array = new int[a];
        switch (args[2]) {
            case "rand": {
                for (int i = 0; i < array.length; i++) {
                    array[i] = rng.nextInt();
                }
                break;
            }

            case "": {
                for (int i = 0; i < array.length; i++) {
                    array[i] = rng.nextInt();
                }
                break;
            }

            case "auf": {
                for (int i = 0; i < array.length; i++) {
                    array[i] = i;
                }
                break;
            }

            case "ab": {
                int j = 0;
                for (int i = array.length - 1; i >= 0; --i) {
                    array[i] = j;
                    ++j;
                }
                break;
            }

            default:
                throw new IllegalArgumentException();
        }
        long tstart, tend, tmsecs;

        switch (args[1]){

            case "insert":{
                tstart = System.currentTimeMillis();
                insertionSort(array);
                tend = System.currentTimeMillis();
                break;
            }
            case "merge":{
                tstart = System.currentTimeMillis();
                mergeSort(array);
                tend = System.currentTimeMillis();
                break;
            }
            case "":{
                tstart = System.currentTimeMillis();
                mergeSort(array);
                tend = System.currentTimeMillis();
                break;
            }
            default:{
                throw new IllegalArgumentException();
            }
        }



        if (isSorted(array)) {
            System.out.println("Feld ist sortiert!");
        } else {
            System.out.println("Feld ist NICHT sortiert!");
        }

        if (a <= 100) {
            System.out.println("");
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
        }
        tmsecs = tend - tstart;
        System.out.println();
        System.out.println("Time: " + tmsecs + "ms");

    }


    public static void insertionSort(int[] array) {
        if (array != null && array.length > 1) {
            int key = 0;
            for (int j = 1; j < array.length; j++) {
                key = array[j];
                int i = j - 1;
                while (i >= 0 && array[i] > key) {
                    array[i + 1] = array[i];
                    --i;
                }
                array[i + 1] = key;
            }
        }
    }

    public static boolean isSorted(int[] array) {

        if (array == null) {
            throw new IllegalArgumentException();
        }

        if (array.length <= 1) {
            return true;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
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


    public static void merge(int[] array, int[] tmpArray, int left, int q, int right) { //nur Arrays, die die Bedingung erfüllen eingeben

        int a = left;
        int b = q + 1;
        int c = left;


        while (a <= q || b <= right) {

            if (a <= q && b <= right) {
                if (array[a] < array[b]) {
                    tmpArray[c] = array[a];
                    ++a;
                } else {
                    tmpArray[c] = array[b];
                    ++b;
                }
            } else if (a <= q) {
                tmpArray[c] = array[a];
                ++a;
            } else {
                tmpArray[c] = array[b];
                ++b;
            }
            ++c;
        }


        c = left;

        while (c <= right) {
            array[c] = tmpArray[c];
            ++c;
        }
    }
}





