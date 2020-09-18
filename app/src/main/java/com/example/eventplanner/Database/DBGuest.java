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

    public static final String DATABASE_NAME = "EventPlanner.db";

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
}

