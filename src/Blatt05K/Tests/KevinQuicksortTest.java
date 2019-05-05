import Blatt05K.KevinSolution.Quicksort;
import Templates.TestHelper;
import org.junit.Test;

public class KevinQuicksortTest {

    @Test
    public void zeroParameter() {
        Quicksort.main(TestHelper.printArguments());
    }

    @Test
    public void oneParameter() {
        Quicksort.main(TestHelper.printArguments("2"));
        Quicksort.main(TestHelper.printArguments("-2"));
    }

    @Test
    public void twoParameter() {
        Quicksort.main(TestHelper.printArguments("1", "1"));
    }
}