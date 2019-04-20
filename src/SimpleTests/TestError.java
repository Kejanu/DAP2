package SimpleTests;

public class TestError {
    public static void main(String[] args) {
        long tStart, tEnd;
        tStart = System.currentTimeMillis();

        try {
            throw new IllegalArgumentException();
        }
        catch (IllegalArgumentException ex) {
            System.out.println("Blub");
        }
        tEnd = System.currentTimeMillis();

        System.out.println(tStart - tEnd);
    }
}
