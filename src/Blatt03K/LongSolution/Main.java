package Blatt03K.LongSolution;

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
        int[] someNumbers = {18,893,1982,181,91,0};

        if(args[0].equals("50000")) {
            // Beginn der Messung
            tStart = System.currentTimeMillis();
            // Hier wird der Code ausgeführt, dessen Laufzeit gemessen werden soll
            bubbleSort(someNumbers);
            // Ende der Messung
            tEnd = System.currentTimeMillis();
            // Die vergangene Zeit ist die Differenz von tStart und tEnd
            msecs = tEnd - tStart;
        }


        for(int numbers:someNumbers){
            System.out.println(numbers);
        }
    }
    public static void fillArrayDescending(int[] array){

    }
}
