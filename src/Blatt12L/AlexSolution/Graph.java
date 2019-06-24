package Blatt12L.AlexSolution;

import java.util.ArrayList;

public class Graph {

    private ArrayList<Node> nodes;

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    public boolean contains(int id) {

        for (Node n : nodes) {
            if (n.getId() == id) {
                return true;
            }
        }

        return false;

    }

    public boolean addNode(int id) {
        if (!contains(id)) {
            Node toInsert = new Node(id);
            nodes.add(toInsert);
            return true;
        }
        return false;
    }


}
