package Heimuebung;

public class Blatt04 {
    public static void main(String[] args) {
        //int[] numbers = new int[]{3, -2, -1, 1, 3, 1, -2, 3};
        int[] numbers1 = new int[]{};
        int[] numbers2 = new int[]{1};
        int[] numbers3 = new int[]{1, 2};
        int[] numbers4 = new int[]{4, 3, 2, 1};
        int[] numbers5 = new int[]{3, -2, -1, 1, 3, 1, -2, 3};

        System.out.println(longestAscendingOrder(numbers1, 0, 0));
        System.out.println(longestAscendingOrder(numbers2, 0, 0));
        System.out.println(longestAscendingOrder(numbers3, 0, 0));
        System.out.println(longestAscendingOrder(numbers4, 0, 0));
        System.out.println(longestAscendingOrder(numbers5, 0, 0));
    }

    public static int longestAscendingOrder(int[] arr, int index, int length) {
        if (arr.length == 0) return 0;
        if (arr.length == 1) return 1;

        if (index + 1 >= arr.length)
            return length + 1;

        if (arr[index] < arr[index + 1]) {
            return longestAscendingOrder(arr, index + 1, length + 1);
        }
        else {
            return longestAscendingOrder(arr, index + 1, length);
        }
    }
}
