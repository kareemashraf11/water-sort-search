package code;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String init = "5;4;" + "b,y,r,b;" + "b,y,r,r;" +
                "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;";
        String grid0 = "3;" +
                "4;" +
                "r,y,r,y;" +
                "y,r,y,r;" +
                "e,e,e,e;";
        // r y e    y e e   y e e  e y e   e y e   y e e
        // y r e    y r e   y e e  e y e   e y r   y e r
        // r y e    r y e   r y r  r y r   e y r   y e r
        // y r e    y r r   y r r  y r r   y r r   y r r
        String grid1 = "5;" +
                "4;" +
                "b,y,r,b;" +
                "b,y,r,r;" +
                "y,r,b,y;" +
                "e,e,e,e;" +
                "e,e,e,e;";
        String grid2 = "5;" +
                "4;" +
                "b,r,o,b;" +
                "b,r,o,o;" +
                "r,o,b,r;" +
                "e,e,e,e;" +
                "e,e,e,e;";
        String grid3 = "6;" +
                "4;" +
                "g,g,g,r;" +
                "g,y,r,o;" +
                "o,r,o,y;" +
                "y,o,y,b;" +
                "r,b,b,b;" +
                "e,e,e,e;";
        String grid4 = "6;" +
                "3;" +
                "r,r,y;" +
                "b,y,r;" +
                "y,b,g;" +
                "g,g,b;" +
                "e,e,e;" +
                "e,e,e;";
      //  State state = new State(init);
	  //  Node root = new Node(state, null, null, 0 ,0);
        System.out.println(WaterSortSearch.solve(grid4, "ID", false));
        System.out.println("-----------");
        System.out.println(WaterSortSearch.solve(grid4, "DF", false));

        //  List<Node> list = root.expand();

       // System.out.println(list);
    }
}
