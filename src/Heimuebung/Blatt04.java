package Heimuebung;

public class Blatt04 {
    public static void main(String[] args) {
//        int[] numbers = new int[]{2, 1, 0, 1, 2, 0, 1, 2};
        int[] numbers = new int[]{3, -2, -1, 1, 3, 1, -2, 3};
        System.out.println(findLongestIncreasing(numbers, numbers.length - 1, 0, 0));
    }

    // https://stackoverflow.com/questions/35978058/divide-and-conquer-algorithm-for-longest-increasing-consecutive-sequence-in-an-a

    public static int findLongestIncreasing(int[] arr, int index, int currentLength, int maxLength) {
        if (arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        if (index == 0) {
            return currentLength > maxLength ? currentLength + 1 : maxLength + 1;
        }

        if (arr[index] > arr[index - 1]) {
            return findLongestIncreasing(arr, index - 1, currentLength + 1, maxLength);
        }
        else {
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
            currentLength = 0;
            return findLongestIncreasing(arr, index - 1, currentLength, maxLength);
        }
    }

    public static int findNumber(int[] arr, int index, int number) {
        if (arr.length == 0) return 0;
        if (arr.length == 1 && arr[index] == number) return 1;
        if (index == arr.length - 1) return arr[index] == number ? 1 : 0;

        return arr[index] == number ? findNumber(arr, index + 1, number) + 1 : findNumber(arr, index + 1, number);
    }
}
