package Exercise_13._06;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComparableCircleTest {
    private ComparableCircle o1;
    private ComparableCircle o2;

    @Before
    public void setUp() throws Exception {
        o1 = new ComparableCircle(15);
        o2 = new ComparableCircle(5);
    }

    @Test
    public void testMaxOfTwoComparableCircleObjects() {
        ComparableCircle o3 = Max.max(this.o1, this.o2);

        assertEquals(this.o1, o3);

        System.out.println("The max circle's radius is " + o3.getRadius());
    }
}