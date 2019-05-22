package Heimuebung;

import java.util.Arrays;

public class Blatt06KevinAufg2 {
    public static void main(String[] args) {

        int[] chocolateWeights = {7, 10, 8, 2, 5, 1, 8, 16, 6};

        System.out.println(findMaxSumMemoization(chocolateWeights));
    }

    public static void maxSum(int[] arr) {
        int[] parent = new int[arr.length];
        parent[0] = -1;

        int lastSum = 0; // last sum encountered
        int lastPos = -1; // position of that last sum
        int currSum = arr[0]; // current sum
        int currPos = 0; // position of the current sum

        for (int i = 1; i < arr.length; ++i) {
            parent[i] = lastPos;  // save the last sum's position for this element

            // below this it is mostly similar to what you have done;
            // just keeping track of position too.
            int probableSum = Integer.max(arr[i] + lastSum, arr[i]);

            lastSum = currSum;
            lastPos = currPos;

            if (probableSum > currSum) {
                currSum = probableSum;
                currPos = i;
            }
        }
        System.out.println("Sum: " + currSum);

        // Print parent array; for debugging purposes
        System.out.println(Arrays.toString(parent));


        // logic to print the elements
        int p = parent[arr.length - 1];

        System.out.print("Indicez: ");
        System.out.print(arr.length - 1 + " ");
        while (p != -1) {
            System.out.print(p + " ");
            p = parent[p];
        }
    }

    private static int findMaxSumMemoization(int[] arr) {
        int[] sums = new int[arr.length];

        for (int i = 0; i < arr.length; ++i) {
            if (i == 0) {
                sums[0] = arr[0];
            }
            else if (i == 1) {
                sums[1] = Math.max(sums[0], arr[1]);
            }
            else {
                sums[i] = Math.max(sums[i - 2] + arr[i], sums[i - 1]);
            }
        }
        return sums[arr.length - 1];
    }
//
//
//    private static int findMaxSumRec(int[] arr, int i) {
//        if (i == 0)
//            return arr[0];
//
//        if (i == 1)
//            return Math.max(arr[0], arr[1]);
//
//        return Math.max(findMaxSumRec(arr, i - 2) + arr[i], findMaxSumRec(arr, i - 1));
//    }
//
//
//    private static int[] getIndizesOfBestChoice(int[] A) {
//        int[] result = new int[A.length / 2];
//        return result;
//    }

//    private static int findMaxSum(int[] arr)
//    {
//        int include = arr[0];
//        int exclude = 0;
//        int tmp;
//
//        for (int i = 1; i < arr.length; ++i)
//        {
//            tmp = include;
//            include = Math.max(include, exclude + arr[i]);
//            exclude = tmp;
//        }
//
//        return Math.max(include, exclude);
//    }


}
