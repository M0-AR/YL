package Exercise_11._18;
/*
(ArrayList of Character) Write a method that returns an array
list of Character from a string using the following header:
    public static ArrayList<Character> toCharacterArray(String s)
For example, toCharacterArray("abc") returns an array list that contains
characters 'a', 'b', and 'c'.
 */
import java.util.ArrayList;

public class ArrayListOfCharacter {
    public static void main(String[] args) {
        for (Character ch: toCharacterArray("Welcome")) {
            System.out.println(ch);
        }
    }
    public static ArrayList<Character> toCharacterArray(String s) {
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            characters.add(s.charAt(i));
        }
        return characters;
    }
}
