import Blatt03K.KevinSolution.Main;
import org.junit.jupiter.api.Test;
class MainTest {

    @Test
    void main() {
        Main.main(printArguments());
        Main.main(printArguments("0.3"));
        Main.main(printArguments("-0.3"));
        Main.main(printArguments("test"));
        Main.main(printArguments("0.3" , "0.4"));
    }

    @Test
    void getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength() {
        Main.getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(10);
        Main.getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(1000);
        Main.getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(10000);
        Main.getTimeOfBubbleSortForArrayDescendingWithSpecifiedLength(100000);
    }

    private String[] printArguments(String... arguments) {
        if (arguments.length == 0)
            System.out.println("Array is empty");

        String concatStr = "\n";
        for (String s : arguments)
            concatStr += s + " ";

        System.out.println(concatStr);
        return arguments;
    }
}
