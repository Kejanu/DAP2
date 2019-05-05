import Blatt05K.KevinSolution.CoinExchangeProblem;
import Templates.TestHelper;
import org.junit.Test;

public class CoinExchangeProblemTest {

    @Test
    public void noArgument() {
        CoinExchangeProblem.main(TestHelper.printArguments());
    }

    @Test
    public void oneArgument() {
        CoinExchangeProblem.main(TestHelper.printArguments("Euro"));
    }

    @Test
    public void twoArgument() {
        CoinExchangeProblem.main(TestHelper.printArguments("Euro", "Test"));
        CoinExchangeProblem.main(TestHelper.printArguments("Test", "455"));
        CoinExchangeProblem.main(TestHelper.printArguments("Euro", "-455"));
        CoinExchangeProblem.main(TestHelper.printArguments("Euro", "455"));
        CoinExchangeProblem.main(TestHelper.printArguments("Alternative", "455"));
    }

    @Test
    public void threeArgument() {
        CoinExchangeProblem.main(TestHelper.printArguments("Euro", "455", "455"));
    }
}