package code;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private State state;
    private Node parent;
    private String operator;
    private int depth;
    private int pathCost;

    public Node(State state, Node parent, String operator, int depth, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
    }

    public List<Node> expand() {
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < state.getBottles().size(); i++) {
            for (int j = 0; j < state.getBottles().size(); j++) {
                if(i != j && state.canPour(i, j)) {
                    State child = new State(state);
                    int times = child.pour(i, j);
                    Node childNode = new Node(child, this, generateOperator(i, j), depth + 1, pathCost + times);
                    children.add(childNode);
                }
            }
        }
        return children;
    }

    public String generateOperator(int i, int j) {
        return "pour_" + i + "_" + j;
    }

    public State getState() {
        return state;
    }

    public int getPathCost() {
        return pathCost;
    }

    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public String generatePlan() {
        StringBuilder sb = new StringBuilder();
        generatePlanHelper(sb);
        return sb.toString();
    }

    private void generatePlanHelper(StringBuilder sb) {
        if (parent != null)
            parent.generatePlanHelper(sb);
        if (operator != null) {
            if (sb.length() > 0)
                sb.append(",");
            sb.append(operator);
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", parent=" + parent +
                ", operator='" + operator + '\'' +
                ", depth=" + depth +
                ", pathCost=" + pathCost +
                '}';
    }
    public int heuristic() {
        // A simple heuristic: number of incorrectly sorted bottles
        int misplacedBottles = 0;
        for (Bottle bottle : state.getBottles()) {
            if (!bottle.isUniformColor()) {
                misplacedBottles++;
            }
        }
        return misplacedBottles;
    }
    public int heuristic2() {
        int incompleteBottles = 0;
    
        for (Bottle bottle : state.getBottles()) {
            // A bottle is considered incomplete if it's neither empty nor filled with one uniform color
            if (!bottle.isEmpty() && !bottle.isUniformColor()) {
                incompleteBottles++;
            }
        }
    
        return incompleteBottles;
    }
    
}