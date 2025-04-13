package code.strategies;

import code.Node;
import code.WaterSortSearch;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar extends WaterSortSearch {

    public AStar(Node root) {
        super(root);
    }

    public String astar(int heuristic, boolean visualize) {
        PriorityQueue<Node> queue;
        switch(heuristic) {
            case 1: queue = new PriorityQueue<>(Comparator.comparingInt((node1) -> node1.getPathCost() + node1.getState().heuristic1())); break;
            case 2: queue = new PriorityQueue<>(Comparator.comparingInt((node1) -> node1.getPathCost() + node1.getState().heuristic2())); break;
            default: queue = new PriorityQueue<>();
        }
        return search(root, queue, 0, false, visualize);
    }
}
