package Templates;

public class InputValidation {
    public static boolean parameterIsFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }
}
