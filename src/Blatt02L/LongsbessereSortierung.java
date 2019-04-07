package Blatt02L;
import java.util.Random;

public class LongsbessereSortierung {

    private static void fillArrayWithRandom(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt();
        }
    }

    public static void main(String[] args) {

        // Calcualte duration of Program

        long tStart, tEnd;
        tStart = System.currentTimeMillis();

        int[] array = new int[Integer.valueOf(args[0])];

        switch (args[1]) {
            case "rand":
                fillArrayWithRandom(array);
                break;

            case "auf":
                for (int i = 0; i < array.length; ++i)
                    array[i] = i;
                break;

            case "ab":
                for (int i = 0; i < array.length; ++i)
                    array[i] = array.length - i;
                break;

            default:
                fillArrayWithRandom(array);
        }

        tEnd = System.currentTimeMillis();

        System.out.println(tEnd - tStart);

        printArray(array);
        LongsbessereSortierung lol = new LongsbessereSortierung();
        //habs nicht getestet
    switch(args[2]){
        case"insert":
            lol.insertionSort(array);
        case "merge":
            lol.mergeSort(array);
    }
    for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
     }
//        int[] array = new int[]{5, 4, 3, 2, 1};
//        insertionSort(array);
//        printArray(array);
//        System.out.println(isSorted(array));
//        LongsbessereSortierung lol = new LongsbessereSortierung();
     /*   int[] array2 = {1,4,2,3,9,4,2,1,6};
        lol.mergeSort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
*/


    }

    public static void insertionSort(int[] array) {
        for (int j = 1; j < array.length; ++j) {
            int key = array[j];
            int i = j - 1;

            while (i >= 0 && array[i] > key) {
                array[i+1] = array[i];
                --i;
            }
            array[i+1] = key;
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] > array[i+1])
                return false;
        }
        return true;
    }

    public static void printArray(int[] array) {
        for (int i : array)
            System.out.print(i + " ");
        System.out.print("\n");
    }
    public static void mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length-1);
        assert isSorted(array);
    }

    public static void mergeSort(int[] array, int[]
            tmpArray, int left, int right){
        if(left < right) {
            int middle = (left+right)/2;
            mergeSort(array, tmpArray, left,middle);
            mergeSort(array, tmpArray,middle+1,right);
            merge(array, tmpArray, left,middle,right);
        }

    }
    public static void merge(int[] array, int[]tmpArray, int left, int middle, int right){
        int arrayCounter = left; //counter für array
        int tmpCounter = left; //counter für temp
        int rightStartArrayCounter = middle+1; // counter für rechtehälfte
        int grenze = right-left+1; //grenze zum auffüllen

        //größenvergleich der beiden Hälften
        while(arrayCounter <= middle && rightStartArrayCounter <= right){
            if(array[arrayCounter] <= array[rightStartArrayCounter]){
                tmpArray[tmpCounter] = array[arrayCounter];
                arrayCounter++;
                //System.out.println("TempArray: " + tmpArray[tmpCounter]);
            }
            else{
               tmpArray[tmpCounter] = array[rightStartArrayCounter];
               rightStartArrayCounter++;
            }
            tmpCounter++;
        }
        //System.out.println("TempCounter: "+ tmpCounter);

        //reste auffüllen links
        while(arrayCounter <= middle){
            tmpArray[tmpCounter] = array[arrayCounter];
            tmpCounter++;
            arrayCounter++;
        }

        //reste auffüllen rechts
        while(rightStartArrayCounter <= right){
            tmpArray[tmpCounter] = array[rightStartArrayCounter];
            tmpCounter++;
            rightStartArrayCounter++;
        }


        //rückwärts auffüllen, da Rekursion
        int backwards = right;

        //neuauffüllen des Arrays
       for(int i=0; i<grenze;i++){
            array[backwards] = tmpArray[backwards];
            backwards--;
        }

    }
}
