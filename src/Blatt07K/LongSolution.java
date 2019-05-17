package Blatt07K;

import java.util.Random;

public class LongSolution {
    public static void main(String[] args) {
       String[] a = {"a","b","c","b","d","a","b"};
       String[]b = {"b","d","c","a","b","a"};
       int c[][] = longestCommonSubsequence(a,b);
        System.out.println(c[a.length][b.length]);
    }


    //Verwenden Sie für die Erzeugung von Zufallsfolgen die folgende Funktion:
    String randStr(int n, Random r) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder(n);
        while (--n >= 0) {
            res.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return res.toString();
    }

    //https://moodle.tu-dortmund.de/pluginfile.php/890170/mod_resource/content/3/Vorlesung10-DynamischeProgrammierung-III.pdf
    public static int[][] longestCommonSubsequence(String[] a, String[] b){
        int m = a.length;
        int n = b.length;
        int[][] c = new int[m+1][n+1];
        for(int i = 0; i <=m; i++){
            c[i][0] = 0;
        }
        for(int j = 0; j <=n; j++){
            c[0][j] = 0;
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                calculatinglength(a,b,c,i,j);
            }
        }
        return c;
    }
    public static void calculatinglength(String[] a, String[] b, int[][] c, int i, int j){
        //i wird zu i+1, i-1 wird zu i j ist analog. 300IQ brain
        if(a[i] == b[j]){
            c[i+1][j+1] = c[i][j]+1;
        }
        else{
            if(c[i][j+1] >= c[i+1][j]){
                c[i+1][j+1] = c[i][j+1];
            }
            else{
                c[i+1][j+1] = c[i+1][j];
            }
        }

    }
}
