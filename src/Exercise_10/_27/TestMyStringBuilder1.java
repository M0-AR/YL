package Exercise_10._27;

public class TestMyStringBuilder1 {
    public static void main(String[] args) {
        MyStringBuilder1 str1 = new MyStringBuilder1("Test");

        System.out.println("\nAppend the string \" MyStringBuilder1\" to string: " +
                str1.append(new MyStringBuilder1(" MyStringBuilder1")));

        // Append 101 to str1
        System.out.println("\nAppend integer 101 to the string: " + str1.append(101));

        // Display length of str1
        System.out.println("\nLength of string: " + str1.length());

        // Display character at index 2
        System.out.println("\nCharacter at index 2: " + str1.charAt(2));

        // Display str1 as lowercase
        System.out.println("\nString to lower case: " + str1.toLowerCase());

        // Display the substring of str1 from index 1 to 3
        System.out.println("\nSubstring of string from index 1 to 3: " +
                str1.substring(1, 3));

        // Return the string
        System.out.println("\nDisplay string: " + str1.toString());
    }
}
