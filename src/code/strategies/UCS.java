package code.strategies;

import code.Node;
import code.WaterSortSearch;

import java.util.PriorityQueue;

public class UCS extends WaterSortSearch {

    public UCS(Node root) {
        super(root);
    }

    public String ucs(boolean visualize) {
        PriorityQueue<Node> minCost = new PriorityQueue<>((node1,node2) -> node1.getPathCost() - node2.getPathCost());
        return search(root, minCost, 0, false, visualize);
    }
}
