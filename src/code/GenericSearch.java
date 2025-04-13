package code;

import java.util.*;

public abstract class GenericSearch {


    public abstract State getInitialState();

    public List<String> getOperators(Node node) {
        return node.getPossibleOperations();
    }

    public List<Node> expand(Node node) {
        return node.expand();
    }

    public boolean goalTest(Node node) {
        return node.getState().isGoalState();
    }

    public int pathCost(Node node) {
        return node.getPathCost();
    }


    public String search(Node root, Collection<Node> queue, int maxDepth, boolean ids, boolean visualize) {
        Set<String> visited = new HashSet<>();
        queue.add(root);
        int expanded = 0;
        while (!queue.isEmpty()) {
            Node front = deque(queue);
            expanded++;
            if (goalTest(front))
                return generateAnswer(front, expanded, visualize);
            else {
                if(ids && front.getDepth() == maxDepth)
                    continue;
                List<Node> children = expand(front);
                for (Node child : children) {
                    if (!visited.contains(child.getState().sort())) {
                        queue.add(child);
                        visited.add(child.getState().sort());
                    }
                }
            }
        }

        return "NOSOLUTION";
    }

    public Node deque(Collection<Node> queue) {
        Node front = null;
        if(queue instanceof Queue)
            front = ((Queue<Node>) queue).poll();
        else if(queue instanceof Stack)
            front = ((Stack<Node>) queue).pop();
        else if(queue instanceof PriorityQueue)
            front = ((PriorityQueue<Node>) queue).poll();
        return front;
    }

    public String generateAnswer(Node goal, int expanded, boolean visualize) {
        return goal.generatePlan(visualize) + ";" + pathCost(goal)  + ";" + expanded;
    }
}
