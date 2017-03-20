package com.example.vladislav.androidtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.Consts;

/**
 * Created by vladislav on 14.03.17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Banks.db";
    public static final int DATABASE_VERSION = 1;

    private static DBHelper instance;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

//    private String CREATE_TABLE_BANKS = "CREATE TABLE " + DATABASE_TABLE + "("
//            + DBBanksContract.BankEntry.ID + " INTEGER PRIMARY KEY," // AUTOINCREMENT
//            + DBBanksContract.BankEntry.ADDRESS + " TEXT, "
//            + DBBanksContract.BankEntry.DISTANCE + " TEXT, "
//            + DBBanksContract.BankEntry.NAME + " TEXT, "
//            + DBBanksContract.BankEntry.ESTIMATIONMARK + " TEXT, "
//            + DBBanksContract.BankEntry.PHONENUMBER + " TEXT )";

    public static void createInstance(Context context) {
        // Use in case of needing to recreate a database.
//        dropDataBase(context);
        if (instance == null) {
            instance = new DBHelper(context);
        }
    }

    public static void dropDataBase(Context context) {
        mSharedPreferences = context.getSharedPreferences(
                Consts.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        System.out.println("Removing a database");
        context.deleteDatabase(DATABASE_NAME);
        System.out.println("Database has been dropped");
        mEditor.putBoolean(Consts.DATABASE_ALREADY_POPULATED, false);
        mEditor.commit();
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            throw new IllegalStateException("createInstance() should be called before");
        }
        return instance;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Attempting to create DB Banks.");
        db.execSQL(DBBanksContract.SQL_CREATE_TABLE);
        System.out.println("DB Banks table has been created.");
    }

    public static BankDetails cursorToBankDetails(Cursor cursor) {
        BankDetails bankDetails = new BankDetails();
        bankDetails.setmAddress(cursor.getString(1));
        bankDetails.setmDistance(cursor.getString(2));
        bankDetails.setmName(cursor.getString(3));
        bankDetails.setmEstimationMark(Integer.parseInt(cursor.getString(4)));
        bankDetails.setmPhoneNumber(cursor.getString(5));
        return bankDetails;
    }

//    private ContentValues setBankDetailsValues(int ID, String address, String distance, String name,
//                                               String estimationMark, String phoneNumber) {
//        ContentValues values = new ContentValues();
//        values.put(DBBanksContract.BankEntry._ID, ID);
//        values.put(DBBanksContract.BankEntry.ADDRESS, address);
//        values.put(DBBanksContract.BankEntry.DISTANCE, distance);
//        values.put(DBBanksContract.BankEntry.NAME, name);
//        values.put(DBBanksContract.BankEntry.ESTIMATIONMARK, estimationMark);
//        values.put(DBBanksContract.BankEntry.PHONENUMBER, phoneNumber);
//        return values;
//    }

    public ContentValues setBankDetailsValues(BankDetails bankDetails) {
        ContentValues values = new ContentValues();
        values.put(DBBanksContract.BankEntry._ID, bankDetails.getmID());
        values.put(DBBanksContract.BankEntry.ADDRESS, bankDetails.getmAddress());
        values.put(DBBanksContract.BankEntry.DISTANCE, bankDetails.getmDistance());
        values.put(DBBanksContract.BankEntry.NAME, bankDetails.getmName());
        values.put(DBBanksContract.BankEntry.ESTIMATIONMARK, bankDetails.getmEstimationMark());
        values.put(DBBanksContract.BankEntry.PHONENUMBER, bankDetails.getmPhoneNumber());
        return values;
    }

}
