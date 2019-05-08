package Heimuebung;

public class Blatt04_02_Kevin {
    public static void main(String[] args){
//        int[] a = {1,2,8,15};
//        int[] b = {5,9,10,16};
//
//        int[] c = {5,7,8,10};
//        int[] d = {1,2,9,17};
//
//        int[] e = {1,2,3,4};
//        int[] f = {5,6,7,8};
//
//        int[] g = {1,3,5,7};
//        int[] h = {2,4,6,8};

        int[] test1 = {1, 3, 6, 9};
        int[] test2 = {2, 4, 7, 8};

        int[] test12 = {1, 3, 4, 9};
        int[] test22 = {2, 6, 7, 8};

        System.out.println(findRank(test1, test2));
        System.out.println(findRank(test12, test22));
    }

    public  static int findRank(int[] arrA, int[] arrB){
        if(arrA.length!= arrB.length){
            throw new IllegalArgumentException();
        }
        return findRank(arrA,0,arrA.length - 1, arrB,0,arrB.length - 1);
    }

    //https://stackoverflow.com/questions/14535905/an-algorithm-to-find-the-nth-largest-number-in-two-arrays-of-size-n
    private static int findRank(int[] arrA, int lowA, int highA, int[] arrB, int lowB, int highB){
        int divider = (highA - lowA + 1) / 2;

        if (highA == lowA && highB == lowB) {
            return Math.min(arrA[lowA], arrB[lowB]);
        }

        if (arrA[lowA + divider - 1] < arrB[lowB + divider - 1]) {
            return findRank(arrA, lowA + divider, highA, arrB, lowB, lowB + divider - 1);
        }
        else{
            return findRank(arrA, lowA, lowA + divider - 1,  arrB, lowB + divider, highB);
        }
    }
}
