package Exercise_19._01;
/*********************************************************************************
 * (Revising Listing 19.1) Revise the GenericStack class in Listing 19.1 to       *
 * implement it using an array rather than an ArrayList. You should check the     *
 * array size before adding a new element to the stack. If the array is full,     *
 * create a new array that doubles the current array size and copy the elements   *
 * from the current array to the new array.                                       *
 *********************************************************************************/
/**
 1- Revise the GenericStack
 2- Use Array rather than an ArrayList
 3- If Array is full creat a new array that doubles the Array size
    and copy the elements from the Array to the new array
 */
public class RevisingListing {
    public static void main(String[] args) {
        // Create a stack to hold strings
        // and add three strings to the stack
        GenericStack<String> stack1 = new GenericStack<>();
        stack1.push("London");
        stack1.push("Paris");
        stack1.push("Berlin");

        // Create a stack that holds integers
        // and add 100 integers to the stack
        GenericStack<Integer> stack2 = new GenericStack<>();
        for (int i = 0; i < 100; i++) {
            stack2.push(i + 1); // autoboxing 1 to new Integer(1)
        }

        // Display the elements in stack 1
        System.out.print("Stack 1: ");
        while (!stack1.isEmpty()) {
            System.out.print(stack1.pop() + " ");
        }
        System.out.println("\n");

        // Display the elements in stack 2
        System.out.println("Stack 2: ");
        for (int i = 1; !stack2.isEmpty(); i++) {
            if (i % 10 == 0)
                System.out.println(stack2.pop());
            else
                System.out.print(stack2.pop() + " ");
        }
        System.out.println();

        System.exit( 1 );
        GenericStack g = new GenericStack();
        g.push( 1 );
        g.push( 2 );
        g.push( 3 );
        g.push( 4 );
        g.push( 5 );
        g.push( 6 );
        g.push( 7 );
        g.push( 8 );
        g.push( 9 );
        g.push( 10 );

        System.out.println(g.getSize());
        System.out.println(g.pop());
        g.push( 10 );
        g.push( 11 );
        System.out.println(g.getSize());
        System.out.println(g.peek());
    }
}
