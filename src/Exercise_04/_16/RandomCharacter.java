package Exercise_04._16;
/*
(Random character) Write a program that displays a random uppercase letter
using the Math.random() method.
*/
public class RandomCharacter {
    public static void main(String[] args) {

        char ch = (char) (Math.random() * 26 + 'A');

        System.out.println(ch);
    }
}
