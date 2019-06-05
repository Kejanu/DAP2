package Blatt09K.KevinSolution;

public class Article {

    private int value;
    private int weight;

    public Article(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Article{" +
                " val = " + value +
                ", weight = " + weight +
                ", ratio = " + getRatio() +
                " }\n";
    }

    public double getRatio() {
        return (double) value / weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
