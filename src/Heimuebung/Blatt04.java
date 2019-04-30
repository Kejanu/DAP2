package Heimuebung;

public class Blatt04 {
    public static void main(String[] args) {
        int[] numbers = new int[]{-2, -1, 1, 3, 1, -2, 3};

        printLongest(new int[]{-2, -1, 1, 3, 1, -2, 3});
        printLongest(new int[]{});
        printLongest(new int[]{1});
        printLongest(new int[]{1, 1});
        printLongest(new int[]{1, 2});
        printLongest(new int[]{1, 2, 3});
        printLongest(new int[]{3, 1, 2, 3, 4});
        printLongest(new int[]{1, 2, 3, 1, 2, 3, 4});
        printLongest(new int[]{-2, 3, 1, -1, 2, 4, 3});

        //System.out.println(findLongestDivideAndConquer(numbers, 0, numbers.length - 1));
        //System.out.println(findLongestDivideAndConquer(numbers1, 0, numbers1.length - 1));
    }

    public static void printLongest(int[] arr) {
        System.out.println(findLongestDivideAndConquer(arr, 0, arr.length - 1));
    }

    public static int findLongestDivideAndConquer(int[]arr, int low, int high) {
        if (arr.length == 0)
            return 0;

        if (low == high)
            return 1;

        int middle = low + (high - low) / 2;
        int lowerHalf = findLongestDivideAndConquer(arr, low, middle);
        int higherHalf = findLongestDivideAndConquer(arr, middle + 1, high);

        // Abbruch, wenn A[....n] > B[m....] ist. Arrays haben keinen "übergreifende" Folge
        //System.out.println("Comparing: " + arr[middle] + " " + arr[middle + 1]);
        if (arr[middle] >= arr[middle + 1]) {
            return Math.max(lowerHalf, higherHalf);
        }
        else {
            int walkToRight = middle + 1;
            while (walkToRight < high && arr[walkToRight] < arr[walkToRight + 1]) {
                ++walkToRight;
            }

            int walkToLeft = middle;
            while (walkToLeft > low && arr[walkToLeft] > arr[walkToLeft - 1]) {
                --walkToLeft;
            }

            return walkToRight - walkToLeft + 1;
        }
    }


//    public static int findLongestDivideAndConquer(int[]arr, int low, int high) {
//        if (low == high)
//            return 1;
//
//        int middle = (high + low) / 2;
//        int lowerHalf = findLongestDivideAndConquer(arr, low, middle);
//        int higherHalf = findLongestDivideAndConquer(arr, middle + 1, high);
//
//        // Abbruch, wenn A[....n] > B[m....] ist
//        if (arr[middle] > arr[middle + 1]) {
//            return Math.max(lowerHalf, higherHalf);
//        }
//        else {
//            int indexR = middle + 1;
//            while (indexR < arr.length -1 && arr[indexR] < arr[indexR + 1]) {
//                indexR++;
//            }
//
//            int indexL = middle;
//            while (indexL > 0 && (arr[indexL - 1] < arr[indexL])) {
//                --indexL;
//            }
//            int c = indexR - indexL + 1;
//            return Math.max(Math.max(lowerHalf, higherHalf), c);
//        }
//    }
}
