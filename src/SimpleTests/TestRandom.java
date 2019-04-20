package SimpleTests;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class TestRandom {
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            System.out.println(ThreadLocalRandom.current().nextDouble(-1, 1));
        }

//        Random r = new Random();
//        for (int i = 0; i < 10; ++i)
//            System.out.println(r.nextInt(2));

//        for (int i = 0; i < 10; ++i)
//            System.out.println(r.nextDouble());

//        double lB = 2;
//        double uB = 5;
//
//        for (int i = 0; i < 10; ++i)
//            System.out.println(lB + (uB - lB) * r.nextDouble());
    }
}
