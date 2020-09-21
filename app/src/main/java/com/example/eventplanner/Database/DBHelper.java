package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.service.controls.ControlsProviderService.TAG;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "EventPlanner.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    /*public static final String TABLE_NAME = "tasks";
    public static String COLUMN_NAME_ID= "id";
    public static final String COLUMN_NAME_TASK_NAME= "taskName";
    public static final String COLUMN_NAME_DATE="date";
    public static final String COLUMN_NAME_TIME= "time";
    public static final String COLUMN_NAME_DESCRIPTION= "description";
    public static final String COLUMN_NAME_FINISHED= "finished";*/

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
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_ENTRIES_TASKS =
                "CREATE TABLE " + EventsMaster.Tasks.TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        EventsMaster.Tasks.COLUMN_NAME_TASK_NAME + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_DATE + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_TIME + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_FINISHED + " TEXT )";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_TASKS); //execute content of sql entries

        String SQL_CREATE_ENTRIES_GUESTS =
                "CREATE TABLE " + EventsMaster.Guest.TABLE_NAME + "(" +
                        EventsMaster.Guest._ID + " INTEGER PRIMARY KEY," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_NAME + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_AGE + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED + " INTEGER," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE + " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_GUESTS);

        String SQL_CREATE_ENTRIES_EVENTS =
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
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_EVENTS);

    }


    public long addInfo(String taskName , String date , String time , String description , int finished){

        //Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        //Create a new map of values, where column names the keys
        ContentValues values = new ContentValues();
        values.put(EventsMaster.Tasks.COLUMN_NAME_TASK_NAME, taskName);
        values.put(EventsMaster.Tasks.COLUMN_NAME_DATE, date);
        values.put(EventsMaster.Tasks.COLUMN_NAME_TIME,  time );
        values.put(EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION,  description );
        values.put(EventsMaster.Tasks.COLUMN_NAME_FINISHED,  finished);

        //Insert the new row , returning the primary key value of the new row
        long newRowId= db.insert(EventsMaster.Tasks.TABLE_NAME,null, values);
        return newRowId;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int countTasks(){

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public List<Task> readAll(){

        List<Task> tasks = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);


        if(cursor.moveToFirst()) {

            do {
                Task task = new Task();

                task.setId(cursor.getInt(0));
                task.setTaskName(cursor.getString(1));
                task.setDate(cursor.getString(2));
                task.setTime(cursor.getString(3));
                task.setDescription(cursor.getString(4));
                task.setFinished(cursor.getInt(5));

                tasks.add(task);


            } while (cursor.moveToNext());
        }

        return tasks;

    }

    public void deleteTask(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(EventsMaster.Tasks.TABLE_NAME,COLUMN_NAME_ID+" =?",new String[]{String.valueOf(id)});

        //sqLiteDatabase.delete(TABLE_NAME,COLUMN_NAME_TASK_NAME+" =?");
        sqLiteDatabase.close();

        //Define 'where' part of query
        //String selection = EventsMaster.Tasks._ID+ " LIKE ?" ;

        //Specify arguments n placeholder order
        // int[] selectionArgs = {id};

        //Issue SQL statement
        //sqLiteDatabase.delete( EventsMaster.Tasks._ID,selection, selectionArgs);
    }

   /* public void deleteTask (SQLiteDatabase db , String deleted){
        String selection = Contracts.achieveTaskClass.ID+" LIKE ?";
        String[] selectionArgs = {deleted};
        db.delete(Contracts.achieveTaskClass.ACHIEVETASK_TABLE,selection,selectionArgs);
    }*/

    //Get a single task

    public Task getSingleTask(int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.query(EventsMaster.Tasks.TABLE_NAME, new String[] {COLUMN_NAME_ID,EventsMaster.Tasks.COLUMN_NAME_TASK_NAME,EventsMaster.Tasks.COLUMN_NAME_DATE,EventsMaster.Tasks.COLUMN_NAME_TIME,EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION,EventsMaster.Tasks.COLUMN_NAME_FINISHED} ,COLUMN_NAME_ID + " =?",new String[]{String.valueOf(id)},null , null,null );

        Task task;

        if(cursor  != null){

            cursor.moveToFirst();

            task = new Task(

                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5)




            );

            return  task;

        }

        return  null;

    }

    //Update a Task
    public int updateTask(Task task){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EventsMaster.Tasks.COLUMN_NAME_TASK_NAME, task.getTaskName());
        values.put(EventsMaster.Tasks.COLUMN_NAME_DATE, task.getDate());
        values.put(EventsMaster.Tasks.COLUMN_NAME_TIME, task.getTime() );
        values.put(EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(EventsMaster.Tasks.COLUMN_NAME_FINISHED,  task.getFinished());

        int status = sqLiteDatabase.update(EventsMaster.Tasks.TABLE_NAME,values,COLUMN_NAME_ID+ " =?",new String[]{String.valueOf(task.getId())});

        sqLiteDatabase.close();

        return status;



    }

    public int countFinished(int i) {

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE =?" + EventsMaster.Tasks.COLUMN_NAME_FINISHED +  String.valueOf(1);


        // Cursor cursor = db.rawQuery(query,null);

        Cursor cursor = db.rawQuery("SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_FINISHED + "=?", new String[]{String.valueOf(i)});
        return cursor.getCount();
    }

    /////////////////////
    //ISIRA - DATABASE
    ////////////////////

    public long addInfo_guest(String guestName, String guestGender, String guestAge, String guestContact, String guestEmail, int guestInvited, String guestNote) {

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


    public long addInfo_event(String eventName,String eventDate,String eventTime,String eventLocation,String eventTheme,String eventDresscode,String eventPhotographers,String eventDescription,String place){
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