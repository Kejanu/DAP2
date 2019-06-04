package Blatt9K.AlexSolution;

public class SearchTree {

    public static void main(String[] args){

        /*
        SearchTree[] tree = new SearchTree[5];
        tree[0] = new SearchTree(null);
        tree[1] = new SearchTree( 1);

        int[] a = {1,6,3,8,0,9,12,5,7};
        tree[2] = new SearchTree(a);

        int[] b = {1,3,2,5,7,6,9,8};
        tree[3] = new SearchTree(b);
        tree[4] = new SearchTree();
         */

        //Eingabe in Array zum Einfügen parsen

        int[] arr = new int[args.length];

        for (int i = 0; i < args.length ; i++) {
            try {
                arr[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e){
                System.out.println("Your input was invalid. Please provide Integers to add to the tree.");
                return;
            }

        }

        SearchTree tree = new SearchTree(arr);

        //Ausgabe

        System.out.println("\nPreOrder:");
        tree.preOrder();
        System.out.println("\nInOrder");
        tree.inOrder();
        System.out.println("\nPostOrder");
        tree.postOrder();
        System.out.println("\n");
    }

    private SearchTree lc, rc;
    private Integer value;

    public SearchTree(int[] values){

        //nur ein komplett leerer Baum kann null enthalten
        if(values != null){
            if(values.length != 0){
                for (int i = 0; i < values.length; ++i){
                    add(values[i]);
                }
            }
        }
    }

    private SearchTree(int value){
        //null kann nicht übergeben werden, da Typ int
        this.value = value;
    }

    public void add(int n){

        //null kann nicht als Argument übergeben werden, da Typ int

        if(isLeaf()){

            if(isEmpty()){  //nur ein komplett leerer Baum kann null enthalten
                value = n;
            } else {
                if(n == this.value) { //Falls Wert schon vorhanden, nicht einfügen, da sonst kein Suchbaum
                    return;
                } else if (n < this.value) {
                    this.lc = new SearchTree(n);
                } else {
                    this.rc = new SearchTree(n);
                }
            }

        } else {

            if(n == this.value){ //s.o,
                return;
            } else if(n < this.value){
                if(this.lc == null) {
                    this.lc = new SearchTree(n);
                } else {
                    this.lc.add(n);
                }

            } else {
                if(this.rc == null) {
                    this.rc = new SearchTree(n);
                } else {
                    this.rc.add(n);
                }
            }
        }
    }

    public boolean isLeaf(){
        return this.lc == null && this.rc == null;
    }

    public boolean isEmpty(){
        return this.value == null;
    }

    public Integer getValue(){
        return this.value;
    }

    public void preOrder(){

        System.out.print(this.getValue() + " ");
        if(this.lc != null){
            this.lc.preOrder();
        }
        if(this.rc != null){
            this.rc.preOrder();
        }
    }

    public void inOrder(){

        if(this.lc != null){
            this.lc.inOrder();
        }
        System.out.print(this.getValue() + " ");
        if(this.rc != null){
            this.rc.inOrder();
        }
    }

    public void postOrder(){

        if(this.lc != null){
            this.lc.postOrder();
        }
        if(this.rc != null){
            this.rc.postOrder();
        }
        System.out.print(this.getValue() + " ");
    }
}

