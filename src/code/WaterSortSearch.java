package code;

import java.util.*;

public class WaterSortSearch extends GenericSearch{

    public static String solve(String initialState, String strategy, boolean visualize) {
        Node root = new Node(new State(initialState), null, null, 0, 0);

        switch (strategy) {
            case "BF": return bfs(root);
            case "DF": return dfs(root, Integer.MAX_VALUE);
            case "ID": return id(root);
            default: return "";
        }
    }

    public static String bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(root);
        int expanded = 0;
        while(!queue.isEmpty()) {
            Node front = queue.poll();
            expanded++;
            if (front.getState().isGoalState())
                return generateAnswer(front, expanded);
            else {
                List<Node> children = front.expand();
                for(Node child : children) {
                    if(!visited.contains(child.getState().toString())) {
                        queue.add(child);
                        visited.add(child.getState().toString());
                    }
                }
            }
        }

        return "NOSOLUTION";
    }

    public static String generateAnswer(Node goal, int expanded) {
        return goal.generatePlan() + ";" + goal.getPathCost()  + ";" + expanded;
    }

    public static String dfs(Node root, int maxDepth) {
        Stack<Node> stack = new Stack<>();
        Set<String> visited = new HashSet<>();
        stack.add(root);
        int expanded = 0;
        while(!stack.isEmpty()) {
            Node front = stack.pop();
            if(front.getDepth() > maxDepth) break;
            expanded++;
            if (front.getState().isGoalState())
                return generateAnswer(front, expanded);
            else {
                List<Node> children = front.expand();
                for(Node child : children) {
                    if(!visited.contains(child.getState().toString())) {
                        stack.push(child);
                        visited.add(child.getState().toString());
                    }
                }
            }
        }

        return "NOSOLUTION";
    }


    public static String id(Node root) {
        int i = 0;
        while(true) {
            String sol = dfs(root, i);
            if(!sol.equals("NOSOLUTION"))
                return sol;
            i++;
        }
    }
}
