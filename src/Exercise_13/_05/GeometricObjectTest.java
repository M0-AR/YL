package Exercise_13._05;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeometricObjectTest {
    Circle c1;
    Circle c2;
    Rectangle r1;
    Rectangle r2;

    @Before
    public void setUp() throws Exception {
        c1 = new Circle(20); // Max
        c2 = new Circle(10);
        r1 = new Rectangle(2, 3);
        r2 = new Rectangle(2, 4); // Max
    }

    @Test
    public void testFindMaxOfTwoCircles() {
        Circle c3 = (Circle) GeometricObject.max(this.c1, this.c2);
        assertEquals(this.c1, c3);
    }

    @Test
    public void testFindMaxOfTwoRectangles() {
        Rectangle r3 = (Rectangle) GeometricObject.max(this.r1, this.r2);
        assertEquals(this.r2, r3);
    }
}