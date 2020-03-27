package Exercise_06.Exercise_19._01;
/**
 1- Revise the GenericStack
 2- Use Array rather than an ArrayList
 3- If Array is full creat a new array that doubles the Array size
    and copy the elements from the Array to the new array
 */
public class RevisingListing {
    public static void main(String[] args) {
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
