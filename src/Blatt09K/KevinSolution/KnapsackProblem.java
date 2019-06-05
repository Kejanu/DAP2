package Blatt09K.KevinSolution;

import Templates.InputValidation;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnapsackProblem {

    private static final String PROPER_USAGE = "Amount of Goods (int), Max WeightLimit (int), p (int)";

    public static void main(String[] args) {

        InputValidation validation = new InputValidation(PROPER_USAGE);
        validation.setNoneNegativeNumbers(true);
        validation.setPattern(int.class, int.class, int.class);

        if (!validation.validate(args)) {
            return;
        }

        int amountOfGoods = Integer.parseInt(args[0]);
        int maxWeight = Integer.parseInt(args[1]);
        int weightMultiplier = Integer.parseInt(args[2]);

        ArrayList<Article> articles =
                IntStream.range(0, amountOfGoods).mapToObj(e ->
                        new Article((int) ThreadLocalRandom.current().nextInt(8000, 12501) / 10000,
                                0
                )).collect(Collectors.toCollection(ArrayList::new));

    }
}
