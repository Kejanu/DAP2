package Templates;

public class SearchAlogrithms {

    public static int binarySearch(int[] arr, int searchVal, int left, int right) {
        if (left == right)
            return left;

        int q = (left + right) / 2;
        if (searchVal <= arr[q]) {
            return binarySearch(arr, searchVal, left, q);
        }
        else {
            return binarySearch(arr, searchVal, q + 1, right);
        }
    }
}
