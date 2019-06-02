package Blatt9K.AlexSolution;

public class Article {

    private int weight, value;

    public Article(int w, int v){
        weight = w;
        value = v;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String toString(){
        return "Article: weight " + weight + ", value " + value + "\n";
    }
}
