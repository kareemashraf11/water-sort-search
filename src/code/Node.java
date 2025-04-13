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
                if(i != j && state.canApplyOperator(i, j)) {
                    State child = new State(state);
                    int times = child.applyOperator(i, j);
                    Node childNode = new Node(child, this, state.generateOperator(i, j), depth + 1, pathCost + times);
                    children.add(childNode);
                }
            }
        }
        return children;
    }

    public List<String> getPossibleOperations() {
        List<String> possibleOperations = new ArrayList<>();
        for (int i = 0; i < state.getBottles().size(); i++) {
            for (int j = 0; j < state.getBottles().size(); j++) {
                if(i != j && state.canApplyOperator(i, j)) {
                    possibleOperations.add(state.generateOperator(i, j));
                }
            }
        }
        return possibleOperations;
    }

    public State getState() {
        return state;
    }

    public int getPathCost() {
        return pathCost;
    }

    public String getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    public String generatePlan(boolean visualize) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        generatePlanHelper(sb1, sb2);

        if (visualize)
            System.out.println(sb2);
        return sb1.toString();
    }

    private void generatePlanHelper(StringBuilder sb1, StringBuilder sb2) {
        if (parent != null) {
            parent.generatePlanHelper(sb1, sb2);
        }

        if (operator != null && state != null) {
            if (sb1.length() > 0)
                sb1.append(",");
            sb1.append(operator);

            if (sb2.length() > 0)
                sb2.append(" -> \n");
            sb2.append(operator).append(" (").append(state).append(")");
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

}