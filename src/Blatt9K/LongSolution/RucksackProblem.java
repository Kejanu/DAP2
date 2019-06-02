package Blatt9K.LongSolution;

import java.util.ArrayList;

public class RucksackProblem {
    public static void main(String[] args) {
        Article a1 = new Article(11, 5);
        Article a2 = new Article(5, 2);
        Article a3 = new Article(2, 1);
        Article a4 = new Article(8, 3);
        Article a5 = new Article(14, 7);
        Article a6 = new Article(9, 4);


        Article[] articles = {a1, a2, a3, a4, a5, a6};
        RucksackProblem lol = new RucksackProblem(6, 0, 2);
        System.out.println(lol.rucksack(6, articles));
    }

    private int n;
    private double maxWeight;
    private double p;

    public RucksackProblem(int n, double maxWeight, double p) {
        this.n = n;
        this.maxWeight = maxWeight;
        this.p = p;
    }

    public Article[] randomArticles(int n, double p) {
        Article[] articles = new Article[n];
        //randomGenerator
        java.util.Random generator = new java.util.Random();
        int randomLowerBoundWeight = (int) (0.8 * p);
        int randomUpperBoundWeight = (int) (1.25 * p);
        int randomLowerBoundValue = (int) (100);
        int randomUpperBoundValue = (int) (1000);

        for (int i = 0; i < n; i++) {
            articles[i].setWeight(randomLowerBoundWeight
                    + (randomUpperBoundWeight - randomLowerBoundWeight) * generator.nextInt());
            articles[i].setValue(randomLowerBoundValue
                    + (randomUpperBoundValue - randomLowerBoundValue) * generator.nextInt());
        }
        return articles;
    }

    public double rucksack(int n, Article[] articles) {
        double[][] Opt = new double[n + 1][articles.length + 1];
        for (int j = 0; j < articles.length + 1; j++) {
            Opt[0][j] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < articles.length + 1; j++) {
                //berechne nach Rekursion..
                Opt[i][j] = optrec(i, j, articles, Opt);
            }
        }
        return Opt[n][articles.length];
    }

    public double optrec(int i, int j, Article[] articles, double[][] Opt) {
        double result;
        if (j < articles[i - 1].getWeight()) {
            result = Opt[i - 1][j];
        } else {
            result = Double.max(Opt[i - 1][j], articles[i - 1].getValue() + Opt[i - 1][j - articles[i - 1].getWeight()]);
        }
        return result;
    }
/*    public double rucksackButGreedyLol(int maxWeight, Article[] articles){

    }
    public ArrayList<Article> sortArticles(Article[] articles){
        //BUBBLESORT LET'S GO

    }*/

}
