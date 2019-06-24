package Blatt12L.AlexSolution;

public class Edge {

    private Node src, dst;

    public Edge(Node a, Node b) {
        src = a;
        dst = b;
    }

    //todo Getter

    public Node getSrc() {
        return src;
    }

    public Node getDst() {
        return dst;
    }
}
