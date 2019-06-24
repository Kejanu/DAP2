package Blatt12L.AlexSolution;

import java.util.ArrayList;

public class Node {

    private ArrayList<Edge> adj;
    private int id;

    public Node(int id) {
        this.id = id;
        adj = new ArrayList<Edge>();
    }

    public int getId() {
        return id;
    }


    public void addEdge(Node dst) {
        if (dst == null) {
            throw new IllegalArgumentException();
        }

        Edge e = new Edge(this, dst);

        boolean toAdd = true;

        for (Edge x : adj) {

            if ((x.getSrc().equals(e.getSrc()) && x.getDst().equals(e.getDst())) ||
                    (x.getSrc().equals(e.getDst()) && x.getDst().equals(e.getSrc()))) {

                toAdd = false;
                break;
            }
        }

        if (toAdd) {
            adj.add(e);
            dst.adj.add(e);
        }
    }

    public boolean equals(Node other) {
        return id == other.id;
    }
}
