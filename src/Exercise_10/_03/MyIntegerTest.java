package Exercise_10._03;
/*********************************************************************************
 * (The MyInteger class) Design a class named MyInteger. The class contains:      *
 *                                                                                *
 * ■ An int data field named value that stores the int value represented by this  *
 *   object.                                                                      *
 * ■ A constructor that creates a MyInteger object for the specified int value.   *
 * ■ A getter method that returns the int value.                                  *
 * ■ The methods isEven(), isOdd(), and isPrime() that return true if the         *
 *   value in this object is even, odd, or prime, respectively.                   *
 * ■ The static methods isEven(int), isOdd(int), and isPrime(int) that            *
 *   return true if the specified value is even, odd, or prime, respectively.     *
 * ■ The static methods isEven(MyInteger), isOdd(MyInteger), and                  *
 *   isPrime(MyInteger) that return true if the specified value is even, odd,     *
 *   or prime, respectively.                                                      *
 * ■ The methods equals(int) and equals(MyInteger) that return true if            *
 *   the value in this object is equal to the specified value.                    *
 * ■ A static method parseInt(char[]) that converts an array of numeric           *
 *   characters to an int value.                                                  *
 * ■ A static method parseInt(String) that converts a string into an int          *
 *   value.                                                                       *
 *                                                                                *
 * Draw the UML diagram for the class and then implement the class. Write a       *
 * client program that tests all methods in the class.                            *
 *********************************************************************************/
public class MyIntegerTest {
    public static void main(String[] args) {
        // Create a test array
        int[] values = {5, 6, 7, 8, 9};

        // Test isEvent(int), isOdd(int), and isPrime(int)
        // by invoking static method from MyInteger class
        System.out.println("\nTest if values are even using isEven(int):");
        for(int i = 0; i < values.length; i++) {
            System.out.println(values[i] + " " + MyInteger.isEvent( values[i] ));
        }

        System.out.println("\nTest if values are odd using isOdd(int):");
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] + " " + MyInteger.isOdd( values[i] ));
        }

        System.out.println("\nTest if values are prime using isPrime(int):");
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i] + " " + MyInteger.isPrime( values[i] ));
        }

        // Test MyInteger() object, isEvent(), isOdd(), isPrime() and getValue()
        System.out.println("\nTest if values are even using isEvent():");
        for (int index = 0; index < values.length; index++) {
            MyInteger myInteger = new MyInteger(values[index]);
            System.out.println(myInteger.getValue() + " " + myInteger.isEvent());
        }

        System.out.println("\nTest if values are odd using isOdd():");
        for (int index = 0; index < values.length; index++) {
            MyInteger myInteger = new MyInteger(values[index]);
            System.out.println(myInteger.getValue() + " " + myInteger.isOdd());
        }

        System.out.println("\nTest if values are prime using isPrime():");
        for (int index = 0; index < values.length; index++) {
            MyInteger myInteger = new MyInteger(values[index]);
            System.out.println(myInteger.getValue() + " " + myInteger.isPrime());
        }
    }
}
