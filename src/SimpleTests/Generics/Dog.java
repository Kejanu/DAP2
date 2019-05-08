package SimpleTests.Generics;

public class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public Dog() {
        this.name = "José";
    }



    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
