package Heimuebung;

import java.util.Arrays;

public class Blatt05LongAufg1 {

    public static void main(String[] args) {
        //not sure if it works correctly
        int erik = 6;
        //Annahme: 0 < A[i + 1] − A[i] ≤ k //A[i] kilometer ab dortmund
        int[] raststätten={1,2,3,6,9,11,14,15,16};
        //l kilometer lange strecke
        int gesamtStrecke = 19;
        int[] test = eriksWunderbareReiseNachJerusalem(raststätten,erik,gesamtStrecke);
        System.out.println(Arrays.toString(test));
    }

    public static int[] eriksWunderbareReiseNachJerusalem(int[] rasttättenInKilometer, int erik, int gesamtStrecke){
        //maximale Arraylaenge
        int[] result = new int[gesamtStrecke/erik];
        int abgefahrendeKilometer = 0;

        //namensWechsel weil mein IQ unter 80 ist
        int restStrecke = gesamtStrecke;

        //Indizes
        int j = 0;
        int i = 1;

        while(restStrecke>0 && i < rasttättenInKilometer.length){
            if((rasttättenInKilometer[i] - abgefahrendeKilometer)> erik){
                abgefahrendeKilometer = rasttättenInKilometer[i-1];
                result[j] = i-1;
                restStrecke = gesamtStrecke-(abgefahrendeKilometer);
                j++;
            }
            i++;
            System.out.println("Reststrecke: "+restStrecke);
        }

        //letzter schritt
        if(restStrecke > erik && i == rasttättenInKilometer.length){
            result[j] = i-1;
        }
        return result;
    }
}
