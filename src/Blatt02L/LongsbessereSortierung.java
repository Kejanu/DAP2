package Blatt02L;


public class LongsbessereSortierung {


    private static void fillArrayWithRandom(int[] array) {
        // Den Generator erzeugen (als Seedwert wird die Systemzeit verwendet)
        java.util.Random numberGenerator = new java.util.Random();
        for (int i = 0; i < array.length; ++i) {
            // Wann immer man eine Zufallszahl braucht
            array[i] = numberGenerator.nextInt();
        }
    }

    //Mainmethode
    public static void main(String[] args) {
        long tStart, tEnd, msecs;
        // Beginn der Messung
        tStart = System.currentTimeMillis();

        if(!parameterIsInteger(args[0]) || Integer.parseInt(args[0]) <= 0){
            System.out.println("Uncorrect format. Syntax: Integer [rand|auf|ab]");
            System.exit(1);
        }
        int[] array = new int[Integer.parseInt(args[0])];

        //fillmethod
        if (args[2].equals("rand") || args[1].equals("")) {
            fillArrayWithRandom(array);
            insertionSort(array);
        }
        else if(args[2].equals("auf")){
            for(int i = 0; i < array.length; i++){
                array[i] = i;
            }
            insertionSort(array);
        }
        else if(args[2].equals("ab")){
            for(int i = array.length; i > 0; i--){
                array[i] = i;
            }
            insertionSort(array);
        }
        else{
            System.out.println("Invalid argument. Syntax: length [rand|auf|ab]");
            System.exit(1);
        }

        //sortmethod
        if(args[1].equals("insert")){

        }
        else if(args[1].equals("merge")){

        }
        else{
            System.out.println("Learn to spell");
        }


        boolean sorted = isSorted(array);

        if(sorted)
            System.out.println("Feld ist sortiert!");
        else
            System.out.println("Feld ist nicht sortiert!");

        // Ende der Messung
        tEnd = System.currentTimeMillis();
        // Die vergangene Zeit ist die Differenz von tStart und tEnd
        msecs = tEnd - tStart;
        System.out.println("Laufzeit betrug: "+msecs);

    }
    //Kevin's implementierung Insertionsort/isSorted/printarray
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

    //mergesort
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
            assert array[backwards] >= array[grenze-i]: "Nicht sortier REEEEE";
            backwards--;
        }

    }
    //Kevin's integer ueberpruefung von blatt 1
    private static boolean parameterIsInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
