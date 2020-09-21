package com.example.eventplanner.Database;

        import android.provider.BaseColumns;

public class EventsMaster {
    public EventsMaster() {
    }

    public static class Events implements BaseColumns {
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



    }

}
