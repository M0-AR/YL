package Exercise_11._15;

import Exercise_10._04.MyPoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class AreaOfAConvexPolygonTest {

    ArrayList<MyPoint> points;

    @Before
    public void setUp() throws Exception {
        points = new ArrayList<>();
        points.add(new MyPoint(-12,  0));
        points.add(new MyPoint(-8.5,  10));
        points.add(new MyPoint( 0,    11.4));
        points.add(new MyPoint( 5.5,  7.8));
        points.add(new MyPoint( 6,   -5.5));
        points.add(new MyPoint( 0,   -7));
        points.add(new MyPoint(-3.5, -13.5));
        // Last point is the same as first point
        points.add(new MyPoint(-12,   0));
    }

    @Test
    public void testAreaOfConvexPolygon() {
        Assert.assertEquals(292.575, AreaOfAConvexPolygon.areaOfConvexPolygon(points), 0.0);
    }

}