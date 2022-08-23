package Exercise_11._15;

import org.junit.Assert;
import org.junit.Test;

public class AreaOfAConvexPolygonTest {
    double[][] points = {
        {-12, 0},   {-8.5, 10}, {0, 11.4},
        {5.5, 7.8}, {6, -5.5},  {0, -7},
        {-3.5, -13.5}, {-12, 0}
            // Last point is same as first point
    };


    @Test
    public void testAreaOfConvexPolygon() {
        Assert.assertEquals(292.575, AreaOfAConvexPolygon.areaOfConvexPolygon(points), 0.0);
    }

}