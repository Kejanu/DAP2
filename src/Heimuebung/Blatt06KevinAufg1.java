package Heimuebung;

import java.util.Arrays;

public class Blatt06KevinAufg1 {
    public static void main(String[] args) {
        int length = 50;
        int[] sticksLength = {4, 2, 7, 9};
        System.out.println(calcSmalestAmount(length, sticksLength));
    }

    private static int calcSmalestAmount(int length, int[] sticksLength) {
        int[] result = new int[length + 1];
        Arrays.fill(result, Integer.MAX_VALUE);

        for (int lengthIndex = 0; lengthIndex <= length; ++lengthIndex) {

            for (int j = 0; j < sticksLength.length; ++j) {

                // Stick can be used "generally speaking"
                if (sticksLength[j] <= lengthIndex) {

                    int remainingLength = lengthIndex - sticksLength[j];
                    if (remainingLength == 0) {
                        // We only have to use this one stick
                        result[lengthIndex] = 1;
                    }
                    else {
                        // We have length to cover left
                        if (result[remainingLength] != Integer.MAX_VALUE) {
                            // We cant represent this length
                            result[lengthIndex] = Math.min(result[lengthIndex], result[remainingLength] + 1);
                        }
                    }
                }
            }
        }
        return result[length];
    }
}
