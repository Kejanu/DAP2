package Blatt05K.AlexSolution;

import Templates.ArrayHelper;

public class CoinProblem {

    final static String MESSAGE = "Proper input: [[Euro | Alternative] | *value* ]";

    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Number of arguments is not sufficient. " + MESSAGE);
            return;
        }

        int[] currency;

        switch (args[0]) {
            case "Euro":
                currency = new int[]{200, 100, 50, 20, 10, 5, 2, 1};
                break;
            case "Alternative":
                currency = new int[]{200, 100, 50, 20, 10, 5, 4, 2, 1};
                break;
            default:
                System.out.println("Invalid currency. " + MESSAGE);
                return;
        }

        int value;

        try {
            value = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Value. Value must be a positive Integer. " + MESSAGE);
            return;
        }

        if (value < 0) {
            System.out.println("The Value you provided is negative. Value must be a postive Integer " + MESSAGE);
        }

        int[] changeOutput = change(value, currency);

        System.out.println("Value " + value);
        System.out.println("Change:");
        ArrayHelper.printTwoIntArrays(currency, changeOutput);
    }

    public static int[] change(int value, int[] currency) {
        int[] changeOutput = new int[currency.length];
        int coinIndex = 0;
        while (coinIndex < currency.length && value > 0) {
            if (value >= currency[coinIndex]) {
                int amount = value / currency[coinIndex];
                changeOutput[coinIndex] = amount;
                value = value - amount * currency[coinIndex];
            }
            ++coinIndex;
        }
        return changeOutput;
    }
}
