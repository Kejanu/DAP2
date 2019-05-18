package Blatt07K;

import java.util.Random;

public class AlexSolution {

    private static final String MESSAGE = "Please provide one positive integer as argument.";

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Too few or many arguments. "  + MESSAGE);
            return;
        }
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.out.println("Integer must be positive. " + MESSAGE);
            return;
        }
        Random random = new Random();
        String a = randStr(n, random);
        String b = randStr(n, random);

        longestCommonSubsequence(a, b);



    }


    public static String longestCommonSubsequence(String a, String b){

        int[][] chart = new int[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                //todo
                
            }

        }
        return "";


    }

    public static String randStr(int n, Random r) {

        String alphabet =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder(n);
        while (--n >= 0) {
            res.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return res.toString();
    }

}
