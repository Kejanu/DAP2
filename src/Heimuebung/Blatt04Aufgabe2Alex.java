package Heimuebung;

public class Blatt04Aufgabe2Alex {

    public static void main(String[] args){
        int[] a = {1,2,8,15};
        int[] b = {5,9,10,16};

        int[] c = {5,7,8,10};
        int[] d = {1,2,9,17};

        int[] e = {1,2,3,4};
        int[] f = {5,6,7,8};

        int[] g = {1,3,5,7};
        int[] h = {2,4,6,8};

        System.out.println(findNthSmallestElementInTwoSortedArrays(g,h));
    }




    public  static int findNthSmallestElementInTwoSortedArrays(int[] a, int [] b){

        if(a.length!= b.length){
            throw new IllegalArgumentException();
        }

        return findNthSmallestElementInTwoSortedArrays(a,b,0,a.length-1,0,b.length-1);

    }

    private static int findNthSmallestElementInTwoSortedArrays(int[] a, int[] b , int lA, int hA, int lB, int hB){

        //https://stackoverflow.com/questions/14535905/an-algorithm-to-find-the-nth-largest-number-in-two-arrays-of-size-n

        int n = hA-lA +1;
        /*if(a[lA + n/2-1] == b[lB + n/2-1]){
            return a[lA + n/2];
        }
        */
        if(hA == lA && hB == lB){
            return Math.min(a[lA], b[lB]);
        }
        if(a[lA + n/2-1] < b[lB + n/2-1]) {
            return findNthSmallestElementInTwoSortedArrays(a, b, lA + n / 2, hA, lB, lB + n / 2 - 1);
        } else{
            return findNthSmallestElementInTwoSortedArrays(a, b, lA, lA + n/2 - 1, lB + n/2, hB);
        }


    }
}
