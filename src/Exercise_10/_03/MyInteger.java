package Exercise_10._03;

//////////////////////
// TODO:
// 1- check for isPrime method
// 2- UML diagram
/////////////////////
public class MyInteger {
    private int value;

    public MyInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private boolean isEvent() {
        return this.value % 2 == 0;
    }

    private boolean isOdd() {
        return !isEvent();
    }

    private boolean isPrime() {
        //return this.value
        // todo
        return false;
    }

    public static boolean isEvent(int value) {
        return value % 2 == 0;
    }

    public static boolean isOdd(int value) {
        return !isEvent(value);
    }

    public static boolean isPrime(int value) {
        //return this.value
        // todo
        return false;
    }


    public static boolean isEvent(MyInteger myInteger) {
        return myInteger.isEvent();
    }

    public static boolean isOdd(MyInteger myInteger) {
        return myInteger.isOdd();
    }

    public static boolean isPrime(MyInteger myInteger) {
        //return this.value
        // todo
        return myInteger.isPrime();
    }

    private boolean equals(int value) {
        return this.value == value;
    }

    private boolean equals(MyInteger myInteger) {
        return this.value == myInteger.getValue();
    }

    public static int parseInt(char[] chars) {
        int sum = 0;
        for (char aChar : chars) {
            sum += aChar;
        }
        return sum;
    }

    public static int parseInt(String string) {
        int sum = 0;
        for (int i = 0; i < string.length(); i++) {
            sum += string.charAt( i );
        }
        return sum;
    }
}
