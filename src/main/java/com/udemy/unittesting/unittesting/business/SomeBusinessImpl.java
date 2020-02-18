
package com.udemy.unittesting.unittesting.business;

import com.udemy.unittesting.unittesting.data.SomeDataService;

public class SomeBusinessImpl {

    private SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }

    /*
    The following function has input values as array as formal arguments
     */
    public int calculteSum(int[] data) {

        int sum = 0;
        for (int value : data) {
            sum += value;
        }

        return sum;
    }

    /*
    In real world the data to the function might be coming from the sort
    of a service instead. Let's see how?
     */
    public int calculateSumUsingDataService() {

        int sum = 0;
        int[] data = someDataService.retrieveAllData();
        for (int value : data) {
            sum += value;
        }

        if(someDataService.storeDataTotal(sum) == false) {
            sum = 0;
            System.out.println("Unable to store data into backend");
        }
        return sum;
    }
}
