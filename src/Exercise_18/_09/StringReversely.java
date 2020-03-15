package Exercise_18._09;

public class StringReversely {
    static int count = 1;
    public static void main(String[] args) { // github
        reverseDisplay("abcd");
    }

    public static void reverseDisplay(String value){
        if (value.length() >= count){
            System.out.print(value.charAt( value.length()-count++ ));
            reverseDisplay( value );
        }
    }

}
