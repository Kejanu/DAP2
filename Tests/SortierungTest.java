import Blatt02L.KevinSolution.Sortierung;
import org.junit.jupiter.api.Test;

class SortierungTest {

    @Test
    public void testMain0Arguments() {
        Sortierung.main(new String[]{});
    }

    @Test
    public void testMain1Arguments() {
        Sortierung.main(new String[]{"Test"});
        System.out.println();
        Sortierung.main(new String[]{"10"});
    }

    @Test
    public void testMain2Arguments() {
        Sortierung.main(new String[]{"Test", "Test"});
        System.out.println();
        Sortierung.main(new String[]{"Test", "10"});
        System.out.println();
        Sortierung.main(new String[]{"10", "Test"});
    }

    @Test
    public void testMain3Arguments() {
        Sortierung.main(new String[]{"Test", "Test", "Test"});
        System.out.println();
        Sortierung.main(new String[]{"10", "merge", "auf"});
        System.out.println();
        Sortierung.main(new String[]{"10", "merge", "ab"});
        System.out.println();
        Sortierung.main(new String[]{"10", "insert", "auf"});
        System.out.println();
        Sortierung.main(new String[]{"10", "insert", "rand"});
    }

    @Test
    public void testMain4Arguments() {
        Sortierung.main(new String[]{"10", "insert", "rand", "30"});
    }
}