package Exercise_13._03;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SortArrayListTest {
    ArrayList<Number> numbers;
    @Before
    public void setUp() throws Exception {
        numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(5);
        numbers.add(14);
        numbers.add(24);
        numbers.add(42);
    }

    @Test
    public void testSort() {
        ArrayList<Number> numbers = new ArrayList<>();
        numbers.add(14);
        numbers.add(24);
        numbers.add(4);
        numbers.add(42);
        numbers.add(5);
        SortArrayList.sort(numbers);
        assertArrayEquals(this.numbers.toArray(), numbers.toArray());
    }
}