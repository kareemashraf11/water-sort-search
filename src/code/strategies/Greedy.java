package code.strategies;

import code.Node;
import code.WaterSortSearch;

import java.util.PriorityQueue;

public class Greedy extends WaterSortSearch {

    public Greedy(Node root) {
        super(root);
    }

    public String greedy(int heuristic, boolean visualize) {
        PriorityQueue<Node> queue;
        switch(heuristic) {
            case 1: queue = new PriorityQueue<>((node1,node2) -> node1.getState().heuristic1() - node2.getState().heuristic1()); break;
            case 2: queue = new PriorityQueue<>((node1,node2) -> node1.getState().heuristic2() - node2.getState().heuristic2()); break;
            default: queue = new PriorityQueue<>(); break;
        }
        return search(root, queue, 0, false, visualize);
    }
}
