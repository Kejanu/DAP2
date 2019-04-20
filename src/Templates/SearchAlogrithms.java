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

    public static int binarySearchWithTimeManagement(int[] arr, int searchVal, int left, int right, long currentTime, long maxTime) {
        long tStart, tEnd, tResult;
        tStart = System.nanoTime();
        if (left == right)
            return left;

        int q = (left + right) / 2;
        if (searchVal <= arr[q]) {
            tEnd = System.nanoTime();
            tResult = tEnd - tStart + currentTime;
            System.out.println("Current Time " + tResult + "\tMax Time " + maxTime);
//            if (maxTime - tResult <= 100)
//                throw new IllegalArgumentException();
            return binarySearchWithTimeManagement(arr, searchVal, left, q, tResult, maxTime);
        }
        else {
            tEnd = System.nanoTime();
            tResult = tEnd - tStart + currentTime;
            System.out.println("Current Time " + tResult + "\tMax Time " + maxTime);
//            if (maxTime - tResult <= 100)
//                throw new IllegalArgumentException();
            return binarySearchWithTimeManagement(arr, searchVal, q + 1, right, tResult, maxTime);
        }
    }
}
