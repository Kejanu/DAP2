package Blatt05K.LongSolution;

import Templates.InputValidation;

import java.util.Arrays;

public class CoinChangingProblem {
    public static void main(String[] args) {
        if (args.length != 2 || !InputValidation.parameterIsInteger(args[1])) {
            System.out.println("2nd Parameter was not Integer or more or less than 2 parameters have been inputted. \n Syntax: Euro|Alternative Integer \n REEEEEEEE");
            return;
        }
        if (args[0].equals("Euro")) {
            int[] w = {200, 100, 50, 20, 10, 5, 2, 1};
            int[] solution = change(Integer.parseInt(args[1]), w);
            System.out.println(Arrays.toString(solution));
        } else if (args[0].equals("Alternative")) {
            //initiliasion bullshit
            int[] w = {200, 100, 50, 20, 10, 5, 4, 2, 1};
            int[] solution = change(Integer.parseInt(args[1]), w);
            System.out.println(Arrays.toString(solution));
        } else {
            System.out.println("Input was not correct. \n Syntax: Euro|Alternative Integer");
            return;
        }
    }

    //Liefert das implementierte Verfahren stets optimale Lösungen mit einer minimalen Anzahl an
    //Münzen?
    //Antwort nein lol.
    public static int[] change(int b, int[] w) {
        int[] result = new int[w.length];
        int positionOfNote = 0;
        while (b > 0) {
            //Ist B ≥ wk..
            if (b >= w[positionOfNote]) {
                //, dann gibt er n Münzen der Wertigkeit wk aus mit 0 ≤ B−n·wk < wk
                result[positionOfNote] = b / w[positionOfNote];
                //und verringert den Betrag entsprechend zu B ← B−n·wk
                b = b - w[positionOfNote] * result[positionOfNote];
            }
            positionOfNote++;
        }
        return result;
    }
}
