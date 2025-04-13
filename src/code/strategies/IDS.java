package code.strategies;

import code.Node;

public class IDS extends DFS{

    public IDS(Node root) {
        super(root);
    }

    public String ids(boolean visualize) {
        int i = 0;
        while (true) {
            String sol = new DFS(root).dfs(i, true, visualize);
            if (!sol.equals("NOSOLUTION"))
                return sol;
            i++;
        }
    }
}
