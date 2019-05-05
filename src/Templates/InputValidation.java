package Templates;

public class InputValidation {
    public static final String NO_ARGUMENTS = "You provided no arguments. Program aborting... ";
    public static final String NOT_ENOUGH_ARGUMENTS = "You didn't provide enough arguments. Program aborting... ";
    public static final String TOO_MANY_ARGUMENTS = "You provided too many arguments. Program aborting... ";

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
