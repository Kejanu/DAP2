package Templates;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InputValidation {
    public static final String NO_ARGUMENTS = "You provided no arguments. Program aborting... ";
    public static final String NOT_ENOUGH_ARGUMENTS = "You didn't provide enough arguments. Program aborting... ";
    public static final String TOO_MANY_ARGUMENTS = "You provided too many arguments. Program aborting... ";

    private Class[] pattern;
    private String properUsage;
    private boolean onlyPositiveNumbers;
    private String[][] acceptedStrings;

    public InputValidation(String properUsage) {
        this.properUsage = properUsage;
    }

    /**
     * @param args Validates the given arguments based on the set variables
     * @return Return true, if the validation succeeded
     */
    public boolean validate(String[] args) {
        int acceptedStringsUsed = 0;

        if (args.length <= 0) {
            System.out.println(NO_ARGUMENTS + properUsage);
            return false;
        }

        if (args.length > pattern.length) {
            System.out.println(TOO_MANY_ARGUMENTS + properUsage);
            return false;
        }

        if (args.length < pattern.length) {
            System.out.println(NOT_ENOUGH_ARGUMENTS + properUsage);
            return false;
        }

        for (int i = 0; i < pattern.length; ++i) {

            // if cceptedString[acceptedStringsUsed] == null, all Strings accepted
            if (pattern[i] == String.class) {
                if (acceptedStrings != null && acceptedStrings[acceptedStringsUsed] != null && acceptedStrings[acceptedStringsUsed].length > 0) {
                    if (!Arrays.stream(acceptedStrings[acceptedStringsUsed]).anyMatch(args[i]::equals)) {
                        System.out.println("Your " + (i + 1) + ". argument is not the same as the possible arguments [" +
                                Arrays.stream(acceptedStrings[acceptedStringsUsed]).collect(Collectors.joining(", ")) + "] " + properUsage);
                        return false;
                    }
                }
                ++acceptedStringsUsed;
            }

            if (pattern[i] == int.class) {
                if (!parameterIsInteger(args[i])) {
                    System.out.println("Your " + (i + 1) + ". argument is no Integer. " + properUsage);
                    return false;
                }
                if (onlyPositiveNumbers && Integer.parseInt(args[i]) < 0) {
                    System.out.println("Your " + (i + 1) + ". argument is no positive Integer. " + properUsage);
                    return false;
                }
            }

            if (pattern[i] == double.class) {
                if (!parameterIsDouble(args[i])) {
                    System.out.println("Your " + (i + 1) + ". argument is no Double. " + properUsage);
                    return false;
                }
                if (onlyPositiveNumbers && Double.parseDouble(args[i]) < 0) {
                    System.out.println("Your " + (i + 1) + ". argument is no positive Double. " + properUsage);
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

    public Class[] getPattern() {return pattern;}

    /**
     * @param pattern The pattern the validate method should follow e.g. String.class, int.class
     */
    public void setPattern(Class... pattern) {this.pattern = pattern;}

    public String getProperUsage() {return properUsage;}

    public void setProperUsage(String properUsage) {this.properUsage = properUsage;}

    public boolean isOnlyPositiveNumbers() {return onlyPositiveNumbers;}

    /**
     * @param onlyPositiveNumbers If only positive numbers should be accepted. Default: false
     */
    public void setOnlyPositiveNumbers(boolean onlyPositiveNumbers) {this.onlyPositiveNumbers = onlyPositiveNumbers;}

    public String[][] getAcceptedStrings() {return acceptedStrings;}

    /**
     * @param acceptedStrings The strings that should be accepted. if String[x] == null => all String accepted
     */
    public void setAcceptedStrings(String[][] acceptedStrings) {this.acceptedStrings = acceptedStrings;}
}
