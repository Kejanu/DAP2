package Blatt05K.KevinSolution;

import Templates.InputValidation;

import java.util.stream.IntStream;

public class CoinExchangeProblem {

    private static final String PROPER_USAGE_MESSAGE = "Input: One String [Euro / Alternative], One Positive Integer ";

    public static void main(String[] args) {
        if (!basicInputValidationSuccess(args))
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
        In unseren Mustern JA.

        In ALLEN Mustern NEIN
        Bsp. format = 11, 5, 1
        changeMoney = 15

        Best outcome   = 5, 5, 5
        Greedy outcome = 11, 1, 1, 1, 1
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

    private static boolean basicInputValidationSuccess(String[] args) {
        if (args.length <= 0) {
            System.out.println(InputValidation.NO_ARGUMENTS + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (args.length < 2) {
            System.out.println(InputValidation.NOT_ENOUGH_ARGUMENTS + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (args.length > 2) {
            System.out.println(InputValidation.TOO_MANY_ARGUMENTS + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (!(args[0].equals("Euro") || args[0].equals("Alternative"))) {
            System.out.println("Your first argument is not Euro, nor Alternative. Program aborting... " + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (!InputValidation.parameterIsInteger(args[1])) {
            System.out.println("Your second arguments is no Integer. Program aborting... " + PROPER_USAGE_MESSAGE);
            return false;
        }

        if (Integer.parseInt(args[1]) < 0) {
            System.out.println("Your second argument is an Integer, but it is negative. Program aborting... " + PROPER_USAGE_MESSAGE);
            return false;
        }
        return true;
    }
}
