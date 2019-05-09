package Heimuebung;

import java.util.Arrays;

public class Blatt05LongAufg2 {
    public static void main(String[] args) {
        int[] wert = {12, 91, 1, 93, 9, 19, 2, 10, 5};
        boolean[] kappa = inventarOptimization(wert, 5);
        System.out.println(Arrays.toString(kappa));
    }

    public static boolean[] inventarOptimization(int[] wert, int n) {
        boolean[] result = new boolean[wert.length];

        for (int c = 0; c < result.length; c++) {
            result[c] = false;
        }

        int k = 0; //inventory
        int highestValue = wert[0];

        while (k < n) {
            int i = 0;
            for (int j = 0; j < wert.length; j++) {
                if (highestValue < wert[j] && !result[j]) {
                    highestValue = wert[j];
                    i = j;
                }
            }

            result[i] = true;
            k++;
            for (int l = 0; l < wert.length; l++) {
                if (!result[l]) {
                    highestValue = wert[l];
                    break;
                }
            }
        }
        return result;
    }
}
