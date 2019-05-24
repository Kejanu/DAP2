package Blatt08LAlex;

public class EditDistance {

    private static final String MESSAGE = "Proper usage: [ String String [   | -o ] ]";

    public static void main(String[] args){

        if(args.length < 2 || args.length > 3){
            System.out.println("Too few or many arguments " + MESSAGE);
            return;
        }


        if(args.length == 3 && args[2].equals("-o")) {

            String output = "";
            int d = distance(args[0], args[1], true, output);



        } else if(args.length == 3){
            System.out.println("Last argument is invalid " + MESSAGE);
            return;
        } else {
            int d = distance(args[0], args[1], false, null);
            System.out.println("Gesamtkosten: " + d);
        }




    }

    public static int distance(String a, String b, boolean output, String o){

        if(o == null && output)
            throw new IllegalArgumentException();


        int[][] chart = new int[a.length()][b.length()];


        for (int i = 1; i < a.length() ; i++) {
            chart[i][0] = i;
        }
        for (int i = 1; i < b.length() ; i++) {
            chart[0][i] = i;
        }

        for (int i = 1; i < a.length() ; i++) {
            for (int j = 1; j < b.length() ; j++) {

                int value1;
                if(a.charAt(i) == b.charAt(j)){
                    value1 = chart[i-1][j-1];
                } else {
                    value1 = Integer.MAX_VALUE;
                }

                int value2 = chart[i-1][j-1] + 1;
                int value3 = chart[i][j-1] + 1;
                int value4 = chart[i-1][j] + 1;

                chart[i][j] = Math.min(Math.min(value1, value2), Math.min(value3, value4));
            }

        }

        if(output){
            //todo
        }

        return chart[a.length() - 1][b.length() - 1];
    }
}
