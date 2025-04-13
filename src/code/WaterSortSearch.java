package code;

import code.strategies.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class WaterSortSearch extends GenericSearch{
    protected Node root;

    public WaterSortSearch(Node root) {
        this.root = root;
    }

    public State getInitialState() {
        return root.getState();
    }

    public static String solve(String initialState, String strategy, boolean visualize) {
        Node root = new Node(new State(initialState), null, null, 0, 0);
        System.gc();

        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        long cput1 = bean.getCurrentThreadCpuTime();
        long t1 = System.nanoTime();
        long m1 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        String sol;
        switch (strategy) {
            case "BF": sol =  new BFS(root).bfs(visualize); break;
            case "DF": sol =  new DFS(root).dfs(0, false, visualize); break;
            case "ID": sol =  new IDS(root).ids(visualize); break;
            case "UC": sol =  new UCS(root).ucs(visualize); break;
            case "AS1": sol =  new AStar(root).astar(1, visualize); break;
            case "AS2": sol =  new AStar(root).astar(2, visualize); break;
            case "GR1": sol =  new Greedy(root).greedy(1, visualize); break;
            case "GR2": sol =  new Greedy(root).greedy(2, visualize); break;

            default: sol = "Invalid Search Strategy";
        }

        long t2 = System.nanoTime();
        long cput2 = bean.getCurrentThreadCpuTime();
        double t = (t2 - t1) / 1000000000.0;
        double cput = (cput2 - cput1) / 1000000000.0;
        double utilization = (cput / (t * Runtime.getRuntime().availableProcessors())) * 100;

        long m2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("CPU Utilization is: " + utilization + "%");
        System.out.println("Memory used is: " + (m2 - m1)  + " bytes");

        return sol;
    }
}



