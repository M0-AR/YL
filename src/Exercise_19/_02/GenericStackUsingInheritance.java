package Exercise_19._02;


import Exercise_19._01.GenericStack;

public class GenericStackUsingInheritance<E> extends java.util.ArrayList<E> {

    public boolean isEmpty(){
        return super.isEmpty();
    }

    public int getSize(){
        return size();
    }

    /** Return the element at the top of the stack */
    public Object peek(){
        return get( getSize()-1 );
    }

    /* Return and remove the element
     * on the top of the stack */
    public Object pop(){
        Object o = get( getSize() -1 );
        remove( getSize() -1 );
        return o;
    }

    /** Push and element on to the stack */
    public void push(E o){
        add( o );
    }

    public int search(Object o){
        return indexOf( o );
    }

    @Override // Override the string method in the ArrayList class
    public String toString() {
        return "stack: " + toString();
    }
}

class Main{
    public static void main(String[] args) {
        GenericStackUsingInheritance<String> stack = new GenericStackUsingInheritance<>();
        stack.push("Tom");
        stack.push("George");
        stack.push("Peter");
        System.out.println(stack.getSize());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}