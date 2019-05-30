package Blatt9K.AlexSolution;

public class SearchTree {

    public static void main(String[] args){

        SearchTree[] tree = new SearchTree[5];
        tree[0] = new SearchTree(null);
        tree[1] = new SearchTree( 1);

        int[] a = {1,6,3,8,0,9,12,5,7};
        tree[2] = new SearchTree(a);

        int[] b = {1,3,2,5,7,6,9,8};
        tree[3] = new SearchTree(b);
        tree[4] = new SearchTree();
    }

    private SearchTree lc, rc;
    private Integer value;

    public SearchTree(int[] values){

        if(values != null){
            if(values.length != 0){
                for (int i = 0; i < values.length; ++i){
                    add(values[i]);
                }
            }
        }
        System.out.println("\nPreOrder:");
        preOrder();
        System.out.println("\nInOrder");
        inOrder();
        System.out.println("\nPostOrder");
        postOrder();
        System.out.println("\n");

    }

    private SearchTree(int value){
        this.value = value;
    }

    private SearchTree(){
    }

    public void add(int n){


        if(isLeaf()){

            if(isEmpty()){
                value = n;
            } else {
                if (n <= this.value) {
                    System.out.println("HEY LISTEN " + value + ">=" + n);
                    this.lc = new SearchTree(n);
                } else {
                    System.out.println("HEY LISTEN " + value + "<" + n);
                    this.rc = new SearchTree(n);
                }
            }

        } else {

            if(n <= this.value){
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

         /*

        if(this.isEmpty()){
            value = n;
        } else {
            SearchTree last = null;
            SearchTree current = this;

            while (current != null) {
                if (current.value >= n) {
                    last = current;
                    current = current.lc;
                } else {
                    last = current;
                    current = current.rc;
                }
            }

            if(last.value >= n){
                last.lc = new SearchTree(n);
            } else {
                last.rc = new SearchTree(n);
            }
        }


         */



    }

    public boolean isLeaf(){
        return this.lc == null && this.rc == null;
    }

    public boolean isEmpty(){
        return this.value == null;
    }

    public  Integer getValue(){
        return this.value;
    }

    public void setValue(Integer n){
        if(n == null && (this.lc != null || this.rc != null)){
            throw new IllegalArgumentException();
        }
        this.value = n;
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

