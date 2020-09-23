package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eventplanner.Budgets;
import com.example.eventplanner.ShoppingLists;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EventInfo.db";
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //budegt details table
    public static final String TABLE_NAME = "budget";
    public static final String COLUMN_NAME_EBNAME = "bname";
    public static final String COLUMN_NAME_EBPADIDA = "bpadia";
    public static final String COLUMN_NAME_EBAMOUNT = "bamount";
    public static final String COLUMN_NAME_EBNOTE = "bnote";
    public static String COLUMN_NAME_ID = "id";

    //shoppingList table
    public static final String TABLE_SNAME = "shopping";
    public static final String COLUMN_NAME_SNAME = "sname";
    public static final String COLUMN_NAME_SQTY = "sqty";
    public static final String COLUMN_NAME_SPRICE = "sprice";
    public static final String COLUMN_NAME_SNOTE = "snote";
    public static String COLUMN_NAME_SID = "ids";


    // @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_EBNAME + " TEXT," +
                        COLUMN_NAME_EBPADIDA + " TEXT," +
                        COLUMN_NAME_EBAMOUNT + " TEXT," +
                        COLUMN_NAME_EBNOTE + " TEXT)";

        String SQL_CREATE_ENTRIEZ =
                "CREATE TABLE " + TABLE_SNAME + "(" +
                        COLUMN_NAME_SID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_SNAME + " TEXT," +
                        COLUMN_NAME_SQTY + " TEXT," +
                        COLUMN_NAME_SPRICE + " TEXT," +
                        COLUMN_NAME_SNOTE + " TEXT)";

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

        long newRowId = db.insert(TABLE_NAME, null, values);

        return newRowId;
    }

    //SHOPIING-LIST
    public long addSLInfo(String sName, String sQty, String sPrice, String sNote) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_SNAME, sName);
        values.put(COLUMN_NAME_SQTY, sQty);
        values.put(COLUMN_NAME_SPRICE, sPrice);
        values.put(COLUMN_NAME_SNOTE, sNote);

        long newRowId = db.insert(TABLE_SNAME, null, values);

        return newRowId;
    }

    public List<Budgets> readAllEvent() {

        List<Budgets> budget = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Budgets.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Budgets budgets = new Budgets();

                budgets.setId((cursor.getInt(0)));
                budgets.setBudgetName((cursor.getString(1)));
                budgets.setPadiAmount((cursor.getString(2)));
                budgets.setAmount((cursor.getString(3)));
                budgets.setNote((cursor.getString(4)));

                budget.add(budgets);
            } while (cursor.moveToNext());
        }
        return budget;

    }


    public List<Budgets> readAllBudgets() {

        List<Budgets> bajs = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Budgets.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);


        if (cursor.moveToFirst()) {

            do {
                Budgets baj = new Budgets();

                baj.setId((cursor.getInt(0)));
                baj.setBudgetName((cursor.getString(1)));
                baj.setPadiAmount((cursor.getString(2)));
                baj.setAmount((cursor.getString(3)));

                bajs.add(baj);


            } while (cursor.moveToNext());
        }

        return bajs;

    }

    //ShoppingList
    /*public  List<ShoppingLists>realAllEvent(){
        List<ShoppingLists> shopping = new ArrayList();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM"+ EventsMaster.Shoppings.TABLE_SNAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do{
                ShoppingLists shoppingLists= new ShoppingLists();
                shoppingLists.setId((cursor.getInt(0)));
                shoppingLists.setShoppingName((cursor.getString(1)));
                shoppingLists.setQty((cursor.getString(2)));
                shoppingLists.setPrice((cursor.getString(3)));
                shoppingLists.setNote((cursor.getString(4)));

                shopping.add(shoppingLists);
            }while (cursor.moveToNext());

       }
        return  shopping;
    }*/
    public List<ShoppingLists> readAllShoppings() {
        List<ShoppingLists> shpls = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + EventsMaster.Shoppings.TABLE_SNAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ShoppingLists shpl = new ShoppingLists();
                shpl.setId(cursor.getInt(0));
                shpl.setShoppingName(cursor.getString(1));
                shpl.setQty(cursor.getString(2));
                shpl.setPrice(cursor.getString(3));
                shpl.setNote(cursor.getString(4));

                shpls.add(shpl);
            } while (cursor.moveToNext());

        }
        System.out.println("jjjjjjjjjjjjj " + shpls);
        return shpls;

    }

    //Delete budget details
    public void deleteBudget(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(id)});
        db.close();

    }

    public Budgets getSingleBudget(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{COLUMN_NAME_ID, COLUMN_NAME_EBNAME, COLUMN_NAME_EBPADIDA, COLUMN_NAME_EBAMOUNT, COLUMN_NAME_EBNOTE,}, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(id)}, null, null, null);

        Budgets budget;

        if (cursor != null) {

            cursor.moveToFirst();

            budget = new Budgets(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)

            );

            return budget;

        }

        return null;

    }

    //Retreive shopping List
    public ShoppingLists getSingleShopping(int ids) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_SNAME, new String[]{
                COLUMN_NAME_SID,
                COLUMN_NAME_SNAME,
                COLUMN_NAME_SQTY,
                COLUMN_NAME_SPRICE,
                COLUMN_NAME_SNOTE,
        }, COLUMN_NAME_SID + " =?", new String[]{String.valueOf(ids)}, null, null, null);


        ShoppingLists shoppingList;

        if (cursor != null) {

            cursor.moveToFirst();

            shoppingList = new ShoppingLists(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)

            );
            System.out.println("fggggggggdasasas sxxxx" + shoppingList);
            return shoppingList;

        }

        return null;

    }

    //Update budget Details
    public int updateBudget(Budgets budget) {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBNAME, budget.getBudgetName());
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBPADIDA, budget.getPadiAmount());
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBAMOUNT, budget.getAmount());
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBNOTE, budget.getNote());

        int status = db.update(EventsMaster.Budgets.TABLE_NAME, values, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(budget.getId())});

        db.close();
        return status;

    }


    //Delete Shpooing Details
    public void deleteShopping(int ids) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_SNAME, COLUMN_NAME_SID + "=?", new String[]{String.valueOf(ids)});
        db.close();
    }

   /* public ShoppingLists getSingleShopping(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor =sqLiteDatabase.query(TABLE_SNAME,new String[}{ COLUMN_NAME_SID,COLUMN_NAME_SNAME,COLUMN_NAME_SQTY,COLUMN_NAME_SPRICE,COLUMN_NAME_SNOTE,},COLUMN_NAME_SID + " =?",new String[]{String.valueOf(id)},null , null,null );

        ShoppingLists shoppingList;

        if(ursor  != null){

        cursor.moveToFirst();

        shoppingList = new ShoppingLists(

                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)

        );

        return  shoppingList;

    }

        return  null;
    }*/

    //Update shopiing list details
    public int updateShopping(ShoppingLists shoppingList) {
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SNAME, shoppingList.getShoppingName());
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SQTY, shoppingList.getQty());
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SPRICE, shoppingList.getPrice());
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SNOTE, shoppingList.getNote());

        int status = db.update(EventsMaster.Shoppings.TABLE_SNAME, values, COLUMN_NAME_SID + " =?", new String[]{String.valueOf(shoppingList.getId())});
        db.close();
        return status;

    }

    public int sumBudget() {

        SQLiteDatabase db = getReadableDatabase();
        int total;

        //Cursor cursor = db.rawQuery("SELECT SUM( " + COLUMN_NAME_EBAMOUNT + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + "=?", new String[]{String.valueOf(eventId)});

        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_NAME_EBAMOUNT + ") as Total FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            //  Cursor cursor = db.rawQuery(query,null);
            return total;

        }

        return 0;

    }

    public int sumBPaid() {

        SQLiteDatabase db = getReadableDatabase();
        int total;

        //Cursor cursor = db.rawQuery("SELECT SUM( " + COLUMN_NAME_EBAMOUNT + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + "=?", new String[]{String.valueOf(eventId)});

        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_NAME_EBPADIDA + ") as Total FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            //  Cursor cursor = db.rawQuery(query,null);
            return total;

        }

        return 0;

    }
    public int sumShopping(){
        SQLiteDatabase db = getReadableDatabase();
        int total ;

        Cursor cursor = db.rawQuery("SELECT SUM(" + COLUMN_NAME_SQTY + " * " + COLUMN_NAME_SPRICE + ") as Total FROM " + TABLE_SNAME, null);

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            //  Cursor cursor = db.rawQuery(query,null);
            return total;

        }

        return 0;
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



