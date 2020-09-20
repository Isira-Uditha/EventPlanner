package com.example.eventplanner.Database;

import android.provider.BaseColumns;

public class EventsMaster {
    private EventsMaster(){

    }

    public static class Budgets implements BaseColumns{
        public static final String TABLE_NAME="budget";
        public static final String COLUMN_NAME_EBNAME="bname";
        public static final String COLUMN_NAME_EBPADIDA="bpadia";
        public static final String COLUMN_NAME_EBAMOUNT="bamount";
        public static final String COLUMN_NAME_EBNOTE="bnote";
    }
}
