package SimpleTests;

import java.util.LinkedList;

public class TestList {

    public static void main(String[] args) {

        LinkedList<String> ll = new LinkedList<>();

        ll.add("Livingen");
        ll.add("Antoningen");
        ll.add("Longingen");

        ll.forEach(System.out::println);

        for (int i = 0; i < 4; ++i) {
            for (int j = i + 1; j < 4; ++j) {
                System.out.println("i: " + i + " j: " + j);
            }
        }

    }
}
