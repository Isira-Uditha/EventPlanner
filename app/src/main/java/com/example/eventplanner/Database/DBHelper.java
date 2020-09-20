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

    public static final String TABLE_NAME = "tasks";
    public static String COLUMN_NAME_ID= "id";
    public static final String COLUMN_NAME_TASK_NAME= "taskName";
    public static final String COLUMN_NAME_DATE="date";
    public static final String COLUMN_NAME_TIME= "time";
    public static final String COLUMN_NAME_DESCRIPTION= "description";
    public static final String COLUMN_NAME_FINISHED= "finished";


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {




        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                       COLUMN_NAME_TASK_NAME + " TEXT," +
                       COLUMN_NAME_DATE + " TEXT," +
                        COLUMN_NAME_TIME + " TEXT," +
                      COLUMN_NAME_DESCRIPTION + " TEXT," +
                        COLUMN_NAME_FINISHED + " TEXT )";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES); //execute content of sql entries

    }


    public long addInfo(String taskName , String date , String time , String description , int finished){

        //Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        //Create a new map of values, where column names the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TASK_NAME, taskName);
        values.put(COLUMN_NAME_DATE, date);
        values.put(COLUMN_NAME_TIME,  time );
        values.put(COLUMN_NAME_DESCRIPTION,  description );
        values.put(COLUMN_NAME_FINISHED,  finished);

        //Insert the new row , returning the primary key value of the new row
        long newRowId= db.insert(TABLE_NAME,null, values);
        return newRowId;
    }

   /* public List readAllTaskInfo(){

        List<EventsMaster.Tasks> tasks = new ArrayList();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String query = "SELECT * FROM " + EventsMaster.Tasks.TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        //define a projection that specified which coloumns from the database
        //you will actually ise after this query

       /* String[] projection = {

                EventsMaster.Tasks._ID,
                EventsMaster.Tasks.COLUMN_NAME_TASK_NAME,
                EventsMaster.Tasks.COLUMN_NAME_DATE,
                EventsMaster.Tasks.COLUMN_NAME_TIME,
                EventsMaster.Tasks.COLUMN_NAME_TIME

        };*/

        //filter results where "userName"='SLIIT USER'
        //String selection = Users.COLUMN_NAME_USER_NAME+"=?";
        //String[] selectionArgs = ("");

        //How you want the results sorted in the resulting cursor
        //String sortOrder =  EventsMaster.Tasks.COLUMN_NAME_TASK_NAME+" DESC";

       /* Cursor cursor = sqLiteDatabase.query(
                EventsMaster.Tasks.TABLE_NAME, //the table to query
                projection, //the column to return
                null, //the column for the WHERE clause
                null, //the values for the where clause
                null, //don't group the rows
                null , //don't filter by row groups
                sortOrder//the sort order




        );*/

     /*  List ids = new ArrayList<>();
       List taskNames = new ArrayList<>();
        List dates = new ArrayList<>();
        List times = new ArrayList<>();
        List descriptions = new ArrayList<>();
        List finishes = new ArrayList<>();

       if(cursor.moveToNext()){

           do {


               String id = cursor.getString(cursor.getColumnIndexOrThrow(EventsMaster.Tasks._ID));
               String taskName = cursor.getString(cursor.getColumnIndexOrThrow(EventsMaster.Tasks.COLUMN_NAME_TASK_NAME));
               String date = cursor.getString(cursor.getColumnIndexOrThrow(EventsMaster.Tasks.COLUMN_NAME_DATE));
               String time = cursor.getString(cursor.getColumnIndexOrThrow(EventsMaster.Tasks.COLUMN_NAME_TIME));
               String description = cursor.getString(cursor.getColumnIndexOrThrow(EventsMaster.Tasks.COLUMN_NAME_DESCRIPTION));
               String finish = cursor.getString(cursor.getColumnIndexOrThrow(EventsMaster.Tasks.COLUMN_NAME_FINISHED));

               ids.add(id);
               taskNames.add(taskName);
               dates.add(date);
               times.add(time);
               descriptions.add(description);
               finishes.add(finish);


           }while(cursor.moveToNext());

        }

       // cursor.close();
       // Log.i(TAG,"readInfo:" + taskNames);

        // return userNames;

        //String req;
        //if(req == "taskName") {
            return taskNames;
       // } else if (req == "password") {

           // return passwords;
       // }else{
           // return null;
      //  }



    }*/


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

       /* String DROP_TABLE_QUERY = "DROP TABLE IF EXISTStackstasks" + EventsMaster.Tasks.TABLE_NAME;
        //Drop older table if exist
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY);
        //create table again
        onCreate(sqLiteDatabase);*/

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
          sqLiteDatabase.delete(TABLE_NAME,COLUMN_NAME_ID+" =?",new String[]{String.valueOf(id)});

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
       Cursor cursor =  sqLiteDatabase.query(TABLE_NAME, new String[] {COLUMN_NAME_ID,COLUMN_NAME_TASK_NAME,COLUMN_NAME_DATE,COLUMN_NAME_TIME,COLUMN_NAME_DESCRIPTION,COLUMN_NAME_FINISHED} ,COLUMN_NAME_ID + " =?",new String[]{String.valueOf(id)},null , null,null );

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

        values.put(COLUMN_NAME_TASK_NAME, task.getTaskName());
        values.put(COLUMN_NAME_DATE, task.getDate());
        values.put(COLUMN_NAME_TIME, task.getTime() );
        values.put(COLUMN_NAME_DESCRIPTION, task.getDescription());
        values.put(COLUMN_NAME_FINISHED,  task.getFinished());

        int status = sqLiteDatabase.update(TABLE_NAME,values,COLUMN_NAME_ID+ " =?",new String[]{String.valueOf(task.getId())});

        sqLiteDatabase.close();

        return status;



    }

    }


