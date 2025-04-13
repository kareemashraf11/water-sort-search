package code.strategies;

import code.Node;
import code.WaterSortSearch;

import java.util.Stack;

public class DFS extends WaterSortSearch {

    public DFS(Node root) {
        super(root);
    }

    public String dfs(int maxDepth, boolean ids, boolean visualize) {
        Stack<Node> stack = new Stack<>();
        return search(root, stack, maxDepth, ids, visualize);
    }
}
