package Heimuebung;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Blatt05KevinAufg2 {
    public static void main(String[] args) {
        ArrayList<Gegenstand> items = new ArrayList<>();
        items.add(new Gegenstand("Schwert",3, 3));
        items.add(new Gegenstand("Edelstein",1, 6));
        items.add(new Gegenstand("Schild",5, 4));
        items.add(new Gegenstand("Gold", 2, 6));
        items.add(new Gegenstand("Diamant",2, 5));
        items.add(new Gegenstand("Stahlschuhe", 7, 9));
        items.add(new Gegenstand("Drachenhaut", 1, 2));
        items.add(new Gegenstand("Long", 6, 4));

        int slots = 10;
        int maxWeight = 10;

        ArrayList<Gegenstand> result = inventarOptimization(slots, maxWeight, items);

        System.out.println(result.stream()
            .map(Gegenstand::getName)
            .collect(Collectors.joining(", ")));

        System.out.println("Current Weight: " + result.stream().mapToInt(Gegenstand::getGewicht).sum());
    }

    public static ArrayList<Gegenstand> inventarOptimization(int slots, int maxWeight, ArrayList<Gegenstand> items) {
        ArrayList<Gegenstand> result = new ArrayList<>();
        int currentWeight = 0;

        while (slots > 0 && !items.isEmpty() && maxWeight >= currentWeight) {

            Gegenstand bestVal = items.get(0);
            for (int i = 1; i < items.size(); ++i) {
                Gegenstand currentItem = items.get(i);

                int currentVal = currentItem.getWert() / currentItem.getGewicht();
                int bestValProW = bestVal.getWert() / bestVal.getGewicht();

                if (bestValProW < currentVal && !(currentItem.getGewicht() + currentWeight > maxWeight)) {
                    bestVal = items.get(i);
                }
            }

            if (bestVal.getGewicht() + currentWeight > maxWeight) {
                return result;
            }

            result.add(bestVal);
            currentWeight += bestVal.getGewicht();
            items.remove(bestVal);
            --slots;
        }

        return result;
    }
}
