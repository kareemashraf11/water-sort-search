package code;

import java.util.*;

public class WaterSortSearch extends GenericSearch{

    public static String solve(String initialState, String strategy, boolean visualize) {
        Node root = new Node(new State(initialState), null, null, 0, 0);

        switch (strategy) {
            case "BF": return bfs(root);
            case "DF": return dfs(root, Integer.MAX_VALUE);
            case "ID": return id(root);
            case "UC": return ucs(root);
            case "AS1": return aStar(root);
            case "AS2": return aStar2(root);

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

    
    public static String ucs(Node root) {
    PriorityQueue<Node> minCost = new PriorityQueue<>((node1,node2) -> node1.getPathCost() - node2.getPathCost()); 
    Set<String> visited = new HashSet<>();
    minCost.add(root);
    int expanded = 0;
    while(!minCost.isEmpty()) {
    	Node front = minCost.poll();
    	expanded ++;
    	if (front.getState().isGoalState())
            return generateAnswer(front, expanded);
        else {
            List<Node> children = front.expand();
            for(Node child : children) {
                if(!visited.contains(child.getState().toString())) {
                    minCost.add(child);
                }
            }
        }
    	
    }

    	
    	return "NOSOLUTION";
    	}

    public static String aStar(Node root){
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(WaterSortSearch::f));
        Set<String> closedSet = new HashSet<>();

        openSet.add(root);
        int expanded = 0;

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            expanded++;

            if (current.getState().isGoalState()) {
                return generateAnswer(current, expanded);
            }

            closedSet.add(current.getState().toString());

            // Expand the current node and add children to the open set
            List<Node> children = current.expand();
            for (Node child : children) {
                if (!closedSet.contains(child.getState().toString())) {
                    openSet.add(child);
                }
            }
        }

        return "NOSOLUTION";
    }
        // f(n) = g(n) + h(n) where g(n) is pathCost and h(n) is the heuristic
        public static int f(Node node) {
            return node.getPathCost() + node.heuristic();
        }

        public static String aStar2(Node root) {
            // Priority Queue ordered by f(n) = g(n) + h(n)
            PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(WaterSortSearch::f2));
            Set<String> closedSet = new HashSet<>();
    
            openSet.add(root);
            int expanded = 0;
    
            while (!openSet.isEmpty()) {
                Node current = openSet.poll();
                expanded++;
    
                if (current.getState().isGoalState()) {
                    return generateAnswer(current, expanded);
                }
    
                closedSet.add(current.getState().toString());
    
                // Expand the current node and add children to the open set
                List<Node> children = current.expand();
                for (Node child : children) {
                    if (!closedSet.contains(child.getState().toString())) {
                        openSet.add(child);
                    }
                }
            }
    
            return "NOSOLUTION";
        }
            // f(n) = g(n) + h(n) where g(n) is pathCost and h(n) is the heuristic
    public static int f2(Node node) {
        return node.getPathCost() + node.heuristic2();
    }
}



