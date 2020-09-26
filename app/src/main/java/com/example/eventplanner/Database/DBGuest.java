package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBGuest extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EventPlanner1.db";

    public DBGuest(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + EventsMaster.Guest.TABLE_NAME + "(" +
                        EventsMaster.Guest._ID + " INTEGER PRIMARY KEY," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_NAME + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_AGE + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED + " INTEGER," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE + " TEXT)";

        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addInfo(String guestName, String guestGender, String guestAge, String guestContact, String guestEmail, int guestInvited, String guestNote) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NAME, guestName);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER, guestGender);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_AGE, guestAge);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT, guestContact);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL, guestEmail);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED, guestInvited);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE, guestNote);

        //Insert the new row
        long newRowId = db.insert(EventsMaster.Guest.TABLE_NAME, null, values);
        return newRowId;

    }

    public List<GuestModel> readAllGuest() {

        List<GuestModel> guests = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + EventsMaster.Guest.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                GuestModel guest = new GuestModel();

                guest.setGuestID(cursor.getInt(0));
                guest.setGuestName(cursor.getString(1));
                guest.setGuestGender(cursor.getString(2));
                guest.setGuestAge(cursor.getString(3));
                guest.setGuestContact(cursor.getString(4));
                guest.setGuestEmail(cursor.getString(5));
                guest.setGuestCheck(cursor.getInt(6));
                guest.setGuestNote(cursor.getString(7));

                guests.add(guest);
            } while (cursor.moveToNext());
        }
            return guests;
        }

    public void deleteGuest(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(EventsMaster.Guest.TABLE_NAME,EventsMaster.Guest._ID+" =?",new String[]{String.valueOf(id)});

        sqLiteDatabase.close();


    }


    public GuestModel readSelectedGuest(int id) {


        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor =  sqLiteDatabase.query(EventsMaster.Guest.TABLE_NAME, new String[] {
                EventsMaster.Guest._ID,
                EventsMaster.Guest.COLUMN_NAME_GUEST_NAME,
                EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER,
                EventsMaster.Guest.COLUMN_NAME_GUEST_AGE,
                EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT,
                EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL,
                EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED,
                EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE,} ,
                EventsMaster.Guest._ID + " =?",new String[]{String.valueOf(id)},null , null,null );

        GuestModel guest;

        if (cursor != null){

            cursor.moveToFirst();

            guest = new GuestModel(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6),
                    cursor.getString(7 )

            );
            return guest;
        }
    return null;
    }

    public int updateGuest(GuestModel guest){

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

    }

    public int totalGuest(){

        SQLiteDatabase db = getReadableDatabase();
        String count = "SELECT * FROM " + EventsMaster.Guest.TABLE_NAME;
        Cursor cursor = db.rawQuery(count,null);
        int num = cursor.getCount();
        cursor.close();
        return num;

    }

    public int invitedGuest(){

        SQLiteDatabase db = getReadableDatabase();
        String count = "SELECT * FROM " + EventsMaster.Guest.TABLE_NAME + " WHERE " + EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED+ " = 1";
        Cursor cursor = db.rawQuery(count,null);
        int num = cursor.getCount();
        cursor.close();
        return num;

    }

}


