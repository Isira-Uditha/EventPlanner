package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.CalendarContract;

import com.example.eventplanner.BudgetDetails;
import com.example.eventplanner.BudgetOne;
import com.example.eventplanner.Budgets;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EventInfo.db";
    private SQLiteDatabase db;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null ,  1);
    }

    //budegt details table
    public static final String TABLE_NAME="budget";
    public static final String COLUMN_NAME_EBNAME="bname";
    public static final String COLUMN_NAME_EBPADIDA="bpadia";
    public static final String COLUMN_NAME_EBAMOUNT="bamount";
    public static final String COLUMN_NAME_EBNOTE="bnote";
    public static String COLUMN_NAME_ID="id";

    //shoppingList table
    public static final String TABLE_SNAME="shopping";
    public static final String COLUMN_NAME_SNAME="sname";
    public static final String COLUMN_NAME_SQTY="sqty";
    public static final String COLUMN_NAME_SPRICE="sprice";
    public static final String COLUMN_NAME_SNOTE="snote";
    public static String COLUMN_NAME_SID="id";


    // @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_NAME_ID     + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_EBNAME + " TEXT," +
                        COLUMN_NAME_EBPADIDA + " TEXT," +
                        COLUMN_NAME_EBAMOUNT + " TEXT," +
                        COLUMN_NAME_EBNOTE + " TEXT)";

        String SQL_CREATE_ENTRIEZ =
                "CREATE TABLE" + TABLE_SNAME+"(" +
                        COLUMN_NAME_SID + " INTEGER PRIMARY KEY," +
                            COLUMN_NAME_SNAME +"TEXT," +
                        COLUMN_NAME_SQTY +"TEXT,"+
                        COLUMN_NAME_SPRICE + "TEXT,"+
                        COLUMN_NAME_SNOTE + "TEXT)";

        //execute the comtemts of  SQL_CREATE_ENTERIES
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIEZ);

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //BUDGET
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
    //SHOPIING-LIST
    public long addSLInfo(String sName, String bPadiAmount, String bAmount, String bNote) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(COLUMN_NAME_EBNAME, bName);
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

    //Delete budget details
    public  void deleteBudget(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME_ID +" =?",new String[]{String.valueOf(id)});
        db.close();

    }

    public Budgets getSingleBudget(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.query(TABLE_NAME, new String[] {COLUMN_NAME_ID,COLUMN_NAME_EBNAME,COLUMN_NAME_EBPADIDA,COLUMN_NAME_EBAMOUNT,COLUMN_NAME_EBNOTE,} ,COLUMN_NAME_ID + " =?",new String[]{String.valueOf(id)},null , null,null );

        Budgets budget;

        if(cursor  != null){

            cursor.moveToFirst();

            budget = new Budgets(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)

            );

            return  budget;

        }

        return  null;

    }


    //Update budget Details
   public int updateBudget(Budgets budget){
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
       values.put(EventsMaster.Budgets.COLUMN_NAME_EBNAME,budget.getBudgetName());
       values.put(EventsMaster.Budgets.COLUMN_NAME_EBPADIDA,budget.getPadiAmount());
       values.put(EventsMaster.Budgets.COLUMN_NAME_EBAMOUNT,budget.getAmount());
       values.put(EventsMaster.Budgets.COLUMN_NAME_EBNOTE,budget.getNote());

       int status = db.update(EventsMaster.Budgets.TABLE_NAME,values,COLUMN_NAME_ID +" =?",new String[]{String.valueOf(budget.getId())});

       db.close();
       return status;

   }

}

    /*public int updateGuest(GuestModel guest){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NAME, guest.getGuestName());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER, guest.getGuestGender());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_AGE, guest.getGuestAge());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT, guest.getGuestContact());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL, guest.getGuestEmail());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED, guest.getGuestCheck());

        int status = db.update(EventsMaster.Guest.TABLE_NAME,values,EventsMaster.Guest._ID+" =?",new String[]{String.valueOf(guest.getGuestID())});

        db.close();
        return status;

    }*/



