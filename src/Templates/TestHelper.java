package Templates;

public class TestHelper {
    public static String[] printArguments(String... arguments) {
        String concatStr = "\n";
        for (String s : arguments)
            concatStr += s + " ";

        System.out.println(concatStr);
        return arguments;
    }
}
