package com.udemy.unittesting.unittesting.business;

import com.udemy.unittesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
The disadvantage of stubbing the interface is that we can't generalize it gracefully;
Various data scenarios needs either have different stubs or data needs to be generated
based on scenario. Maintaining so many stubs is not only difficult but erroneous e.g.
if we add new method in the interface, then all the stub implementation would break as
you need to implement all the interface contracts (i.e. methods).

Thats where mocking comes into picture. Instead of creating stubs like this, you can
programatically create class using Mockito framework.
 */
class SomeDataServiceStubThreeInputs implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {1,2,3};
    }
}

class SomeBusinessStubTest {

    @Test
    public void calculateSumUsingDataServiceBasic() {

        SomeBusinessImpl business = new SomeBusinessImpl();
        business.setSomeDataService(new SomeDataServiceStubThreeInputs());
        int actualResult = business.calculateSumUsingDataService();

        assertEquals(6, actualResult);
    }
}
