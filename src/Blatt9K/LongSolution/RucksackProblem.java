package Blatt9K.LongSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class RucksackProblem {
    public static void main(String[] args) {
        Article a1 = new Article(11, 5);
        Article a2 = new Article(5, 2);
        Article a3 = new Article(2, 1);
        Article a4 = new Article(8, 3);
        Article a5 = new Article(14, 7);
        Article a6 = new Article(9, 4);
        RucksackProblem testRucksack;
        if(args.length!=3){
            System.out.println("Exactly 3 Parameters must be given. Integer Double Double");
            return;
        }
        try{
            int ntest = Integer.parseInt(args[0]);
            double ptest = Integer.parseInt(args[1]);
            int maxWeighttest = Integer.parseInt(args[2]);

        }
        catch(NumberFormatException ex){
            System.out.println("Parameters were not in the correct Format. Integer Double Double");
        }
    //    Article[] articles = {a1, a2, a3, a4, a5, a6};
        RucksackProblem lol = new RucksackProblem(6, 0, 2);
   //     System.out.println(lol.rucksack(articles));
   //     System.out.println(lol.rucksackButGreedyLol(articles));

        testRucksack = new RucksackProblem(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        Article[] articlestest = testRucksack.randomArticles();
        System.out.println("folgende Werte wurden random generiert: ");
        for(int i = 0; i < articlestest.length; i++){
            System.out.println(articlestest[i].toString());
        }
        //laufzeitmessung
        long tStart, tEnd;


        System.out.println("Dynamic Method: ");
        tStart = System.currentTimeMillis();
        double wert = testRucksack.rucksack(articlestest);
        tEnd = System.currentTimeMillis();
        System.out.println("Optimaler wert: "+wert);
        System.out.println("Laufzeit betrug: "+ ( tEnd  -tStart) + " msecs.");
        System.out.println("Greedy Method: ");
        tStart = System.currentTimeMillis();
        double optgreedy = testRucksack.rucksackButGreedyLol(articlestest);
        tEnd = System.currentTimeMillis();
        testRucksack.rucksackButGreedyLolbutprint(articlestest);
        System.out.println("Optimaler wert: "+optgreedy);
        System.out.println("Laufzeit betrug: "+ ( tEnd-tStart) + " msecs.");



    }

    private int n;
    private int maxWeight;
    private double p;

    public RucksackProblem(int n, int maxWeight, double p) {
        this.n = n;
        this.maxWeight = maxWeight;
        this.p = p;
    }

    public Article[] randomArticles() {
        Article[] articles = new Article[n];
        //randomGenerator
        int randomLowerBoundWeight = (int)(0.8 * p);
        int randomUpperBoundWeight = (int) (1.25 * p);
        int randomLowerBoundValue = (int) (100);
        int randomUpperBoundValue = (int) (1000);
        //        randomLowerBoundValue+ (upperBound - randomLowerBoundValued) * generator.nextDouble()

        for (int i = 0; i < n; i++) {
            int value = (int)getRandomIntegerBetweenRange(randomLowerBoundValue, randomUpperBoundValue);
            int weight = (int)getRandomIntegerBetweenRange(randomLowerBoundWeight, randomUpperBoundWeight);
            Article temp = new Article(value,weight);
            articles[i] = temp;
        }
        return articles;
    }
    //https://dzone.com/articles/random-number-generation-in-java
    public static double getRandomIntegerBetweenRange(double min, double max){ double x = (int)(Math.random()*((max-min)+1))+min; return x; }

    public double rucksack(Article[] articles) {
        double[][] Opt = new double[n + 1][maxWeight + 1];
        for (int j = 0; j < maxWeight + 1; j++) {
            Opt[0][j] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < maxWeight+1; j++) {
                //berechne nach Rekursion..
                Opt[i][j] = optrec(i, j, articles, Opt);
            }
        }
        for(int i = 0; i < n+1; i++){
            System.out.println(Arrays.toString(Opt[i]));
        }
        return Opt[n][maxWeight];
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
   public double rucksackButGreedyLol(Article[] articles){
        sortArticlesAscending(articles);
        int currentWeight = 0;
        int articlesIndex = 0;
        double articleSum = 0;
        //nimm die ersten Objekte raus, für die das gut ist
        while(currentWeight < maxWeight && articlesIndex < articles.length){
            if(articles[articlesIndex].getWeight() <= maxWeight-currentWeight){
                currentWeight = currentWeight + articles[articlesIndex].getWeight();
                articleSum = articleSum + articles[articlesIndex].getValue();
            }
            articlesIndex++;
        }
        return articleSum;
    }
    public void rucksackButGreedyLolbutprint(Article[] articles){
        sortArticlesAscending(articles);
        int currentWeight = 0;
        int articlesIndex = 0;
        //nimm die ersten Objekte raus, für die das gut ist
        while(currentWeight <= maxWeight && articlesIndex < articles.length){
            if(articles[articlesIndex].getWeight() <= maxWeight-currentWeight){
                currentWeight = currentWeight + articles[articlesIndex].getWeight();
                System.out.println("Article: [Value: "+articles[articlesIndex].getValue()+", Weight: " + articles[articlesIndex].getWeight() + "] wurde mit reingenommen.");
            }
            articlesIndex++;
        }
    }

    public void sortArticlesAscending(Article[] articles){
        ArrayList<Article> toSort = new ArrayList<>();
        //screw sorting Algorithms
        for(Article artikel:articles){
            toSort.add(artikel);
        }
        Collections.sort(toSort);
        int i = 0;
        for(Article artikel: toSort){
            articles[i] = artikel;
            i++;
        }
    }

}
