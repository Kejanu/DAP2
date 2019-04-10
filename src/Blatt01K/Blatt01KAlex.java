package Blatt01K;

public class Blatt01KAlex {



        public static void main(String[] args){

            if(args.length<2){
                System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
                throw new IllegalArgumentException();
            }

           if(args[0].equals("Euclid")){

               if(args.length!=3){
                   System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
                   throw new IllegalArgumentException();
               }

               int v1=0;
               int v2=0;
               try{
                   v1=Integer.parseInt(args[1]);
                   v2=Integer.parseInt(args[2]);
               }
               catch (Exception e){
                   System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
                   throw new IllegalArgumentException();
               }

               int result= euclid(v1, v2);
               System.out.println("Ergebnis: " + result);
           }
           else if(args[0].equals("Eratosthenes")) {

               if (args.length > 3) {
                   System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
                   throw new IllegalArgumentException();
               }

               int v = 0;
               try {
                   v = Integer.parseInt(args[1]);
               } catch (Exception e) {
                   System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
                   throw new IllegalArgumentException();
               }

               if (args.length == 2) {
                   System.out.println("Anzahl: " + prime(v, false));
               } else if (args[2].equals("-o")) {
                   System.out.println("Anzahl: " + prime(v, true));
               } else if (args[2].equals("")) {
                   System.out.println("Anzahl: " + prime(v, false));
               } else {
                   System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
                   throw new IllegalArgumentException();
               }
           }else{
               System.out.println("Falsche Eingabe! Korrekte Eingaben: [ Euclid | Value1 | Value2 ] [ Eratosthenes | Value | [   | -o ] ]");
               throw new IllegalArgumentException();
           }
        }




        //1.1

        public static int euclid(int a, int b){

            if(b==0){           //sonst würde durch 0 geteilt werden
                return a;
            }
            else{
                return euclid(b, a%b);
            }
        }

        //1.2

        public static int prime(int n, boolean output){

            if(n<2){  //2 ist kleinste Primzahl
                return 0;
            }

            boolean[] isPrime= new boolean[n+1]; //Feld anlegen

            for(int i=2;i<isPrime.length; i++){ //da Feld mit false initialisiert wird alles ab Index 2 auf true
                isPrime[i]=true;
            }

            for(int i=2; i<=n/2; i++){ //Vielfache von jedem index der ersten Hälfte des Arrays, da danach selbst das kleinste Vielfache 2 über das Array hinaus geht

                for(int j=2; i*j<=n; j++){ //Abbruch, wenn vielfaches größer als größter Indexwert des Arrays
                    isPrime[i*j]=false;
                }

            }

            //Zählen + Ausgabe falls gewünscht
            int count =0;
            System.out.println("");
            for(int i=2; i<isPrime.length; i++){
                if(isPrime[i]){
                    count++;
                    if(output){
                        System.out.print(i + " ");

                    }
                }
            }
            System.out.println("");
            return count;

        }



}
