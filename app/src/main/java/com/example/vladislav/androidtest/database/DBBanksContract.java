package com.example.vladislav.androidtest.database;

import android.provider.BaseColumns;

/**
 * Created by vladislav on 14.03.17.
 */

public final class DBBanksContract {

    private DBBanksContract() {}

    public static class BankEntry implements BaseColumns {
        public static final String TABLE_NAME = "BANKS";
        public static final String ADDRESS = "address";
        public static final String DISTANCE = "distance";
        public static final String NAME = "name";
        public static final String ESTIMATIONMARK = "estimationmark";
        public static final String PHONENUMBER = "phonenumber";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + BankEntry.TABLE_NAME + " (" +
                    BankEntry._ID + " INTEGER PRIMARY KEY," +
                    BankEntry.ADDRESS + " TEXT," +
                    BankEntry.DISTANCE + " TEXT," +
                    BankEntry.NAME + " TEXT," +
                    BankEntry.ESTIMATIONMARK + " TEXT," +
                    BankEntry.PHONENUMBER + " TEXT)";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + BankEntry.TABLE_NAME;

}