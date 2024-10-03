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
                    child.pour(i, j);
                    Node childNode = new Node(child, this, generateOperator(i, j), depth + 1, pathCost + 1);
                    children.add(childNode);
                }
            }
        }
        return children;
    }

    public String generateOperator(int i, int j) {
        return "pour_" + i + "_" + j;
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