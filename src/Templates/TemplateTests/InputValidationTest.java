import Templates.InputValidation;
import Templates.TestHelper;
import org.junit.Test;

public class InputValidationTest {

    private static final String PROPER_USAGE = "PROPER USAGE HERE";

    @Test
    public void testValidationOnlyStrings() {
        InputValidation validator = new InputValidation(PROPER_USAGE);
        validator.setPattern(String.class);

        String[] args = new String[]{"First", "Second"};
        boolean valid;

        valid = validator.validate(TestHelper.printArguments(args));
        System.out.println(valid);

        args = new String[]{"First", "Second", "Third"};
        validator.setPattern(String.class, String.class, String.class);
        valid = validator.validate(args);
        System.out.println(valid);
    }

    @Test
    public void testValidationStringsWithCodition() {
        InputValidation validator = new InputValidation(PROPER_USAGE);

        String[] args = new String[]{"Test"};
        String[][] validStrs = new String[][]{
                {"First", "Second"}
        };
        validator.setPattern(String.class);
        validator.setAcceptedStrings(validStrs);
        boolean valid = validator.validate(TestHelper.printArguments(args));
        System.out.println(valid);

        args = new String[]{"First", "Second", "Third"};
        validator.setAcceptedStrings(null);
        validator.setPattern(String.class, String.class, String.class);
        valid = validator.validate(args);
        System.out.println(valid);
    }

    @Test
    public void testValidationInts() {
        InputValidation validator = new InputValidation(PROPER_USAGE);
        String[] args = new String[]{"-1"};

        validator.setOnlyPositiveNumbers(true);
        validator.setPattern(int.class);

        boolean valid = validator.validate(TestHelper.printArguments(args));
        System.out.println(valid);

        String[][] validStrs = new String[][]{
                {"First", "Second"}
        };
        args = new String[]{"First", "10"};
        validator.setOnlyPositiveNumbers(true);
        validator.setPattern(String.class, int.class);
        validator.setAcceptedStrings(validStrs);
        valid = validator.validate(TestHelper.printArguments(args));
        System.out.println(valid);
    }

}