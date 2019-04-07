package Blatt1L;

public class LongJavaPraktikumAufg1 {
    public int Euclid(int a, int b) {
        if(a <0 || b < 0) {
            throw new IllegalArgumentException("a and b must be positive");
        }
        if(b==0) {
            return a;
        }
        else{
            return Euclid(b,a % b);
        }
    }

    public static void main(String[] args) {
        LongJavaPraktikumAufg1 ljp = new LongJavaPraktikumAufg1();
        System.out.println(ljp.Euclid(-1,0));
    }
}
