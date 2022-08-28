package Exercise_11._17;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PerfectSquareTest {
    int m;
    ArrayList<Integer> factorListOfM;
    HashMap<Integer, Integer> frequenciesOfNumberHashMap;
    ArrayList<Integer> numberAppearOddOfTimesList;
    int n;

    @Before
    public void setUp() throws Exception {
        m = 1500;
        factorListOfM = new ArrayList<>(Arrays.asList(2, 2, 3, 5, 5, 5));
        frequenciesOfNumberHashMap = new HashMap<Integer, Integer>(){{
            put(2, 2);
            put(3, 1); // Appear odd of times
            put(5, 3); // Appear odd of times
        }};
        numberAppearOddOfTimesList = new ArrayList<>(Arrays.asList(3, 5));
        n = 15;
    }

    @Test
    public void testGettingFactorsOfNumber() {
        ArrayList<Integer> factorList = PerfectSquare.getFactors(m);

        Assert.assertArrayEquals(this.factorListOfM.toArray(), factorList.toArray());
    }

    @Test
    public void testNumberAppearOddOfTimes() {
        ArrayList<Integer> numberAppearOddOfTimesList = PerfectSquare.getNumbersAppearOddOfTimes(factorListOfM);

        Assert.assertArrayEquals(this.numberAppearOddOfTimesList.toArray(), numberAppearOddOfTimesList.toArray());
    }

    @Test
    public void testCountFrequenciesOfNumbers() {
        HashMap<Integer, Integer> frequenciesOfNumberHashMap = PerfectSquare.countFrequenciesOfNumber(factorListOfM);

        Assert.assertTrue(this.frequenciesOfNumberHashMap.equals(frequenciesOfNumberHashMap));
    }

    @Test
    public void testMultiplyNumbersInList() {
        int n = PerfectSquare.multiplyNumbersInList(this.numberAppearOddOfTimesList);

        Assert.assertEquals(this.n, n);
    }
}