package Blatt08L.LongSolution;

import java.lang.reflect.Array;
import java.sql.SQLOutput;

public class EditDistance {
    public static void main(String[] args) {

        if (args.length != 2) {
            System.out.println("Exakt 2 argumente müssen angegeben werden. Du lauch. Aborting now...");
            return;
        }
        System.out.println(distance(args[0], args[1]));
    }

    public static int distance(String a, String b) {
        int n = a.length();
        int m = b.length();
        int[][] D = new int[n + 1][m + 1];
        for (int j = 0; j <= m; j++) {
            D[0][j] = j;
        }
        for (int i = 0; i <= n; i++) {
            D[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int statvalue;
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    statvalue = D[i - 1][j - 1];
                } else {
                    statvalue = Integer.MAX_VALUE;
                }
                D[i][j] = Math.min(Math.min(statvalue, D[i - 1][j - 1] + 1), Math.min(D[i][j - 1] + 1, D[i - 1][j] + 1));
            }
        }
        //dab on those haters
        printEditOperations(D, a, b);
        for(int i = 0; i < D.length; i++){
          for(int j = 0; j < D[0].length;j++){
              System.out.print(D[i][j]+" ");
          }
            System.out.println("");
        }
        return D[n][m];
    }

    public static void printEditOperations(int[][] D, String a, String b) {
        //https://davedelong.com/blog/2015/12/01/edit-distance-and-edit-steps/
        //Diagonal = ersetzen
        //horizontal = einfügen
        //vertikal = löschen
        System.out.println("Loesung fuer '"+a+"' --> '"+b+"' mit Gesamtkosten "+D[a.length()][b.length()]+":");

        int i = a.length() - 1;
        int j = b.length() - 1;
        int maxSize = b.length();
        char[] result = new char[maxSize];
        System.out.println("ich stell nicht vor.");
        System.out.println("Dap tiltet mich");
        System.out.println("möchte schlafen..");
        System.out.println(new String(result));
       // return new String(result);


    }
}
