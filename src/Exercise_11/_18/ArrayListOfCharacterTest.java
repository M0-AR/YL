package Exercise_11._18;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListOfCharacterTest {

    String s;
    ArrayList<Character> characterList;

    @Before
    public void setUp() throws Exception {
        s = "abc";
        characterList = new ArrayList<>(Arrays.asList('a', 'b', 'c'));
    }


    @Test
    public void testToCharacterArray() {
        ArrayList<Character> characters = ArrayListOfCharacter.toCharacterArray(s);

        Assert.assertArrayEquals(this.characterList.toArray(), characters.toArray());
    }

}