public class Blatt01 {

    private static void bearbeiteArray(int[] array) {

        //n ← length[A]
        int n = array.length;
        //Kevin stinkt

        // for i ← n downto 1 do || downto runterzählen
        for (int i = n; i > 0; --i) {
            //System.out.println("For executed");

            // for j ← 1 to i − 1 do || to hochzählen
            for (int j = 0; j < i - 1; ++j) {
                System.out.println("For2 executed");

                // if A[j + 1] < A[j] then
                if (array[j+1] < array[j]) {

                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
                printArray(array);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{6, 5, 4, 3};
        System.out.println("Länge " + array.length);
        bearbeiteArray(array);
        //printArray(array);
    }

    private static void printArray(int[] array) {
        for (int i :  array) {
            System.out.print(array[i] + " ");
        }
    }
}
