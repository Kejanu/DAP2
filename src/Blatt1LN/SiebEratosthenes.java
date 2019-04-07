package Blatt1LN;

public class SiebEratosthenes {
    public static void main(String[] args) {
        //test
        SiebEratosthenes test = new SiebEratosthenes();
        int b = test.siebdesEratosthenes(100);
       /* for(int i = 0; i < b.length; i++) {
            if(b[i] == true) {
                System.out.println(i);
            }


        }*/
       System.out.println(b);

    }
    //wie zum Teufel kriege ich das -o rein
    public int siebdesEratosthenes(int n) {

        boolean[] isPrime = new boolean[n+1];
        //0 ist keine Primzahl
        isPrime[0] = false;
        isPrime[1] = false;
        //alles auf true setzen
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        //primzahl bestimmen
        isPrime = primzahl(isPrime);

        //array zurückgeben
        int counter = 0;
        for(int j = 0;j <=n;j++ ) {
            if(isPrime[j]){
                counter++;
            }
        }
        return counter;
    }

    static boolean[] primzahl(boolean[]isPrime) {
        //laufvariable auf 0 setzen
        int i = 0;
        //Primzahlen bestimmen
        while(i < isPrime.length) {
            int j = 2;
            //Bis zur wurzel = Laufzeit sparen yay
            while(j <= Math.sqrt(i)) {
                //wenn i restlos teilbar durch j ist und i ungleich j ist,
                //dann muss es sich um keine Primzahl handeln
                if(i % j == 0 && i != j) {
                    isPrime[i] = false;
                }
                j++;
            }
            i++;
        }
        return isPrime;
    }
}
