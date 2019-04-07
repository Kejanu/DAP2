package Blatt01K;

import java.util.Arrays;

public class Blatt01KBetterThanLiv {
    public static void main(String[] args) {
        if (args[0].equals("Euclid")) {
            doEuclid(args);
        }
        else if (args[0].equals("Eratosthenes")) {
            doEratosthenes(args);
        }
        else {
            System.out.println("Invalid first argument: Euclid or Eratosthenes");
        }
    }

    private static void doEratosthenes(String[] args) {
        // Basic input validation
        if (args.length < 2 || !parameterIsInteger(args[1])) {
            System.out.println("Invalid arguments. Optional in (). Syntax: Eratosthenes Integer1 (-o)");
            System.exit(1);
        }

        Integer upperBound = Integer.parseInt(args[1]);
        if (upperBound <= 1) {
            System.out.println("Keine Primzahlen bis " + upperBound + " vorhanden");
            System.exit(0);
        }

        // 0 and 1 arent primenumbers => only 2 and above true
        boolean[] isPrime = new boolean[upperBound];
        for (int i = 2; i < isPrime.length; ++i)
            isPrime[i] = true;

        // Siebs des Eratosthenes Logik
        for (int i = 2; i < upperBound; ++i) {
            if (isPrime[i]) {
                for (int j = i * 2; j < upperBound; j = j + i) {
                    isPrime[j] = false;
                }
            }
        }

        int countOfPrimenumbers = 0;
        boolean printNumbers = args.length >= 3 && args[2].equals("-o");

        for (int i = 2; i < isPrime.length; ++i) {
            if (isPrime[i]) {
                if (printNumbers)
                    System.out.print(i + " ");
                ++countOfPrimenumbers;
            }
        }

        System.out.println("\nAnzahl der Primzahlen: " + countOfPrimenumbers);
    }

    private static void doEuclid(String[] args) {
        // Basic input validation
        if (args.length < 3 || !parameterIsInteger(args[1]) || !parameterIsInteger(args[2])) {
            System.out.println("Invalid arguments. Syntax: Euclid Integer1 Integer2");
            System.exit(1);
        }

        int result = euclid(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println(result);
    }

    private static int euclid(int a, int b) {
        if (b == 0)
            return a;

        return euclid(b, a % b);
    }

    private static boolean parameterIsInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
