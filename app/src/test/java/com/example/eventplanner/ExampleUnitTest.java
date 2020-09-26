package com.example.eventplanner;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.eventplanner.Database.DBHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {





    private DBHelper dbHelper;
    private Context context;
    TaskHome taskHome;
    BudgetDetails budgetDetails;
    Home home;

    @Before
    public void setUp(){

        budgetDetails = new BudgetDetails();
        home = new Home();
        taskHome = new TaskHome();

    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test

    public void test_checkTasks(){
     int results = taskHome.TasksToDo(4,2);
     assertEquals(2,results);
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