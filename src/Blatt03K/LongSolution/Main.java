package Blatt03K.LongSolution;
import Templates.InputValidation;

public class Main {
    //warum habt ihr sie alle Main genannt
    public static void bubbleSort(int[] array){
        int n = array.length;
        for(int i = 0; i<n;i++){
            for(int j = n-1; j>i;j--){
                if(array[j-1]>array[j]){
                    int tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        long tStart, tEnd, msecs;

        if(args[0].equals("BubbleSort")){
            int[] array = new int[50000];
            // Beginn der Messung
            tStart = System.currentTimeMillis();
            bubbleSort(array);
            tEnd = System.currentTimeMillis();
            msecs = tEnd - tStart;
            System.out.println("Time needed to sort 50000 Elements in msecs: "+msecs);
        }
        else if(args[0].equals("Geometrische_Suche") || args[0].equals("Binäre_Suche")) {
            if(!InputValidation.parameterIsFloat(args[1])){
                System.out.println("Parameter war kein Float");
                return;
            }
            System.out.println("");
            geometricalSearch(Float.parseFloat(args[1]),1000);
        }
        else{
            System.out.println("Syntax: Geometrische_Suche | Binäre Suche float" +
                    "oder Syntax: BubbleSort");
        }


    }
    //fieldSize is 1k
    public static void geometricalSearch(float time, int fieldSize){
        long tStart, tEnd, msecs;
        float convertedmsecs;
        int[] arraySearch = new int[fieldSize];
        fillArrayDescending(arraySearch);

        //Ermittlung vom Intervall das zu untersuchen ist
        while(true) {
            tStart = System.currentTimeMillis();
            bubbleSort(arraySearch);
            tEnd = System.currentTimeMillis();
            msecs = tEnd - tStart;
            convertedmsecs = msecs / 100.0f;
            if (convertedmsecs < time) {
                arraySearch = new int[arraySearch.length*2];
                fillArrayDescending(arraySearch);
            }
            else{
                break;
            }
        }
        System.out.println("Array length before binarySearch: "+ arraySearch.length);
        System.out.println("Bubblesort time before binarySearch: "+ convertedmsecs);

        //binäre Suche wird gestartet
        int solution = binarySearch(time, convertedmsecs,arraySearch,arraySearch.length/2,arraySearch.length);

        //ergebnis yay
        System.out.println("Das Ergebnis: Das Array hat die Länge: " + solution);

    }

    //Suche nachder Länge für die gilt systemtime-toleranzgrenze < timeInput < systemtime +toleranzgrenze
    public static int binarySearch( float time, float systemTime, int[] array, int left, int right){
        float tolerantlimit = 0.1f;
        if (systemTime-tolerantlimit <= time && time <= systemTime+tolerantlimit)
            return left;
        int q = (left + right) / 2;
        //laufzeitmessung von bubblesort
        systemTime = binarySearchHelper(q,array);
        System.out.println("Bubblesort in binarySearch time: "+ systemTime);
        if (time <= systemTime) {
            return binarySearch(time, systemTime, array, left, q);
        }
        else {
            return binarySearch(time, systemTime, array, q+1, right);
        }
    }

    public static float binarySearchHelper(int arraylength,int[]arraySearchHelper){

        long tStart, tEnd, msecs;
        float convertedmsecs;
        //length has been cutted for binarySearch purposes
        arraySearchHelper = new int[arraylength];

        System.out.println("arraylength in binarySearch time: "+arraylength);

        fillArrayDescending(arraySearchHelper);
        tStart = System.currentTimeMillis();
        bubbleSort(arraySearchHelper);
        tEnd = System.currentTimeMillis();
        msecs = tEnd - tStart;
        convertedmsecs = msecs / 100.0f;
        return convertedmsecs;
    }



    public static void fillArrayDescending(int[] array){
        for(int i= array.length-1; i >0; i--){
            array[i] = i;
        }
    }
}
