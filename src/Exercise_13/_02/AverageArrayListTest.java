package Exercise_13._02;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AverageArrayListTest {
    ArrayList<Integer> numbers;
    int average;

    @Before
    public void setUp() throws Exception {
        numbers = new ArrayList<>(Arrays.asList(1, 2, 3));
        average = (1 + 2 + 3) / 3;
    }


    @Test
    public void testAverage() {
       int average = AverageArrayList.average(numbers);
       assertEquals(this.average, average);
    }
}