import java.util.Random;

public class Sortierung {

    private static void fillArrayWithRandom(int[] array) {
        Random random = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = random.nextInt();
        }
    }

    public static void main(String[] args) {

        // Calcualte duration of Program
        long tStart, tEnd;
        tStart = System.currentTimeMillis();

        int[] array = new int[Integer.valueOf(args[0])];

        switch (args[1]) {
            case "rand":
                fillArrayWithRandom(array);
                break;

            case "auf":
                for (int i = 0; i < array.length; ++i)
                    array[i] = i;
                break;

            case "ab":
                for (int i = 0; i < array.length; ++i)
                    array[i] = array.length - i;
                break;

            default:
                fillArrayWithRandom(array);
        }

        tEnd = System.currentTimeMillis();

        System.out.println(tEnd - tStart);

        printArray(array);

//        int[] array = new int[]{5, 4, 3, 2, 1};
//        insertionSort(array);
//        printArray(array);
//        System.out.println(isSorted(array));




    }

    public static void insertionSort(int[] array) {
        for (int j = 1; j < array.length; ++j) {
            int key = array[j];
            int i = j - 1;

            while (i >= 0 && array[i] > key) {
                array[i+1] = array[i];
                --i;
            }
            array[i+1] = key;
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] > array[i+1])
                return false;
        }
        return true;
    }

    public static void printArray(int[] array) {
        for (int i : array)
            System.out.print(i + " ");
        System.out.print("\n");
    }

}
