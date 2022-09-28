package Exercise_13._07;

import Exercise_13._05.GeometricObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SquareTest {
    GeometricObject[] geometricObjects;
    double[] expectedArea;

    @Before
    public void setUp() throws Exception {
        geometricObjects = new GeometricObject[]{
                new Square(2), new Square(3),
                new Square(4), new Square(5),
                new Square(6)
        };
        expectedArea = new double[] {4, 9, 16, 25, 36};
    }

    @Test
    public void testHowToColorFunction() {
        for (int i = 0; i < geometricObjects.length; i++) {
            assertEquals("Color all four sides.",
                    ((Square)geometricObjects[i]).howToColor());
        }
    }

    @Test
    public void testArea() {
        for (int i = 0; i < expectedArea.length; i++) {
            assertEquals(expectedArea[i], geometricObjects[i].getArea(), 0);
        }
    }
}