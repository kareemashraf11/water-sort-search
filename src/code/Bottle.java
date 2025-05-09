package code;

import java.util.Stack;

public class Bottle {

    private int capacity;
    private Stack<Character> colors;

    public Bottle(int capacity, char[] colors) {
        this.colors = new Stack<>();
        this.capacity = capacity;
        for(int i = colors.length - 1; i >= 0; i--) {
            if(colors[i] != 'e')
                this.colors.push(colors[i]);
        }
    }

    public Bottle(Bottle parent) {
        colors = (Stack<Character>) parent.colors.clone();
        capacity = parent.capacity;
    }


    public void add(char c) {
        if(!isFull())
            colors.push(c);
    }

    public char getTop() {
        if(isEmpty()) return 'e';
        return colors.peek();
    }

    public char removeTop() {
        if(isEmpty()) return 'e';
        return colors.pop();
    }

    public boolean isFull() {
        return colors.size() == capacity;
    }

    public boolean isEmpty() {
        return colors.size() == 0;
    }

    @Override
    public String toString() {
        return "colors=" + colors;
    }

    public int getNumOfDifferentLayers() {
        Stack<Character> temp = new Stack<>();
        int result = 0;
        char last;
        if(!isEmpty()) {
            last = colors.pop();
            temp.push(last);
            result++;
        }
        else
            return 0;
        while(!isEmpty()) {
            char c = colors.pop();
            temp.add(c);
            if(c != last)
                result++;
            last = c;
        }
        while(!temp.isEmpty())
            colors.push(temp.pop());
        return result;
    }

    public boolean isUniform(){
        if(colors.isEmpty()) return true;
        char topColor = colors.peek();
        for(char color : colors){
            if(color != topColor){
                return false;
            }
        }
        return true;
    }

}



