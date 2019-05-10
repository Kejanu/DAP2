package Blatt05K.KevinSolution;

import Templates.InputValidation;

import java.util.stream.IntStream;

public class CoinExchangeProblem {

    private static final String PROPER_USAGE_MESSAGE = "Input: One String [Euro / Alternative], One Positive Integer ";

    public static void main(String[] args) {

        String[][] acceptedStrings = new String[][] {
                {"Euro", "Alternative"}
        };

        InputValidation validator = new InputValidation(PROPER_USAGE_MESSAGE);
        validator.setPattern(String.class, int.class);
        validator.setAcceptedStrings(acceptedStrings);
        validator.setOnlyPositiveNumbers(true);

        if (!validator.validate(args))
            return;

        int[] format;
        if (args[0].equals("Euro")) {
            format = new int[]{200, 100, 50, 20, 10, 5, 2, 1};
        }
        else {
            format = new int[]{200, 100, 50, 20, 10, 5, 4, 2, 1};
        }

        int[] result = change(Integer.parseInt(args[1]), format);

        IntStream.range(0, result.length)
                 .mapToObj(e -> format[e] + "\t- " + result[e])
                 .forEach(System.out::println);
    }

    /*
        Liefert das implementierte Verfahren stets optimale Lösungen mit einer minimalen Anzahl an Münzen?
        Euro-Muster: Ja
        Alternative-Muster: Nein

        Bsp. 8
        Euro = 5, 2, 1
        Alternative = 5, 2, 1, besser wäre 4, 4
    */

private static int[] change(int changeMoney, int[] format) {
        int[] result = new int[format.length];
        int i = 0;
        while (changeMoney > 0) {
            assert i < format.length;
            if (changeMoney >= format[i]) {
                result[i] = changeMoney / format[i];
                changeMoney %= format[i];
            }
            ++i;
        }
        return result;
    }
}
