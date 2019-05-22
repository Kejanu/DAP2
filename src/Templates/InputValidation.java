package Templates;

import java.lang.ref.PhantomReference;
import java.util.Arrays;
import java.util.stream.Collectors;

public class InputValidation {
    private static final String NO_ARGUMENTS = "You provided no arguments. Program aborting... ";
    private static final String NOT_ENOUGH_ARGUMENTS = "You didn't provide enough arguments. Program aborting... ";
    private static final String TOO_MANY_ARGUMENTS = "You provided too many arguments. Program aborting... ";

    private Class[] pattern;
    private String properUsage;
    private boolean onlyPositiveNumbers;
    private boolean noneNegativeNumbers;
    private String[][] acceptedStrings;
    private int acceptedStringsUsed;

    private String errorMessage;

    public InputValidation(String properUsage) {
        this.properUsage = "\n" + properUsage;
    }

    /**
     * @param args Validates the given arguments based on the set variables
     * @return Return true, if the validation succeeded
     */
    public boolean validate(String[] args) {
        acceptedStringsUsed = 0;
        System.out.println("Your Input: " + Arrays.toString(args));
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
            if (pattern[i] == String.class) {
                if(!checkStringCase(args[i], i)) {
                    System.out.println(errorMessage + properUsage);
                    return false;
                }
            }

            if (pattern[i] == int.class) {
                if(!checkIntCase(args[i], i)) {
                    System.out.println(errorMessage + properUsage);
                    return false;
                }
            }

            if (pattern[i] == double.class) {
                if(!checkDoubleCase(args[i], i)) {
                    System.out.println(errorMessage + properUsage);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkStringCase(String s, int index) {
        if (acceptedStrings != null && acceptedStrings[acceptedStringsUsed] != null && acceptedStrings[acceptedStringsUsed].length > 0) {
            // Check "normal" Strings
            if (Arrays.stream(acceptedStrings[acceptedStringsUsed]).noneMatch(s::equals)) {
                // Not one matching String was found

                // So check for possible int Values, if "$int" is provided in the acceptedStrings
                if (Arrays.stream(acceptedStrings[acceptedStringsUsed]).anyMatch("$int"::equals)) {
                    if (!checkIntCase(s, index))
                        return false;
                }
                else {
                    String replacement = "anyInteger";
                    if (isOnlyPositiveNumbers())
                        replacement = "positive Integer";

                    if (isNoneNegativeNumbers())
                        replacement = "non negative Integer";

                    errorMessage = "Your " + (index + 1) + ". argument is not the same as the possible arguments [" +
                            Arrays.stream(acceptedStrings[acceptedStringsUsed])
                                    .collect(Collectors.joining(", "))
                                    .replace("$int", replacement) + "] ";
                    return false;
                }
            }
        }
        ++acceptedStringsUsed;
        return true;
    }

    private boolean checkIntCase(String s, int index) {
        if (!parameterIsInteger(s)) {
            errorMessage = "Your " + (index + 1) + ". argument is no Integer ";
            return false;
        }
        if (onlyPositiveNumbers && !parameterIsPositiveInteger(s)) {
            errorMessage = "Your " + (index + 1) + ". argument is no positive Integer ";
            return false;
        }
        if (noneNegativeNumbers && !parameterIsNonNegativeInteger(s)) {
            errorMessage = "Your " + (index + 1) + ". argument is a negative Integer. Only none negative Integers allowed ";
            return false;
        }
        return true;
    }

    private boolean checkDoubleCase(String s, int index) {
        if (!parameterIsDouble(s)) {
            errorMessage = "Your " + (index + 1) + ". argument is no Double ";
            return false;
        }
        if (onlyPositiveNumbers && Double.parseDouble(s) < 0) {
            errorMessage = "Your " + (index + 1) + ". argument is no positive Double ";
            return false;
        }
        return true;
    }

    private static boolean parameterIsPositiveInteger(String s) {
        try { int i = Integer.parseInt(s); return i > 0; }
        catch (NumberFormatException nfe) { return false; }
    }

    private static boolean parameterIsNonNegativeInteger(String s) {
        try { int i = Integer.parseInt(s); return i > -1; }
        catch (NumberFormatException nfe) { return false; }
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

    public boolean isNoneNegativeNumbers() {return noneNegativeNumbers;}

    /**
     * @param noneNegativeNumbers If the numbers should be greater than -1. Default: false
     */
    public void setNoneNegativeNumbers(boolean noneNegativeNumbers) {this.noneNegativeNumbers = noneNegativeNumbers;}
}
