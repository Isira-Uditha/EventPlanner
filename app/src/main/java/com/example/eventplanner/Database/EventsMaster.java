package com.example.eventplanner.Database;

import android.provider.BaseColumns;

public class EventsMaster {
//<<<<<<< Kasuni
    public EventsMaster() {
    }

    public static class Tasks implements BaseColumns{

        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TASK_NAME= "taskName";
        public static final String COLUMN_NAME_DATE="date";
        public static final String COLUMN_NAME_TIME= "time";
        public static final String COLUMN_NAME_DESCRIPTION= "description";
        public static final String COLUMN_NAME_FINISHED= "finished";

        public static String getTableName() {
            return TABLE_NAME;
        }

        public static String getColumnNameTaskName() {
            return COLUMN_NAME_TASK_NAME;
        }

        public static String getColumnNameDate() {
            return COLUMN_NAME_DATE;
        }

        public static String getColumnNameTime() {
            return COLUMN_NAME_TIME;
        }

        public static String getColumnNameDescription() {
            return COLUMN_NAME_DESCRIPTION;
        }

        public static String getColumnNameFinished() {
            return COLUMN_NAME_FINISHED;
        }
    }

//=======

    /*public EventsMaster() {
    }*/

    public static class Guest implements BaseColumns{

        public static final String TABLE_NAME = "guest";
        public static final String COLUMN_NAME_GUEST_NAME = "guest_name";
        public static final String COLUMN_NAME_GUEST_GENDER = "gender";
        public static final String COLUMN_NAME_GUEST_AGE= "age";
        public static final String COLUMN_NAME_GUEST_CONTACT = "guest_contact";
        public static final String COLUMN_NAME_GUEST_EMAIL = "guest_email";
        public static final String COLUMN_NAME_GUEST_INVITED = "invited";
        public static final String COLUMN_NAME_GUEST_NOTE = "note";
//>>>>>>> Integration

    }
}
