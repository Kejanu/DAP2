package Blatt04L;

public class Aufgabe2LN {
    public static void main(String[] args) {
        //Initialisierung array
        int[] arr = {29,11,40,9,2,5,3,2,0,2,30};

        if (args[0].equals("EinfachesProgramm")) {

            //addiert zusammen alle geraden und ungeraden+1 in einem array
            //2.1soll ergebnis 138
            int v = EinfachesProgramm(arr);
            System.out.println(v);
        }
        else if(args[0].equals("SucheElement")) {

            //sucht größtes Element in einem Intervall (bzw. bis arr.length, da i = arr.length)
            //2.2soll ergebnis 40
            int w = SucheElement(arr,arr.length-1);
            System.out.println(w);
        }
        else {
            System.out.println("Invalid first argument: EinfachesProgramm or SucheElement");
        }
    }
    public static int EinfachesProgramm(int[] arr){
        int n = arr.length;
        int v = 0;
        for(int i = 0; i < n;i++){
            if(arr[i]%2 == 1){
                arr[i] = arr[i] +1;
            }
            v = v + arr[i];
        }
        return v;
    }
    public static int SucheElement(int[] arr, int i){
        if(i == 0){
            return arr[i];
        }
        else{
            int k = SucheElement(arr, i-1);
            if(k > arr[i]){
                return k;
            }
            else{
                return arr[i];
            }
        }
    }
}
