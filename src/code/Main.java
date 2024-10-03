package code;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        State state = new State("5;4;" + "b,y,r,b;" + "b,y,r,r;" +
                "y,r,b,y;" + "e,e,e,e;" + "e,e,e,e;");
	    Node root = new Node(state, null, null, 0 ,0);

        List<Node> list = root.expand();

        System.out.println(list);
    }
}
