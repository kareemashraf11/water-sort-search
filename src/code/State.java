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

    public boolean canApplyOperator(int i, int j) {
        Bottle first = bottles.get(i), second = bottles.get(j);
        if(first.isEmpty() || second.isFull()) return false;

        if(second.isEmpty() || first.getTop() == second.getTop()) return true;

        return false;
    }

    public int applyOperator(int i, int j) {
        int count = 0;
        while(true) {
            if (canApplyOperator(i, j)) {
                bottles.get(j).add(bottles.get(i).removeTop());
                count++;
            }
            else
                break;
        }
        return count;
    }

    public String generateOperator(int i, int j) {
        return "pour_" + i + "_" + j;
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


    public String sort() {
        List<Bottle> sorted = new ArrayList<>(bottles);
        sorted.sort((bottle1, bottle2) -> bottle1.toString().compareTo(bottle2.toString()));

        StringBuilder sb = new StringBuilder("Bottles:\n");
        for(Bottle b : sorted)
            sb.append(b.toString()).append("\n");
        return sb.toString();
    }

    public int heuristic1(){
        int n =0;
        for(int i =0; i<getBottles().size(); i++){
            if(!getBottles().get(i).isUniform()){
                n++;
            }
        }
        return n;
    }

    public int heuristic2() {
        int heuristicValue = 0;
        for (Bottle b : getBottles()) {
            if (b.isEmpty()) continue;

            heuristicValue+= b.getNumOfDifferentLayers();
        }

        return heuristicValue;
    }

    @Override
    public String toString() {
        return "Bottles: " + bottles;
    }
}

