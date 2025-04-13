package code.strategies;

import code.Node;
import code.WaterSortSearch;

import java.util.LinkedList;
import java.util.Queue;

public class BFS extends WaterSortSearch {

    public BFS(Node root) {
        super(root);
    }

    public String bfs(boolean visualize) {
        Queue<Node> queue = new LinkedList<>();
        return search(root, queue, 0, false, visualize);
    }
}
