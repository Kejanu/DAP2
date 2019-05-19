package Blatt07K.LongSolution;

import java.util.Random;

public class LongSolution {
    public static void main(String[] args) {
        String a = "abcbdab";
        String b = "bdcaba";
        try {
            long tStart, tEnd;

            Random r = new Random();
            //String a = randStr(Integer.parseInt(args[0]), r);
            //String b = randStr(Integer.parseInt(args[0]), r);

            char achar[] = a.toCharArray();
            char bchar[] = b.toCharArray();

            //print elements of String array
            tStart = System.currentTimeMillis();
            int[][] c = longestCommonSubsequence(achar, bchar);
            tEnd = System.currentTimeMillis();
            int maxlength = c[achar.length][bchar.length];
            System.out.println("longest common substring is: " + c[achar.length][bchar.length]);
            System.out.println("substring is: " + printLCS(achar, bchar, maxlength, c));
            System.out.println("it also needed: " + (tEnd - tStart) + " miliseconds to calculate the length of LCS.\n" +
                    "that's faster than the tutor would need to come to us to look at our code.");
        } catch (Exception ex) {
            System.out.println("something went wrong lol.");
        }
    }


    //Verwenden Sie für die Erzeugung von Zufallsfolgen die folgende Funktion:
    static String randStr(int n, Random r) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder res = new StringBuilder(n);
        while (--n >= 0) {
            res.append(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return res.toString();
    }

    //https://moodle.tu-dortmund.de/pluginfile.php/890170/mod_resource/content/3/Vorlesung10-DynamischeProgrammierung-III.pdf
    public static int[][] longestCommonSubsequence(char[] a, char[] b) {
        int m = a.length;
        int n = b.length;
        int[][] c = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            c[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            c[0][j] = 0;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                calculatinglength(a, b, c, i, j);
            }
        }
        return c;
    }

    public static void calculatinglength(char[] a, char[] b, int[][] c, int i, int j) {
        //i wird zu i+1, i-1 wird zu i j ist analog. 300IQ brain
        //primitive types ==, Classes .equals()
        if (a[i] == b[j]) {
            c[i + 1][j + 1] = c[i][j] + 1;
        } else {
            if (c[i][j + 1] >= c[i + 1][j]) {
                c[i + 1][j + 1] = c[i][j + 1];
            } else {
                c[i + 1][j + 1] = c[i + 1][j];
            }
        }
    }

    public static String printLCS(char[] a, char[] b, int maxlength, int[][] c) {
        int i = a.length - 1;
        int j = b.length - 1;
        char[] result = new char[maxlength];
        while (i >= 0 && j >= 0) {
            //if same trivial
            if (a[i] == b[j]) {
                result[maxlength - 1] = a[i];
                i--;
                j--;
                maxlength--;
            }
            //if not same check "stair" in the table
            else if (c[i][j + 1] > c[i + 1][j]) {
                i--;
            } else {
                j--;
            }
        }
        //https://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string
        return new String(result);
    }
}
