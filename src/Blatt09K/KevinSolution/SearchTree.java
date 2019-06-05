package Blatt09K.KevinSolution;

import java.util.Arrays;

public class SearchTree {

    private Node root;

    public static void main(String[] args) {
        int[] values;

        try {
            values = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        }
        catch (NumberFormatException nfe) {
            System.out.println("Your provided numbers contain a NON-Number. Please provide only numbers. Thanks.\n" +
                    "Programm aborting...");
            return;
        }

        SearchTree bt = new SearchTree(values);

        System.out.println("Pre-Order");
        bt.traversePreOrder();

        System.out.println("\nIn-Order");
        bt.traverseInOrder();

        System.out.println("\nPost-Order");
        bt.traversePostOrder();
    }

    public SearchTree(int[] values) {
        Arrays.stream(values).forEach(this::add);
    }

    public void traversePreOrder() {
        traversePreOrder(root);
    }

    public void traverseInOrder() {
        traverseInOrder(root);
    }

    public void traversePostOrder() {
        traversePostOrder(root);
    }


    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(node.value + " ");
            traverseInOrder(node.right);
        }
    }

    private void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.left);
            traversePostOrder(node.right);
            System.out.print(node.value + " ");
        }
    }


    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node current, int value) {

        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        }
        else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    private class Node {
        private int value;
        private Node left;
        private Node right;

        Node(int value) {
            this.value = value;
            right = null;
            left = null;
        }
    }
}
