package Templates;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{6, 5, 4, 3, 2, 1};
        printArray(arr);
        mergeSort(arr);
        printArray(arr);
    }

    public static void mergeSort(int[] array, int[] tmpArray, int low, int high) {
        // low == high, if we have 1 note
        if (low < high) {
            int middle = (low + high) / 2;
            // Sort left side
            mergeSort(array, tmpArray, low, middle);
            // Sort right side
            mergeSort(array, tmpArray, middle + 1, high);
            // Combine both sides
            merge(array, tmpArray, low, middle, high);
        }
    }

    public static void merge(int[] array, int[] tmpArray, int lIndex, int mIndex, int hIndex) {
        for (int i = lIndex; i <= hIndex; ++i) {
            tmpArray[i] = array[i];
        }

        int i = lIndex;
        int j = mIndex + 1;
        int k = lIndex;

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

    private static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
        //assert isSorted(array);
    }
}
