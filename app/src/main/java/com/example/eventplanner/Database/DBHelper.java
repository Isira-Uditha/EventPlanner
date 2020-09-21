package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.eventplanner.Event;

import java.util.ArrayList;
import java.util.List;

import static android.service.controls.ControlsProviderService.TAG;

public class DBHelper extends SQLiteOpenHelper {

    public  static  final  String DATABASE_NAME ="EventInformation.db";
    private SQLiteDatabase db;


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    public static final String TABLE_NAME = "events";
    public static final String COLUMN_NAME_EVENTNAME = "eventname";
    public static final String COLUMN_NAME_DATE = "eventdate";
    public static final String COLUMN_NAME_TIME = "eventtime";
    public static final String COLUMN_NAME_LOCATION = "eventlocation";
    public static final String COLUMN_NAME_THEME = "eventtheme";
    public static final String COLUMN_NAME_DRESSCODE = "eventdresscode";
    public static final String COLUMN_NAME_PHOTOGRAPHERS = "eventphotographers";
    public static final String COLUMN_NAME_DESCRIPTION = "eventdescription";
    public static final String COLUMN_NAME_PLACE = "place";
    public static  String COLUMN_NAME_ID= "id";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + EventsMaster.Events.TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        EventsMaster.Events.COLUMN_NAME_EVENTNAME + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_DATE + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_TIME + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_LOCATION + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_THEME + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_DRESSCODE + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_PHOTOGRAPHERS + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        EventsMaster.Events.COLUMN_NAME_PLACE + " TEXT )";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public long addInfo(String eventName,String eventDate,String eventTime,String eventLocation,String eventTheme,String eventDresscode,String eventPhotographers,String eventDescription,String place){
        SQLiteDatabase db =getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Events.COLUMN_NAME_EVENTNAME,eventName);
        values.put(EventsMaster.Events.COLUMN_NAME_DATE,eventDate);
        values.put(EventsMaster.Events.COLUMN_NAME_TIME,eventTime);
        values.put(EventsMaster.Events.COLUMN_NAME_LOCATION,eventLocation);
        values.put(EventsMaster.Events.COLUMN_NAME_THEME,eventTheme);
        values.put(EventsMaster.Events.COLUMN_NAME_DRESSCODE,eventDresscode);
        values.put(EventsMaster.Events.COLUMN_NAME_PHOTOGRAPHERS,eventPhotographers);
        values.put(EventsMaster.Events.COLUMN_NAME_DESCRIPTION,eventDescription);
        values.put(EventsMaster.Events.COLUMN_NAME_PLACE,place);

        long  newRowId = db.insert(EventsMaster.Events.TABLE_NAME,null,values);
        return  newRowId;
    }

        public List<Event> readAllEvent(){


        List<Event> events = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Events.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);


        if(cursor.moveToFirst()) {

            do {
                Event event = new Event();

                event.setId((cursor.getInt(0)));
                event.setEventName((cursor.getString(1)));
                event.setDate((cursor.getString(2)));
                event.setTime((cursor.getString(3)));
                event.setLocation((cursor.getString(4)));
                event.setTheme((cursor.getString(5)));
                event.setDressCode((cursor.getString(6)));
                event.setPhotographer((cursor.getString(7)));
                event.setDescription((cursor.getString(8)));
                event.setPlace((cursor.getString(9)));

                events.add(event);


            } while (cursor.moveToNext());
        }

        return events;

    }



    public  void deleteInfo (String eventName){
        SQLiteDatabase db= getReadableDatabase();
        String selection = EventsMaster.Events.COLUMN_NAME_EVENTNAME + " LIKE ?";
        String [] selectionArgs = {eventName};
        db.delete(EventsMaster.Events.TABLE_NAME,selection,selectionArgs);
    }
    public int updateInfo (String eventName,String eventLocation){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(EventsMaster.Events.COLUMN_NAME_LOCATION,eventLocation);

        String selection = EventsMaster.Events.COLUMN_NAME_EVENTNAME + " LIKE ?";
        String [] selectionArgs = {eventName};

        int count = db.update(
                EventsMaster.Events.TABLE_NAME,
                values,
                selection,
                selectionArgs

        );

        return  count;
    }

    public int countEvents(){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + EventsMaster.Events.TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public void deleteEvent(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLUMN_NAME_ID + " =?",new String[]{String.valueOf(id)});


        sqLiteDatabase.close();


    }

    public Event getSingleEvent(int id){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor= db.query(TABLE_NAME,new String[]{COLUMN_NAME_ID,COLUMN_NAME_EVENTNAME,COLUMN_NAME_DATE,COLUMN_NAME_TIME,COLUMN_NAME_LOCATION,COLUMN_NAME_THEME,COLUMN_NAME_DRESSCODE,COLUMN_NAME_PHOTOGRAPHERS,COLUMN_NAME_DESCRIPTION , COLUMN_NAME_PLACE},
                COLUMN_NAME_ID  + "= ?",new String[]{String.valueOf(id)},null,null,null);

        Event event;
        if(cursor != null){
            cursor.moveToFirst();
            event = new Event(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9)

            );

            return event;
        }
        return null;
    }

    public int updateSingleEvent(Event event){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_EVENTNAME,event.getEventName());
        values.put(COLUMN_NAME_DATE,event.getDate());
        values.put(COLUMN_NAME_TIME,event.getTime());
        values.put(COLUMN_NAME_LOCATION,event.getLocation());
        values.put(COLUMN_NAME_THEME,event.getTheme());
        values.put(COLUMN_NAME_DRESSCODE,event.getDressCode());
        values.put(COLUMN_NAME_PHOTOGRAPHERS,event.getPhotographer());
        values.put(COLUMN_NAME_DESCRIPTION,event.getDescription());
        values.put(EventsMaster.Events.COLUMN_NAME_PLACE,event.getPlace());


        int status = db.update(TABLE_NAME,values,COLUMN_NAME_ID +" =?",
                new String[]{String.valueOf(event.getId())});

        db.close();
        return status;


    }
}

