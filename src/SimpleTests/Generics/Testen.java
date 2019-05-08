package SimpleTests.Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Testen {
    private static <T> List<T> magicalListGetter(Class<T> clazz) {
        List<T> list = new ArrayList<>();

        try {
            T test = clazz.getDeclaredConstructor().newInstance();
            list.add(test);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<Dog> dogList = magicalListGetter(Dog.class);
        System.out.println(dogList);
    }
}
