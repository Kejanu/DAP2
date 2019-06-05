package Blatt09K.KevinSolution;

import Templates.InputValidation;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KnapsackProblem {

    private static final String PROPER_USAGE = "Amount of Goods (int), Max WeightLimit (int), weightMultiplier (int)";

    public static void main(String[] args) {

        InputValidation validation = new InputValidation(PROPER_USAGE);
        validation.setNoneNegativeNumbers(true);
        validation.setPattern(int.class, int.class, int.class);

        if (!validation.validate(args)) {
            return;
        }

        int amountOfGoods = Integer.parseInt(args[0]);
        int maxWeight = Integer.parseInt(args[1]);
        int weightMultiplier = Integer.parseInt(args[2]);

        //System.out.println(knapsackLecture(createArticles(amountOfGoods, weightMultiplier), maxWeight, true));

        fixedAmountOfItems(amountOfGoods, maxWeight, weightMultiplier);
        fixedMaxWeight(amountOfGoods, maxWeight, weightMultiplier);

        // Normal comparison
        ArrayList<Article> articles;
        ArrayList<Integer> greedy, dynamic;

        greedy = new ArrayList<>();
        dynamic = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            articles = createArticles(amountOfGoods, weightMultiplier);
            greedy.add(knapsackGreedy(articles, maxWeight, false));
            dynamic.add(knapsackLecture(articles, maxWeight, false));
        }

        System.out.println("Greedy: \t" + greedy);
        System.out.println("Dynamic: \t" + dynamic);
    }

    private static int knapsackGreedy(ArrayList<Article> articles, int maxWeight, boolean print) {
        articles = articles.stream().sorted(Comparator.comparingDouble(Article::getRatio).reversed()).collect(Collectors.toCollection(ArrayList::new));

        if (print) {
            System.out.println(articles);
            System.out.println("MaxWeight: " + maxWeight);
        }

        int result = 0, currentWeight = 0;

        for (int i = 0; i < articles.size(); ++i) {
            if (articles.get(i).getWeight() + currentWeight <= maxWeight) {

                result += articles.get(i).getValue();
                currentWeight += articles.get(i).getWeight();

            }
        }
        return result;
    }

    private static void fixedMaxWeight(int amountOfGoods, int maxWeight, int weightMultiplier) {
        // Increasing Amount, fixed MaxWeight

        int maxUpperAmount = 10000;
        int amountSteps = 100;
        double[] xData = new double[maxUpperAmount / amountSteps];
        double[] yData = new double[maxUpperAmount / amountSteps];

        ArrayList<Article> articles;
        long tStart, tEnd;
        int index = 0;

        System.out.println("Running fixed MaxWeight...");
        for (int i = amountOfGoods; i < maxUpperAmount; i = i + amountSteps) {
            articles = createArticles(i, weightMultiplier);

            tStart = System.currentTimeMillis();
            knapsackLecture(articles, i, false);
            tEnd = System.currentTimeMillis();

            xData[index] = i;
            yData[index] = tEnd - tStart;
            ++index;
        }

        XYChart chart = QuickChart.getChart("Fixed Max Weight", "Amount", "Time", "Time(Amount)", xData, yData);
        new SwingWrapper(chart).displayChart();
    }

    private static void fixedAmountOfItems(int amountOfGoods, int maxWeight, int weightMultiplier) {
        // Increasing maxWeight, fixed Amount

        int maxUpperWeight = 10000000;
        int weightSteps = 100000;
        double[] xData = new double[maxUpperWeight / weightSteps];
        double[] yData = new double[maxUpperWeight / weightSteps];

        ArrayList<Article> articles = createArticles(amountOfGoods, weightMultiplier);
        long tStart, tEnd;
        int index = 0;

        System.out.println("Running fixed Amount...");
        for (int i = maxWeight; i < maxUpperWeight; i = i + weightSteps) {
            tStart = System.currentTimeMillis();
            knapsackLecture(articles, i, false);
            tEnd = System.currentTimeMillis();

            xData[index] = i;
            yData[index] = tEnd - tStart;
            ++index;
        }

        XYChart chart = QuickChart.getChart("Fixed Amount", "MaxWeight", "Time", "Time(MaxWeight)", xData, yData);
        new SwingWrapper(chart).displayChart();
    }

    private static ArrayList<Article> createArticles(int amountOfGoods, int weightMultiplier) {
        return  IntStream.range(0, amountOfGoods).mapToObj(e ->
                        new Article(
                                randomValue(),
                                randomWeight(weightMultiplier)
                        )
                ).collect(Collectors.toCollection(ArrayList::new));
    }

    private static int knapsackLecture(ArrayList<Article> articles, int maxWeight, boolean print) {
        if (print)
            System.out.println("KnapSack-Input:\n" + articles.toString() + "\n");

        int[][] memo = new int[articles.size() + 1][maxWeight + 1];

        for (int j = 0; j < maxWeight; ++j) {
            memo[0][j] = 0;
        }

        // Iterate of Articles
        for (int i = 1; i <= articles.size(); ++i) {

            for (int j = 0; j <= maxWeight; ++j) {

                if (j < articles.get(i - 1).getWeight()) {
                    memo[i][j] = memo[i - 1][j];
                }
                else {
                    memo[i][j] = Math.max(
                            memo[i - 1][j],
                            memo[i - 1][j - articles.get(i - 1).getWeight()] + articles.get(i - 1).getValue()
                    );
                }
            }
        }

        if (print)
            for (int i = memo.length - 1; i > -1; --i) {
                System.out.println(Arrays.toString(memo[i]));
            }

        return memo[articles.size()][maxWeight];
    }

    private static int randomWeight(int weightMultiplier) {
        double val = (double) ThreadLocalRandom.current().nextInt(8000, 12501) / 10000;
        val *= weightMultiplier;
        return (int) val;
    }

    private static int randomValue() {
        return ThreadLocalRandom.current().nextInt(100, 1000);
    }
}
