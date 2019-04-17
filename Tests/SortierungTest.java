import Blatt02L.KevinSolution.Sortierung;
import org.junit.Test;

public class SortierungTest {

    @Test
    public void testMain0Arguments() {
        Sortierung.main(new String[]{});
    }

    @Test
    public void testMain1Arguments() {
        Sortierung.main(printArguments(new String[]{"Test"}));
        Sortierung.main(printArguments(new String[]{"10"}));
        Sortierung.main(printArguments(new String[]{"-10"}));
    }

    @Test
    public void testMain2Arguments() {
        Sortierung.main(printArguments(new String[]{"Test", "Test"}));
        Sortierung.main(printArguments(new String[]{"Test", "10"}));
        Sortierung.main(printArguments(new String[]{"10", "Test"}));
        Sortierung.main(printArguments(new String[]{"10", "merge"}));
        Sortierung.main(printArguments(new String[]{"10", "insert"}));
        Sortierung.main(printArguments(new String[]{"-10", "insert"}));
    }

    @Test
    public void testMain3Arguments() {
        Sortierung.main(printArguments(new String[]{"Test", "Test", "Test"}));
        Sortierung.main(printArguments(new String[]{"10", "merge", "auf"}));
        Sortierung.main(printArguments(new String[]{"10", "merge", "ab"}));
        Sortierung.main(printArguments(new String[]{"10", "insert", "auf"}));
        Sortierung.main(printArguments(new String[]{"10", "insert", "rand"}));
    }

    @Test
    public void testMain4Arguments() {
        Sortierung.main(printArguments(new String[]{"10", "insert", "rand", "30"}));
    }

    private String[] printArguments(String[] arguments) {
        String concatStr = "\n";
        for (String s : arguments)
            concatStr += s + " ";

        System.out.println(concatStr);
        return arguments;
    }
}