package Blatt9K.LongSolution;

public class SearchTree {
    private SearchTree lc;
    private SearchTree rc;
    private Integer root;

    public static void main(String[] args) {
        int[] arr = {8, 4, 6, 3, 9, 7};
        SearchTree tree = new SearchTree(arr);
        System.out.println("Preorder: ");
        tree.preOrder();
        System.out.println("");
        System.out.println("InOrder: ");
        tree.inOrder();
        System.out.println("");
        System.out.println("PostOrder: ");
        tree.postOrder();
    }

    public SearchTree(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                add(arr[i]);
            }
        }
    }

    //minibäume
    public SearchTree(int x) {
        this.root = x;
    }

    public void add(int toAdd) {
        if (root == null) {
            root = (Integer) toAdd;
        } else if (toAdd < root.intValue()) {
            if (this.lc == null) {
                this.lc = new SearchTree(toAdd);
            } else {
                this.lc.add(toAdd);
            }
        } else if (toAdd > root.intValue()) {
            if (this.rc == null) {
                this.rc = new SearchTree(toAdd);
            } else {
                this.rc.add(toAdd);
            }
        }
    }

    //https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    public void preOrder() {
        System.out.print(root.intValue() + ", ");
        if (this.lc != null)
            this.lc.preOrder();
        if (this.rc != null)
            this.rc.preOrder();
    }

    public void inOrder() {
        if (this.lc != null)
            this.lc.inOrder();
        System.out.print(root.intValue() + ", ");
        if (this.rc != null)
            this.rc.inOrder();
    }

    public void postOrder() {
        if (this.lc != null)
            this.lc.postOrder();
        if (this.rc != null)
            this.rc.postOrder();
        System.out.print(root.intValue() + ", ");
    }
}
