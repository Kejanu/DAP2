package SimpleTests;

public class TestMerge {
    public static void main(String[] args) {
        int[] arr = new int[]{8, 4, 9, 6};
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
            System.out.println("Copied value: " + array[i]);
            tmpArray[i] = array[i];
        }

        int lower = lIndex;
        int upper = mIndex + 1;
        int arrayIndex = lIndex;

        System.out.print("Current MAIN-array:\t");
        printArray(array);
        System.out.print("Current TMP-array:\t");
        printArray(tmpArray);

        System.out.println("Starting of Main-While Loop");
        while (lower <= mIndex && upper <= hIndex) {
            System.out.println("Variablen:\nlIndex: " + lIndex + "  mIndex: " + mIndex + "  hIndex: " + hIndex + "\nlower: " + lower + "  upper: " + upper + "  arrayIndex: " + arrayIndex + "\n");
            System.out.print("Current TMP-array:\t");
            printArray(tmpArray);
            System.out.println("Is tmpArray["+lower+"] <= tmpArray["+upper+"]? bzw "+ tmpArray[lower] + " <= " + tmpArray[upper] +"? " + (tmpArray[lower] <= tmpArray[upper]));
            if (tmpArray[lower] <= tmpArray[upper]) {
                System.out.println("Execute if case");
                System.out.print("Current MAIN-array:\t");
                printArray(array);
                System.out.print("Current TMP-array:\t");
                printArray(tmpArray);
                System.out.println("array[" + arrayIndex + "] = tmpArray[" + lower + "] bzw array[" + arrayIndex + "] = " + tmpArray[lower]);
                array[arrayIndex] = tmpArray[lower];
                System.out.print("MAIN-array now:\t");
                printArray(array);
                ++lower;
                System.out.println("Increment lower");
            }
            else {
                System.out.println("Execute else case");
                System.out.print("Current MAIN-array:\t");
                printArray(array);
                System.out.print("Current TMP-array:\t");
                printArray(tmpArray);
                System.out.println("array[" + arrayIndex + "] = tmpArray[" + upper + "] bzw array[" + arrayIndex + "] = " + tmpArray[upper]);
                array[arrayIndex] = tmpArray[upper];
                System.out.print("MAIN-array now:\t");
                printArray(array);
                ++upper;
                System.out.println("Increment upper");
            }
            ++arrayIndex;
            System.out.println("Increment arrayIndex");
            System.out.println("One Main-While finished...\n");
            System.out.println("Is lower <= mIndex? " + (lower <= mIndex) + " AND Is upper <= hIndex? " + (upper <= hIndex) + "\n\n");
        }

        if (lower <= mIndex) {
            System.out.println("Execute copy rest of array\n");
        } else {
            System.out.println("Don't execute copy rest of array\n\n");
        }

        // Copy rest of left side of tmpArray to array
        while (lower <= mIndex) {
            System.out.print("Current MAIN-array:\t");
            printArray(array);
            System.out.print("Current TMP-array:\t");
            printArray(tmpArray);
            System.out.println("array[arrayIndex ("+ arrayIndex + ")] = tmpArray[lower ("+ lower + ")]");
            array[arrayIndex] = tmpArray[lower];
            System.out.print("Current MAIN-array:\t");
            printArray(array);
            System.out.print("Current TMP-array:\t");
            printArray(tmpArray);
            ++arrayIndex;
            ++lower;
            System.out.println("Increment arrayIndex and lower");
            if (!(lower <= mIndex))
                System.out.println("End of copy rest of array\n\n");
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
