package Heimuebung;

import Templates.ArrayHelper;

public class Blatt05KevinAufg1 {
    public static void main(String[] args) {

        String[] places = new String[]{
                "Am Fichtenplan Nord",
                "Hirschberg Ost",
                "Im Hegau Ost",
                "Von und zu Longingen",
                "Flämingen West",
                "Dresdner Tor Süd",
                "Holmmoor Ost",
                "Eichelborn Süd",
                "Zweidorfer",
                "Livingen",
                "Livi",
                "Antonius",
                "Cao und auf Wiedersehen"
        };

        int[] result = getPlacesToStayAt(places, 4, 15);
        ArrayHelper.printIntArray(result);
    }

    public static int[] getPlacesToStayAt(String[] places, int dailyLength, int totalLength) {
        int[] result = new int[totalLength / dailyLength];
        int index = 0;

        while (totalLength > 0) {
            result[index] = dailyLength + dailyLength * index;
            totalLength -= dailyLength;
        }
        return result;
    }
}
