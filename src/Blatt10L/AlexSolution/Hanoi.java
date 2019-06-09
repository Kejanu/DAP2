package Blatt10L.AlexSolution;

public class Hanoi {

    public static void main(String[] args){
        if(args.length != 1){
            System.out.println("Please provide the size of your tower.");
            return;
        }

        int n;

        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e){
            System.out.println("The size you provided is invalid.");
            return;
        }

        if(n < 0){
            System.out.println("The size you provided is invalid.");
            return;
        }

        System.out.println("Steps:\n");
        move(n, 'A', 'B', 'C');
    }


    public static void move(int quantity, char start, char help, char target){

        if(quantity > 0) {
            if (quantity == 1) {
                System.out.println("Verschiebe oberste Scheibe von " + start + " nach " + target);
            } else {
                move(quantity - 1, start, target, help);
                System.out.println("Verschiebe oberste Scheibe von " + start + " nach " + target);
                move(quantity - 1, help, start, target);
            }
        }
    }
}
