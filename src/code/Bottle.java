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
<<<<<<< HEAD
}
=======
}
>>>>>>> b7f0e37a898238fa49e688ec1a0fe506f780278c
