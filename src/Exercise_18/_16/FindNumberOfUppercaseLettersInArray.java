package Exercise_18._16;

public class FindNumberOfUppercaseLettersInArray {// github and y
    public static void main(String[] args) {
        char[] m = {'a', 'A', 'b', 'B'};
        System.out.println(count( m ));
    }

    public static int count(char[] chars){
        return count(chars, 0);
    }

    public static int count(char[] chars, int high){
        // Counting the number of uppercase letters
        int number = 0;
        if (high < chars.length){
            number = count( chars, ++high )+((Character.isUpperCase( chars[high-1] ))?1:0);
        }
        return number;
    }
}
