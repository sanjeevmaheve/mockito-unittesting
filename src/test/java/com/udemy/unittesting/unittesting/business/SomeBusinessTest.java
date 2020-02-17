package com.udemy.unittesting.unittesting.business;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SomeBusinessTest {

    @Test
    public void calculateSumBasic() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculteSum(new int[] {1,2,3});

        assertEquals(6, actualResult);
    }

    @Test
    public void calculatSumEmpty() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculteSum(new int[] {});

        assertEquals(0, actualResult);
    }

    @Test
    public void calculateSumOne() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        int actualResult = business.calculteSum(new int[] {1});

        assertEquals(1, actualResult);
    }
}
