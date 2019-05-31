package Blatt9K.AlexSolution;

import java.util.Random;

public class Rucksackproblem {

    public static final String MESSAGE = " ";

    public static void main(String[] args){
        if(args.length != 3){
            System.out.println("Too few or many arguments. " + MESSAGE);
            return;
        }

        int n = 0;
        int w = 0;
        double p = 0;

        try{
            n = Integer.parseInt(args[0]);
            w = Integer.parseInt(args[1]);
            p = Double.parseDouble(args[2]);
        } catch (NumberFormatException e){
            System.out.println(MESSAGE);
            return;
        }

        Article[] articles = new Article[n];
        Random random = new Random();

        double upper = 1.25 * p;
        double lower = 0.8 * p;


        for (int i = 0; i < articles.length ; i++) {
            double randomValue = lower + (upper - lower) * random.nextDouble();
            int weight = (int)  Math.round(randomValue);

            if (weight < lower){
                ++weight;
            }

            if (weight > upper){
                --weight;
            }
            articles[i] = new Article( weight, random.nextInt(900) + 100);
        }





    }
}
