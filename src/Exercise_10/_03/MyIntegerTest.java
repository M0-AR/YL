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

        // Test isEven(MyInteger), isOdd(MyInteger), isPrime(MyInteger)
        System.out.println("\nTest if values are even using isEven(MyInteger):");
        for (int index = 0; index < values.length; index++) {
            MyInteger myInteger = new MyInteger(values[index]);
            System.out.println(myInteger.getValue() + " " + MyInteger.isEvent( myInteger ));
        }

        System.out.println("\nTest if values are odd using isOdd(MyInteger):");
        for (int index = 0; index < values.length; index++) {
            MyInteger myInteger = new MyInteger(values[index]);
            System.out.println(myInteger.getValue() + " " + MyInteger.isOdd( myInteger ));
        }

        System.out.println("\nTest if values are prime using isPrime(MyInteger):");
        for (int index = 0; index < values.length; index++) {
            MyInteger myInteger = new MyInteger(values[index]);
            System.out.println(myInteger.getValue() + " " + MyInteger.isPrime( myInteger));
        }

        // Test equals(int) and equals(MyInteger)
        int[] values2 = {5, 9, 7};
        MyInteger myInteger = new MyInteger( 9 );
        System.out.println("\nTest if " + myInteger.getValue() +
                            " is equal to the specified value:");
        for (int index = 0; index < values2.length; index++) {
            System.out.println(values2[index] + " " + myInteger.equals( values2[index] ));
        }

        System.out.println("\nTest if " + myInteger.getValue() +
                                " is equal to the specified value");
        for (int index = 0; index < values2.length; index++) {
            MyInteger myInteger1 = new MyInteger( values2[index] );
            System.out.println(values2[index] + " " + myInteger.equals( myInteger1 ));
        }

        // Test parseInt(char[]) and parseInt(String)
        System.out.println("\nTest parseInt(char[]) and parseInt(String):");
        char[] numericCharacters = {'3', '4', '2'};
        String numericString = "658";
        System.out.print("\'");
        for (int index = 0; index < numericCharacters.length; index++) {
            System.out.print(numericCharacters[index]);
        }
        System.out.println("\' + \"" + numericString + "\" = " +
                               (MyInteger.parseInt( numericCharacters ) +
                                MyInteger.parseInt( numericString )));
    }
}
