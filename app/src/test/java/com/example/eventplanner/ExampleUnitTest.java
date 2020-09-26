package com.example.eventplanner;


import com.example.eventplanner.Database.DBHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    BudgetDetails budgetDetails;
    Home home;
    DBHelper d;


    @Before
    public void setUp(){


        budgetDetails = new BudgetDetails();
        home = new Home(); 
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test_calculateOverDue(){

        int result = budgetDetails.calculateOverDue(20,10);
        assertEquals(10,result);
}
    @Test
    public void test_calculateOutdoor(){

        int result = home.calculateOutdoor(4,2);
        assertEquals(2,result);

    }
}