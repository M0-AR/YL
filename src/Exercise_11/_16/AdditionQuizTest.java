package Exercise_11._16;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdditionQuizTest {
    @Test
    public void testAdd() {
        // Given
        int num1 = 5, num2 = 9;

        // When
        int sum = AdditionQuiz.add(num1, num2);

        // Then
        Assert.assertEquals(14, sum);
    }
}