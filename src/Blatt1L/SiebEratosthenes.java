package Blatt1L;

public class SiebEratosthenes {
    public static void main(String[] args) {
        //test
        SiebEratosthenes test = new SiebEratosthenes();
        boolean[] b = test.siebdesEratosthenes(500);
        for(int i = 2; i < b.length; i++) {
            if(b[i] == true) {
                System.out.println(i);
            }
        }
    }
    public boolean[] siebdesEratosthenes(int n) {

        boolean[] b = new boolean[n];
        //0 ist keine Primzahl
        b[0] = false;
        //alles auf true setzen
        for (int i = 1; i < n; i++) {
            b[i] = true;
        }
        //primzahl bestimmen
        b = primzahl(b);

        //array zurückgeben
        return b;
    }

    static boolean[] primzahl(boolean[]b) {
        //laufvariable auf 0 setzen
        int i = 0;
        //Primzahlen bestimmen
        while(i < b.length) {
            int j = 2;
            //Bis zur wurzel = Laufzeit sparen yay
            while(j <= Math.sqrt(i)) {
                //wenn i restlos teilbar durch j ist und i ungleich j ist,
                //dann muss es sich um keine Primzahl handeln
                if(i % j == 0 && i != j) {
                    b[i] = false;
                }
                j++;
            }
            i++;
        }
        return b;
    }
}
