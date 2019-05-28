import Blatt07K.KevinSolution;
import Templates.TestHelper;
import org.junit.Test;

public class KevinSolutionTest {

    @Test
    public void zeroArguments() {
        KevinSolution.main(TestHelper.printArguments());
    }

    @Test
    public void oneArguments() {
        KevinSolution.main(TestHelper.printArguments("test"));
        KevinSolution.main(TestHelper.printArguments("20"));
        KevinSolution.main(TestHelper.printArguments("graph"));
        KevinSolution.main(TestHelper.printArguments("greph"));
        KevinSolution.main(TestHelper.printArguments("-20"));
    }

    @Test
    public void twoArguments() {
        KevinSolution.main(TestHelper.printArguments("20", "10"));
    }


}