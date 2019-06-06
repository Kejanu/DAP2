package Blatt09K.LongSolution;

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
    public int compareTo(Object o) { //absteigend sortieren nach verhältnis
        if (((((Article) o).getValue())/((Article) o).getWeight()) < (((this.getValue())/(this.getWeight())))){
            return -1;
        } else if (((((Article) o).getValue())/((Article) o).getWeight()) > (((this.getValue())/(this.getWeight())))) {
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return "Article: [Value: "+this.value+", Weight: "+this.weight+"]";
    }
}
