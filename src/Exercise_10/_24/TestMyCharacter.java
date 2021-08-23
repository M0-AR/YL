package Exercise_10._24;
/*************************************************************************************
 * 10.24 (Implement the Character class) The Character class is provided in the Java *
 * library. Provide your own implementation for this class. Name the new class       *
 * MyCharacter.                                                                      *
 ************************************************************************************/
public class TestMyCharacter {
    public static void main(String[] args) {
        MyCharacter character = new MyCharacter('A');
        System.out.println(character.isDigit());
        System.out.println(MyCharacter.isDigit('t'));
    }
}
