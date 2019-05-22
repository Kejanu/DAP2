package Templates;

import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    public static String generateString(int n) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder(n);
        while (--n >= 0) {
            res.append(alphabet.charAt(ThreadLocalRandom.current().nextInt(alphabet.length())));
        }
        return res.toString();
    }
}
