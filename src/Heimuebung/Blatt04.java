package Heimuebung;

public class Blatt04 {
    public static void main(String[] args) {
//        int[] numbers = new int[]{-2, -1, 1, 3, 1, -2, 3};
        int[] numbers = new int[]{3, -2, -1, 1, 3, 1, -2, 3};

        System.out.println(findLongestDivideAndConquer(numbers, 0, numbers.length - 1));
        //System.out.println(findLongestDivideAndConquer(numbers1, 0, numbers1.length - 1));
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
