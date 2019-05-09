package Templates;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InputValidation {
    public static final String NO_ARGUMENTS = "You provided no arguments. Program aborting... ";
    public static final String NOT_ENOUGH_ARGUMENTS = "You didn't provide enough arguments. Program aborting... ";
    public static final String TOO_MANY_ARGUMENTS = "You provided too many arguments. Program aborting... ";

    public static boolean validateArgs(
            String[] args,
            String[][] acceptedString,
            int requiredLength,
            final String PROPER_USAGE,
            boolean intsPositve,
            Class... classes) {

        int acceptedStringsUsed = 0;

        if (args.length <= 0) {
            System.out.println(NO_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        if (args.length > requiredLength) {
            System.out.println(TOO_MANY_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        if (args.length < requiredLength) {
            System.out.println(NOT_ENOUGH_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        for (int i = 0; i < requiredLength; ++i) {
            if (classes[i] == int.class) {
                if (!parameterIsInteger(args[i])) {
                    System.out.println("Your " + (i + 1) + ". argument is no Integer. " + PROPER_USAGE);
                    return false;
                }
                if (intsPositve && Integer.parseInt(args[i]) < 0) {
                    System.out.println("Your " + (i + 1) + ". argument is no positive Integer. " + PROPER_USAGE);
                    return false;
                }
            }


            // if cceptedString[acceptedStringsUsed] == null, all Strings accepted
            if (classes[i] == String.class) {
                if (acceptedString[acceptedStringsUsed] != null) {
                    if (!Arrays.stream(acceptedString[acceptedStringsUsed]).anyMatch(args[i]::equals)) {
                        System.out.println("Your " + (i + 1) + ". argument is not the same as the possible arguments [" +
                            Arrays.stream(acceptedString[acceptedStringsUsed]).collect(Collectors.joining(", ")) + "] " + PROPER_USAGE);
                        return false;
                    }
                }
                ++acceptedStringsUsed;
            }
        }

        return true;
    }










    public static boolean parameterIsFloat(String s) {
        try { Float.parseFloat(s); return true; }
        catch (NumberFormatException nfe) { return false; }
    }

    public static boolean parameterIsDouble(String s) {
        try { Double.parseDouble(s); return true; }
        catch (NumberFormatException nfe) { return false; }
    }

    public static boolean parameterIsInteger(String s) {
        try { Integer.parseInt(s); return true; }
        catch (NumberFormatException nfe) { return false; }
    }
}
