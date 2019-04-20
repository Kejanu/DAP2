package Blatt03K.LongSolution;
import Templates.InputValidation;
import Templates.SearchAlgorithms;
import Templates.SortAlgorithms;

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
            fillArrayDescending(array);
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
        time = time*1000;
        fillArrayDescending(arraySearch);

        //Ermittlung vom Intervall das zu untersuchen ist
        while(true) {
            tStart = System.currentTimeMillis();
            SortAlgorithms.bubbleSort(arraySearch);
            tEnd = System.currentTimeMillis();
            msecs = tEnd - tStart;
            convertedmsecs = msecs;
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
        int solution;
        if(arraySearch.length == 1000) {
            solution = binarySearch(time, convertedmsecs, 0, arraySearch.length);
        }
        else{
            solution = binarySearch(time, convertedmsecs, arraySearch.length / 2, arraySearch.length);
        }
        System.out.println("Das Ergebnis: Das Array hat die Länge: " + solution);


    }

    //Suche nachder Länge für die gilt systemtime-toleranzgrenze <= timeInput <= systemtime +toleranzgrenze
    public static int binarySearch( float timeInput, float systemTime, int left, int right){
        float tolerantlimit = 100f;
        if(left == right)
            return left;
        int q = (left + right) / 2;
        //laufzeitmessung von bubblesort
        systemTime = binarySearchHelper(q);
        if (timeInput-tolerantlimit < systemTime && systemTime < timeInput+tolerantlimit) {
            return q;
        }

        if (timeInput < systemTime) {
            return binarySearch(timeInput, systemTime, left, q);
        }
        else {
            return binarySearch(timeInput, systemTime, q+1, right);
        }
    }

    public static float binarySearchHelper(int arraylength){

        long tStart, tEnd, msecs;
        float convertedmsecs;
        //length has been cutted for binarySearch purposes
        int[] arraySearchHelper = new int[arraylength];
        fillArrayDescending(arraySearchHelper);

        //System.out.println("arraylength in binarySearch time: "+arraylength);



        tStart = System.currentTimeMillis();
        SortAlgorithms.bubbleSort(arraySearchHelper);
        tEnd = System.currentTimeMillis();

        msecs = tEnd - tStart;
        convertedmsecs = msecs;
        System.out.println("Bubblesort in binarySearch systemtime: " + convertedmsecs);
        System.out.println("arraylength in binarySearch time: "+arraySearchHelper.length);
        return convertedmsecs;
    }



    public static void fillArrayDescending(int[] array){
        int counter = 0;
        for(int i= array.length-1; i >0; i--){
            array[counter] = i;
            counter++;
        }
    }
}
