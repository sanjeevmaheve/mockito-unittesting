package com.udemy.unittesting.unittesting.business;

import com.udemy.unittesting.unittesting.data.SomeDataService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/*
The disadvantage of stubbing the interface method is that we can't generalize it gracefully;
Various data scenarios needs either have different stubs or data needs to be generated
based on scenario. Maintaining so many stubs is not only difficult but erroneous e.g.
if we add new method in the interface, then all the stub implementation would break as
you need to implement all the interface contracts (i.e. methods).

Thats where mocking comes into picture. Instead of creating stubs like this, you can
programatically create class using Mockito framework. 80% of the JAVA projects are
using Mockito as the syntax and usage is very very simple.
 */

@ExtendWith(MockitoExtension.class)
class SomeBusinessMockTest {

    @InjectMocks
    SomeBusinessImpl business;
    //Don't need 'SomeBusinessImpl business = new SomeBusinessImpl();' after annotation is put.

    @Mock
    SomeDataService someDataServiceMock;
    //Don't need 'SomeDataService someSomeDataServiceMock = mock(SomeDataService.class);' after annotation is put

    @BeforeEach
    public void before() {
        business.setSomeDataService(someDataServiceMock);
    }

    @Test
    public void calculateSumUsingDataServiceBasic() {
        // Mocking/Stubbing the function (under test)
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{1, 2, 3});
        when(someDataServiceMock.storeDataTotal(anyInt())).thenReturn(true);
        assertEquals(6, business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataServiceEmpty() {
        // Mocking/Stubbing the function (under test)
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{});
        when(someDataServiceMock.storeDataTotal(anyInt())).thenReturn(true);
        business.setSomeDataService(someDataServiceMock);
        assertEquals(0, business.calculateSumUsingDataService());
    }

    @Test
    public void calculateSumUsingDataServiceOne() {
        // Mocking/Stubbing the function (under test)
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{1});
        when(someDataServiceMock.storeDataTotal(anyInt())).thenReturn(true);
        assertEquals(1, business.calculateSumUsingDataService());
        verify(someDataServiceMock, times(1)).retrieveAllData();
        verify(someDataServiceMock, times(1)).storeDataTotal(anyInt());
    }

    @Test
    public void calculateSumUsingDataServiceOneStoreFailed() {
        // Mocking/Stubbing the function (under test)
        when(someDataServiceMock.retrieveAllData()).thenReturn(new int[]{1});
        when(someDataServiceMock.storeDataTotal(anyInt())).thenReturn(false);
        assertEquals(0, business.calculateSumUsingDataService());
        verify(someDataServiceMock, times(1)).retrieveAllData();
        verify(someDataServiceMock, times(1)).storeDataTotal(anyInt());
    }
}
