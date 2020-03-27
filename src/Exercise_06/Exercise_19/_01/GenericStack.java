package Exercise_06.Exercise_19._01;

public class GenericStack<E> {
    private E[] list;
    private int size = -1;

    // Creates an empty stack
    public GenericStack() {
        this.list = (E[]) new Object[10];;
    }

    // Returns the number of elements in this stack
    public int getSize(){
        return size + 1;
    }

    // Returns the top element in this stack
    public E peek(){
        return list[size];
    }

    // Returns and removes the top element int this stack
    public E pop(){
        return list[size--];
    }

    // Adds a new element to the top of this stack
    public void push(E o){
        if (size == list.length-1){
            doublesArraySize();
        }

        list[++size] = o;
    }

    // Returns true if the stack is empty
    public boolean isEmpty(){
        return (this.size == -1);
    }

    public void doublesArraySize(){
        E[] temp = (E[]) list;
        list = (E[]) new Object[size*2+1];

        for (int i = 0; i <temp.length; i++) {
            list[i] = temp[i];
        }

    }

}
