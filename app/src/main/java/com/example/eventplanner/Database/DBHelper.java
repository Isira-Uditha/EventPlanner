package com.example.eventplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eventplanner.Budgets;
import com.example.eventplanner.ShoppingLists;

import java.util.ArrayList;
import java.util.List;

import static android.service.controls.ControlsProviderService.TAG;

public class DBHelper extends SQLiteOpenHelper {

    //Declaring the database
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

        //Create the Task Table
        String SQL_CREATE_ENTRIES_TASKS =
                "CREATE TABLE " + EventsMaster.Tasks.TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        EventsMaster.Tasks.COLUMN_NAME_TASK_NAME + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_DATE + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_TIME + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_FINISHED + " TEXT," +
                        EventsMaster.Tasks.COLUMN_NAME_EVENT_ID+ " TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_TASKS); //execute content of sql entries

        //Implementing the Guest table inside the database
        String SQL_CREATE_ENTRIES_GUESTS =
                "CREATE TABLE " + EventsMaster.Guest.TABLE_NAME + "(" +
                        EventsMaster.Guest._ID + " INTEGER PRIMARY KEY," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_NAME + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_AGE + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED + " INTEGER," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE + " TEXT," +
                        EventsMaster.Guest.COLUMN_NAME_GUEST_EVENT_ID + " INTEGER)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_GUESTS); //execute content of sql entries

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


        //Anuththara's

        String SQL_CREATE_ENTRIES_BUDGETS =
                "CREATE TABLE " + EventsMaster.Budgets.TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        EventsMaster.Budgets.COLUMN_NAME_EBNAME + " TEXT," +
                        EventsMaster.Budgets.COLUMN_NAME_EBPADIDA + " TEXT," +
                        EventsMaster.Budgets.COLUMN_NAME_EBAMOUNT + " TEXT," +
                        EventsMaster.Budgets.COLUMN_NAME_EBNOTE + " TEXT," +
                        EventsMaster.Budgets.COLUMN_NAME_EMP_ID + " TEXT)";

        String SQL_CREATE_ENTRIEZ_SHOPPING =
                "CREATE TABLE " + EventsMaster.Shoppings.TABLE_SNAME + "(" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                        EventsMaster.Shoppings.COLUMN_NAME_SNAME + " TEXT," +
                        EventsMaster.Shoppings.COLUMN_NAME_SQTY + " TEXT," +
                        EventsMaster.Shoppings.COLUMN_NAME_SPRICE + " TEXT," +
                        EventsMaster.Shoppings.COLUMN_NAME_SNOTE + " TEXT," +
                        EventsMaster.Shoppings.COLUMN_NAME_EMP_ID + " TEXT)";

        //execute the comtemts of  SQL_CREATE_ENTERIES
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES_BUDGETS);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIEZ_SHOPPING);


    }

    //Insert Tasks Information to the database
    public long addInfo(String taskName , String date , String time , String description , int finished , int eventId){

        //Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        //Create a new map of values, where column names the keys
        ContentValues values = new ContentValues();
        values.put(EventsMaster.Tasks.COLUMN_NAME_TASK_NAME, taskName);
        values.put(EventsMaster.Tasks.COLUMN_NAME_DATE, date);
        values.put(EventsMaster.Tasks.COLUMN_NAME_TIME,  time );
        values.put(EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION,  description );
        values.put(EventsMaster.Tasks.COLUMN_NAME_FINISHED,  finished);
        values.put(EventsMaster.Tasks.COLUMN_NAME_EVENT_ID,  eventId);

        //Insert the new row , returning the primary key value of the new row
        long newRowId= db.insert(EventsMaster.Tasks.TABLE_NAME,null, values);
        return newRowId;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Calculates the amount of all Tasks
    public int countTasks(String eventId){

        SQLiteDatabase db = getReadableDatabase();
     //   String query = "SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME;
        Cursor cursor = db.rawQuery("SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + "=?", new String[]{String.valueOf(eventId)});

        return cursor.getCount();
    }

    //Retrieve all Task Records
    public List<Task> readAll(String eventId){

        List<Task> tasks = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();



        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + "=?", new String[]{String.valueOf(eventId)});

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

    //Delete a Task
    public void deleteTask(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(EventsMaster.Tasks.TABLE_NAME,COLUMN_NAME_ID+" =?",new String[]{String.valueOf(id)});


        sqLiteDatabase.close();
    }


    //Retrieve a Single Task
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

    //Calculates the amount of completed Tasks
    public int countFinished(int i , String eventId) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_FINISHED + " = ? AND " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + " =?",new String[]{String.valueOf(1),eventId});
        return cursor.getCount();
    }

    /////////////////////
    //ISIRA - DATABASE
    ////////////////////

    //This function is required to add a new Guest record to the application
    public long addInfo_guest(String guestName, String guestGender, String guestAge, String guestContact, String guestEmail, int guestInvited, String guestNote, int eID) {

        //Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        //Create a new map of values, where column names the keys
        ContentValues values = new ContentValues();
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NAME, guestName);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER, guestGender);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_AGE, guestAge);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT, guestContact);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL, guestEmail);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED, guestInvited);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE, guestNote);
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_EVENT_ID, eID);

        //Insert the new row
        long newRowId = db.insert(EventsMaster.Guest.TABLE_NAME, null, values);
        return newRowId;

    }

    //This function is required to fetch all Guest records from the database
    public List<GuestModel> readAllGuest(String eid) {

        List<GuestModel> guests = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + EventsMaster.Guest.TABLE_NAME + " WHERE " + EventsMaster.Guest.COLUMN_NAME_GUEST_EVENT_ID + "=" + eid;

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

    //This function is required to delete a Guest record from the database
    public void deleteGuest(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(EventsMaster.Guest.TABLE_NAME,EventsMaster.Guest._ID+" =?",new String[]{String.valueOf(id)});

        sqLiteDatabase.close();


    }

    //This function is required to select a specific Guest record from the database
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

    //This function is required to Update a specific Guest record
    public int updateGuest(GuestModel guest){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NAME, guest.getGuestName());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_GENDER, guest.getGuestGender());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_AGE, guest.getGuestAge());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_CONTACT, guest.getGuestContact());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_EMAIL, guest.getGuestEmail());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED, guest.getGuestCheck());
        values.put(EventsMaster.Guest.COLUMN_NAME_GUEST_NOTE, guest.getGuestNote());

        int status = db.update(EventsMaster.Guest.TABLE_NAME,values,EventsMaster.Guest._ID+" =?",new String[]{String.valueOf(guest.getGuestID())});

        db.close();
        return status;

    }

    //This function is required to calculate the total number of Guest related to specific event.
    public int totalGuest(String eID){

        System.out.println("DBHADLER EMPID " + eID);
        SQLiteDatabase db = getReadableDatabase();
        String count = "SELECT * FROM " + EventsMaster.Guest.TABLE_NAME + " WHERE " + EventsMaster.Guest.COLUMN_NAME_GUEST_EVENT_ID+ " = " + eID ;
        Cursor cursor = db.rawQuery(count,null);
        int num = cursor.getCount();
        cursor.close();
        return num;

    }

    //This function is required to calculate the total number of invited Guest related to specific event.
    public int invitedGuest(String eID){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EventsMaster.Guest.TABLE_NAME + " WHERE " + EventsMaster.Guest.COLUMN_NAME_GUEST_INVITED + " = ? AND " + EventsMaster.Guest.COLUMN_NAME_GUEST_EVENT_ID + " =?",new String[]{"1",eID});
        //Cursor cursor = db.rawQuery(count,null);
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
        sqLiteDatabase.delete(EventsMaster.Tasks.TABLE_NAME, EventsMaster.Tasks.COLUMN_NAME_EVENT_ID+ " =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.delete(EventsMaster.Guest.TABLE_NAME, EventsMaster.Guest.COLUMN_NAME_GUEST_EVENT_ID+ " =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.delete(EventsMaster.Budgets.TABLE_NAME, EventsMaster.Budgets.COLUMN_NAME_EMP_ID+ " =?",new String[]{String.valueOf(id)});
        sqLiteDatabase.delete(EventsMaster.Shoppings.TABLE_SNAME, EventsMaster.Shoppings.COLUMN_NAME_EMP_ID+ " =?",new String[]{String.valueOf(id)});

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

    public int countIndoor(String place) {

        SQLiteDatabase db = getReadableDatabase();
        //String query = "SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE =?" + EventsMaster.Tasks.COLUMN_NAME_FINISHED +  String.valueOf(1);
        Cursor cursor = db.rawQuery("SELECT * FROM " + EventsMaster.Events.TABLE_NAME + " WHERE " + EventsMaster.Events.COLUMN_NAME_PLACE + "=?" , new String[]{String.valueOf(place)});

        // Cursor cursor = db.rawQuery(query,null);

        // Cursor cursor = db.rawQuery("SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_FINISHED + "=?", new String[]{String.valueOf(i)});
        return cursor.getCount();
    }


    //BUDGET
    public long addBInfo(String bName, String bPadiAmount, String bAmount, String bNote, int eid) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBNAME, bName);
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBPADIDA, bPadiAmount);
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBAMOUNT, bAmount);
        values.put(EventsMaster.Budgets.COLUMN_NAME_EBNOTE, bNote);
        values.put(EventsMaster.Budgets.COLUMN_NAME_EMP_ID, eid);

        long newRowId = db.insert(EventsMaster.Budgets.TABLE_NAME, null, values);

        return newRowId;
    }

    //SHOPIING-LIST
    public long addSLInfo(String sName, String sQty, String sPrice, String sNote, int eid) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SNAME, sName);
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SQTY, sQty);
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SPRICE, sPrice);
        values.put(EventsMaster.Shoppings.COLUMN_NAME_SNOTE, sNote);
        values.put(EventsMaster.Shoppings.COLUMN_NAME_EMP_ID, eid);

        long newRowId = db.insert(EventsMaster.Shoppings.TABLE_SNAME, null, values);

        return newRowId;
    }



    public List<Budgets> readAllBudgets(String eid) {

        List<Budgets> bajs = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM " + EventsMaster.Budgets.TABLE_NAME + " WHERE " + EventsMaster.Budgets.COLUMN_NAME_EMP_ID + "=?", new String[]{String.valueOf(eid)});

       // Cursor cursor = sqLiteDatabase.rawQuery(query, null);


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
    public List<ShoppingLists> readAllShoppings(String eid) {
        List<ShoppingLists> shpls = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM " + EventsMaster.Shoppings.TABLE_SNAME + " WHERE " + EventsMaster.Shoppings.COLUMN_NAME_EMP_ID + "=?", new String[]{String.valueOf(eid)});

       // String query = "SELECT * FROM " + EventsMaster.Shoppings.TABLE_SNAME;
       // Cursor cursor = sqLiteDatabase.rawQuery(query, null);

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
        db.delete(EventsMaster.Budgets.TABLE_NAME, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(id)});
        db.close();

    }

    //Retrieve a single budget details
    public Budgets getSingleBudget(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(EventsMaster.Budgets.TABLE_NAME, new String[]{COLUMN_NAME_ID, EventsMaster.Budgets.COLUMN_NAME_EBNAME, EventsMaster.Budgets.COLUMN_NAME_EBPADIDA, EventsMaster.Budgets.COLUMN_NAME_EBAMOUNT, EventsMaster.Budgets.COLUMN_NAME_EBNOTE,}, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(id)}, null, null, null);

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
        Cursor cursor = sqLiteDatabase.query(EventsMaster.Shoppings.TABLE_SNAME, new String[]{
                COLUMN_NAME_ID,
                EventsMaster.Shoppings.COLUMN_NAME_SNAME,
                EventsMaster.Shoppings.COLUMN_NAME_SQTY,
                EventsMaster.Shoppings.COLUMN_NAME_SPRICE,
                EventsMaster.Shoppings.COLUMN_NAME_SNOTE,
        }, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(ids)}, null, null, null);


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
        db.delete(EventsMaster.Shoppings.TABLE_SNAME, COLUMN_NAME_ID + "=?", new String[]{String.valueOf(ids)});
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

        int status = db.update(EventsMaster.Shoppings.TABLE_SNAME, values, COLUMN_NAME_ID + " =?", new String[]{String.valueOf(shoppingList.getId())});
        db.close();
        return status;

    }

    //Calculates the summation of the whole budgets
    public int sumBudget(String eid) {

        SQLiteDatabase db = getReadableDatabase();
        int total;

        //Cursor cursor = db.rawQuery("SELECT SUM( " + COLUMN_NAME_EBAMOUNT + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + "=?", new String[]{String.valueOf(eventId)});

        Cursor cursor = db.rawQuery("SELECT SUM(" + EventsMaster.Budgets.COLUMN_NAME_EBAMOUNT + ") as Total FROM " + EventsMaster.Budgets.TABLE_NAME + " WHERE " + EventsMaster.Budgets.COLUMN_NAME_EMP_ID+ " =?",new String[]{eid});

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            //  Cursor cursor = db.rawQuery(query,null);
            return total;

        }

        return 0;

    }

    //Calculates the summation of the all paid amounts of the budget
    public int sumBPaid(String eid) {

        SQLiteDatabase db = getReadableDatabase();
        int total;

        //Cursor cursor = db.rawQuery("SELECT SUM( " + COLUMN_NAME_EBAMOUNT + " WHERE " + EventsMaster.Tasks.COLUMN_NAME_EVENT_ID + "=?", new String[]{String.valueOf(eventId)});
        Cursor cursor = db.rawQuery("SELECT SUM(" + EventsMaster.Budgets.COLUMN_NAME_EBPADIDA + ") as Total FROM " + EventsMaster.Budgets.TABLE_NAME + " WHERE " +  EventsMaster.Shoppings.COLUMN_NAME_EMP_ID + " =?",new String[]{eid});

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            //  Cursor cursor = db.rawQuery(query,null);
            return total;

        }

        return 0;

    }

    //Calculates the summation of all item quantity in the shopping list
    public int sumShopping(String eid){
        SQLiteDatabase db = getReadableDatabase();
        int total ;

        Cursor cursor = db.rawQuery("SELECT SUM(" + EventsMaster.Shoppings.COLUMN_NAME_SQTY + " * " + EventsMaster.Shoppings.COLUMN_NAME_SPRICE + ") as Total FROM " + EventsMaster.Shoppings.TABLE_SNAME +  " WHERE " +  EventsMaster.Shoppings.COLUMN_NAME_EMP_ID + " =?",new String[]{eid});

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            //  Cursor cursor = db.rawQuery(query,null);
            return total;

        }

        return 0;
    }

}