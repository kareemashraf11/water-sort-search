package code;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class State {
    private List<Bottle> bottles;

    public State(String init) {
        bottles = new ArrayList<>();
        String[] split = init.split(";");
        int capacity = Integer.parseInt(split[1]);
        for(int i = 2; i < split.length; i++) {
            String[] colors = split[i].split(",");
            char[] arr = new char[capacity];
            for(int j = 0; j < arr.length; j++)
                arr[j] = colors[j].charAt(0);
            Bottle b = new Bottle(capacity, arr);
            bottles.add(b);
        }
    }

    public State(State parent) {
        bottles = new ArrayList<>();
        for (Bottle b : parent.getBottles())
            bottles.add(new Bottle(b));
    }

    public List<Bottle> getBottles() {
        return bottles;
    }

    public boolean canPour(int i, int j) {
        Bottle first = bottles.get(i), second = bottles.get(j);
        if(first.isEmpty() || second.isFull()) return false;

        if(second.isEmpty() || first.getTop() == second.getTop()) return true;

        return false;
    }

    public int pour(int i, int j) {
        int count = 0;
        while(true) {
            if (canPour(i, j)) {
                bottles.get(j).add(bottles.get(i).removeTop());
                count++;
            }
            else
                break;
        }
        return count;
    }

    public boolean isGoalState() {
        boolean goal = true;
        for(Bottle b : bottles) {
            HashSet<Character> hs = new HashSet<>();
            Stack<Character> temp = new Stack<>();
            while (!b.isEmpty()) {
                char current = b.removeTop();
                temp.push(current);
                hs.add(current);

                if(hs.size() > 1) {
                    goal = false;
                    break;
                }
            }

            while(!temp.isEmpty())
                b.add(temp.pop());

            if(!goal) return false;
        }

        return goal;
    }

    @Override
    public String toString() {
        return "bottles=" + bottles;
    }
}