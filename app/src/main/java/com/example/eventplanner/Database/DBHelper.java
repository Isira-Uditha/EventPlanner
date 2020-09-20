package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;

import com.example.eventplanner.BudgetDetails;
import com.example.eventplanner.Budgets;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EventInfo.db";
    private SQLiteDatabase db;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null ,  1);
    }

    public static final String TABLE_NAME="budget";
    public static final String COLUMN_NAME_EBNAME="bname";
    public static final String COLUMN_NAME_EBPADIDA="bpadia";
    public static final String COLUMN_NAME_EBAMOUNT="bamount";
    public static final String COLUMN_NAME_EBNOTE="bnote";
    public static String COLUMN_NAME_ID="id";


    // @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID     + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_EBNAME + " TEXT," +
                        COLUMN_NAME_EBPADIDA + " TEXT," +
                        COLUMN_NAME_EBAMOUNT + " TEXT," +
                        COLUMN_NAME_EBNOTE + " TEXT)";

        //execute the comtemts of  SQL_CREATE_ENTERIES
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addInfo(String bName, String bPadiAmount, String bAmount, String bNote) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_EBNAME, bName);
        values.put(COLUMN_NAME_EBPADIDA, bPadiAmount);
        values.put(COLUMN_NAME_EBAMOUNT, bAmount);
        values.put(COLUMN_NAME_EBNOTE, bNote);

        long newRowId = db.insert(TABLE_NAME,null,values);

        return  newRowId;
    }
    public List<Budgets>readAllEvent(){

        List<Budgets> budget = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Budgets.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Budgets budgets = new Budgets();

                budgets.setId((cursor.getInt(0)));
                budgets.setBudgetName((cursor.getString(1)));
                budgets.setPadiAmount((cursor.getString(2)));
                budgets.setAmount((cursor.getString(3)));
                budgets.setNote((cursor.getString(4)));

                budget.add(budgets);
            }while (cursor.moveToNext());
        }
        return budget;

    }


    public List<Budgets>  readAllBudgets(){

        List<Budgets> bajs = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Budgets.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);


        if(cursor.moveToFirst()) {

            do {
                Budgets baj = new Budgets();

                baj.setId((cursor.getInt(0)));
                baj.setBudgetName((cursor.getString(1)));
                baj.setPadiAmount((cursor.getString(2)));

                bajs.add(baj);


            } while (cursor.moveToNext());
        }

        return bajs;

    }

}




