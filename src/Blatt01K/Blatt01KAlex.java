package Blatt01K;

public class Blatt01KAlex {



        public static void main(String[] args){
            //System.out.println(euclid(264,846));
            System.out.println(prime(40,true));

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
                        System.out.print(" " + i);

                    }
                }
            }
            System.out.println("");
            return count;

        }



}
