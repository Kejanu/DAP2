package Blatt09K.Blatt10L.AlexSolution;

import java.util.Arrays;
import java.util.Random;

public class MaximumSubarray {

    public static void main(String[] args){
        int[] input;

        if((args[0].equals("rand") || args[0].equals("randPos") || args[0].equals("randNeg")) && args.length == 2){
            int length;
            try {
                length = Integer.parseInt(args[1]);
            } catch (NumberFormatException e){
                System.out.println("Syntax: Array or [ [ rand | randPos | randNeg ] n ]");
                return;
            }

            input = new int[length];
            Random r = new Random();

            for (int i = 1; i < input.length; ++i){
                if(args[0].equals("rand")){
                    input[i] = rand(r, true, true);
                } else if(args[0].equals("randPos")){
                    input[i] = rand(r, true, false);
                } else {
                    input[i] = rand(r, false, true);
                }
            }

        } else {


            input = new int[args.length];

            try {
                for (int i = 0; i < args.length; ++i) {
                    input[i] = Integer.parseInt(args[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("The values you provided are not valid Integers.");
                return;
            }
        }

        /*
        int[] a = {1,-2,-3,4,-5,6,7,8,-9};
        int[] sub1 = easySolution(a);
        int[] sub2 = dynamicSolution(a);
         */

        //Ausgabe

        System.out.println("\nInput: " + Arrays.toString(input));

        long easyStart, dynamicStart, easyEnd, dynamicEnd;

        System.out.println("\nEasy Solution:\n");
        System.gc();
        easyStart = System.currentTimeMillis();
        System.out.println(Arrays.toString(easySolution(input)));
        easyEnd = System.currentTimeMillis();

        System.out.println("\nDynamic Solution:\n");
        System.gc();
        dynamicStart = System.currentTimeMillis();
        System.out.println(Arrays.toString(dynamicSolution(input)));
        dynamicEnd = System.currentTimeMillis();

        System.out.println("\nTime needed:\n\nEasy Solution: " + (easyEnd - easyStart) + " ms\nDynamicSolution: " + (dynamicEnd - dynamicStart) + " ms");


    }

    public static int[] easySolution(int[] arr){
        int lower = 0;
        int upper = 0;
        int maxSum = 0;

        if(arr.length == 0){
            return new int[0];
        }

        for(int i = 0; i < arr.length; ++i){
            int sum = 0;
            for(int j = i; j < arr.length; ++j){
                sum = sum + arr[j];
                if(sum > maxSum){
                    maxSum = sum;
                    lower = i;
                    upper = j;
                }
            }
        }

        int[] result = new int[upper - lower + 1];
        int count = 0;

        for(int i = lower; i <= upper; ++i){
            result[count] = arr[i];
            ++count;
        }

        return result;
    }

    public static int[] dynamicSolution(int[] arr){

        if(arr.length == 0){
            return new int[0];
        }

        boolean onlyPositive = true;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < 0){
                onlyPositive = false;
                break;
            }
        }

        if(onlyPositive){
            return arr;
        }

        int upper = 0;
        int lower = 0;
        int maxSum = 0;

        int[] dynamicValues = new int[arr.length];

        //Rekursionsabbruch

        if(arr[0] < 0){
            dynamicValues[0] = 0;
            ++lower;
        } else {
            dynamicValues[0] = arr[0];
            maxSum = dynamicValues[0];
        }

        //dynamisches Befüllen

        //todo lower fehlerhaft, irgendwie anders berechnen!
        for (int i = 1; i < dynamicValues.length; ++i){
            if(dynamicValues[i - 1] + arr[i] < 0){
                dynamicValues[i] = 0;
                lower = i + 1;
            } else {
                dynamicValues[i] = dynamicValues[i - 1] + arr[i];
                if(dynamicValues[i] > maxSum){
                    maxSum = dynamicValues[i];
                }
            }
        }

        //System.out.println(Arrays.toString(dynamicValues));

        //obere Grenze festlegen


        if(maxSum == 0){
            upper = 0;
        } else {
            for (int i = lower; i < dynamicValues.length; ++i) {
                if(dynamicValues[i] == maxSum){
                    upper = i;
                }
            }
        }

        //Ausgabe

        int[] result;

        if(lower > upper){
            System.out.println("Hey Listen");
            result = new int[0];
        } else {
            result = new int[upper - lower + 1];
            int count = 0;

            for(int i = lower; i <= upper; ++i){
                result[count] = arr[i];
                ++count;
            }

        }

        System.out.println(Arrays.toString(dynamicValues));
        System.out.println(result.length);
        System.out.println(lower);
        System.out.println(upper);
        System.out.println(maxSum);

        return result;

    }

    public static int rand(Random r, boolean positive, boolean negative){
        if(negative && positive){
            return r.nextInt(200) - 100;
        }
        if(positive){
            return r.nextInt(100);
        }
        if (negative){
            return r.nextInt(100) - 200;
        }
        throw new IllegalArgumentException();
    }


}
