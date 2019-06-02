package Blatt9K.LongSolution;

public class Article implements Comparable{
    int weight;
    double value;

    public Article(double value, int weight) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        if (((Article) o).weight > this.weight) {
            return -1;
        } else if (((Article) o).weight < this.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
