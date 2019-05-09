import Templates.TestHelper;
import org.junit.Test;

public class InputValidationTest {

    private static final String PROPER_USAGE = "PROPER USAGE HERE";

    @Test
    public void testValidationOnlyStrings() {
        String[] args = new String[]{"First"};
        boolean valid = Templates.InputValidation.validateArgs(TestHelper.printArguments(args), PROPER_USAGE,
                String.class);
        System.out.println(valid);

        args = new String[]{"First", "Second", "Third"};
        valid = Templates.InputValidation.validateArgs(TestHelper.printArguments(args), PROPER_USAGE,
                String.class, String.class, String.class);
        System.out.println(valid);
    }

    @Test
    public void testValidationStringsWithCodition() {
        String[] args = new String[]{"Test"};
        String[][] validStrs = new String[][]{
                {"First", "Second"}
        };
        boolean valid = Templates.InputValidation.validateArgs(TestHelper.printArguments(args), validStrs, PROPER_USAGE,
                String.class);
        System.out.println(valid);

        args = new String[]{"First", "Second", "Third"};
        valid = Templates.InputValidation.validateArgs(TestHelper.printArguments(args), PROPER_USAGE,
                String.class, String.class, String.class);
        System.out.println(valid);
    }

    @Test
    public void testValidationInts() {
        String[] args = new String[]{"-1"};
        boolean valid = Templates.InputValidation.validateArgs(TestHelper.printArguments(args), null, PROPER_USAGE, true,
                int.class);
        System.out.println(valid);

        String[][] validStrs = new String[][]{
                {"First", "Second"}
        };
        args = new String[]{"First", "10"};
        valid = Templates.InputValidation.validateArgs(TestHelper.printArguments(args), validStrs, PROPER_USAGE, true,
                String.class, int.class);
        System.out.println(valid);
    }

}