package Templates;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InputValidation {
    public static final String NO_ARGUMENTS = "You provided no arguments. Program aborting... ";
    public static final String NOT_ENOUGH_ARGUMENTS = "You didn't provide enough arguments. Program aborting... ";
    public static final String TOO_MANY_ARGUMENTS = "You provided too many arguments. Program aborting... ";

    public static boolean validateArgs(String[] args, final String PROPER_USAGE, Class... classes) {
        return validateArgs(args, null, PROPER_USAGE, classes);
    }

    public static boolean validateArgs(String[] args, String[][] validStrs, final String PROPER_USAGE, Class... classes) {
        return validateArgs(args, validStrs, PROPER_USAGE, false, classes);
    }

    public static boolean validateArgs(
            String[] args,
            String[][] validStrs,
            final String PROPER_USAGE,
            boolean positiveNum,
            Class... classes) {

        int acceptedStringsUsed = 0;

        if (args.length <= 0) {
            System.out.println(NO_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        if (args.length > classes.length) {
            System.out.println(TOO_MANY_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        if (args.length < classes.length) {
            System.out.println(NOT_ENOUGH_ARGUMENTS + PROPER_USAGE);
            return false;
        }

        for (int i = 0; i < classes.length; ++i) {

            // if cceptedString[acceptedStringsUsed] == null, all Strings accepted
            if (classes[i] == String.class) {
                if (validStrs[acceptedStringsUsed] != null && validStrs[acceptedStringsUsed].length > 0) {
                    if (!Arrays.stream(validStrs[acceptedStringsUsed]).anyMatch(args[i]::equals)) {
                        System.out.println("Your " + (i + 1) + ". argument is not the same as the possible arguments [" +
                                Arrays.stream(validStrs[acceptedStringsUsed]).collect(Collectors.joining(", ")) + "] " + PROPER_USAGE);
                        return false;
                    }
                }
                ++acceptedStringsUsed;
            }

            if (classes[i] == int.class) {
                if (!parameterIsInteger(args[i])) {
                    System.out.println("Your " + (i + 1) + ". argument is no Integer. " + PROPER_USAGE);
                    return false;
                }
                if (positiveNum && Integer.parseInt(args[i]) < 0) {
                    System.out.println("Your " + (i + 1) + ". argument is no positive Integer. " + PROPER_USAGE);
                    return false;
                }
            }

            if (classes[i] == double.class) {
                if (!parameterIsDouble(args[i])) {
                    System.out.println("Your " + (i + 1) + ". argument is no Double. " + PROPER_USAGE);
                    return false;
                }
                if (positiveNum && Double.parseDouble(args[i]) < 0) {
                    System.out.println("Your " + (i + 1) + ". argument is no positive Double. " + PROPER_USAGE);
                    return false;
                }
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
