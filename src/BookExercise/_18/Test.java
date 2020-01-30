package BookExercise._18;

public class Test {
    public static void main(String[] args) {
//        m(5);
//        xMethod( 1234567 );
//        Test test = new Test();// Infinite recursion due to new Test() inside the constructor Test().

        System.out.println("Is moon a palindrome? " + isPalindrome("moon"));
        System.out.println("Is noon a palindrome? " + isPalindrome("nabn"));
        System.out.println("Is a a palindrome? " + isPalindrome("a"));
        System.out.println("Is aba a palindrome? " + isPalindrome("aba"));
        System.out.println("Is ab a palindrome? " + isPalindrome("ab"));
    }

    public static void m(int n) {
        if (n > 0) {
            m( n - 1 );
            System.out.print( n + " " );
        }
    }


    public static void xMethod(int n) {
        if (n > 0) {
            System.out.print( n % 10 );
            xMethod( n / 10 );
        }
    }

    public Test() {
        Test test = new Test();
    }

    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) // BAse case
            return true;
        else if (s.charAt( 0 ) != s.charAt( s.length() - 1 ))  // Base case
            return false;
        else
            return isPalindrome( s.substring( 1, s.length() -1  ) );

    }

}



