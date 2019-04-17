package Blatt02L.KevinSolution;

public class SortAlgorithms {
    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
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

        int a = lIndex, b = mIndex + 1, c = lIndex;

        while (a <= mIndex && b <= hIndex) {
            if (tmpArray[a] <= tmpArray[b]) {
                array[c] = tmpArray[a];
                ++a;
            }
            else {
                array[c] = tmpArray[b];
                ++b;
            }
            ++c;
        }
        // Copy rest of left side of tmpArray to array
        while (a <= mIndex) {
            array[c] = tmpArray[a];
            ++c;
            ++a;
        }
    }

    public static void insertionSort(int[] array) {
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
}
