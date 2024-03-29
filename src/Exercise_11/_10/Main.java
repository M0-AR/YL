package Exercise_11._10;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        MyStack stack = new MyStack();

        for (int i = 0; i < 5; i++)
            stack.push(input.nextLine());

        System.out.println(stack.getSize() + " strings are: ");
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }
}

class MyStack extends java.util.ArrayList<Object> {
    public boolean isEmpty() {
        return super.isEmpty();
    }

    public int getSize() {
        return size();
    }

    public Object peek() {
        return get(getSize() - 1);
    }

    public Object pop() {
        return remove(getSize() - 1);
    }

    public Object push(Object o) {
        add(o);
        return o;
    }

    public int search(Object o) {
        return indexOf(o);
    }

    public String toString() {
        return "Stack: " + toString();
    }
}
