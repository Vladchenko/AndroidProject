package com.example.vladislav.androidtest.datasource;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.example.vladislav.androidtest.Consts;
import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 05.02.17.
 */

public class BanksDetailsOperating {

    private boolean mDBPopulated;
    private int mBanksNumberDefault = 10;
    //    private int mBanksNumber = 10;
    private int mTemp;
    private List<BankDetails> mBanksDetailsList;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private DBHelper mDbHelper;
    private Cursor mCursor = null;

    public BanksDetailsOperating(Context context) {

        mSharedPreferences = context.getSharedPreferences(
                Consts.SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);

        // Checking if a database is populated.
        mDBPopulated = mSharedPreferences.getBoolean(Consts.DATABASE_ALREADY_POPULATED, false);

        // When willing to get data from database:
        if (Consts.GET_DATA_FROM_DB) {
            System.out.println("Using database to work with banks data.");
            // If database is not populated with a banks data.
            if (!mDBPopulated) {
                // Populating а table in a data base.
                mDbHelper = DBHelper.getInstance();
                mBanksDetailsList = createBanksDetailsAtRandom();
                try {
                    for (int i = 0; i < mBanksNumberDefault; i++) {
                        mDbHelper.getWritableDatabase().insert(Consts.DATABASE_TABLE, null,
                                mDbHelper.setBankDetailsValues(mBanksDetailsList.get(i)));
                    }
                    System.out.println("Database's Banks table has been populated");
                } finally {
                    // Putting to a file a boolean flag saying that database is populated.
                    mEditor = mSharedPreferences.edit();
                    mEditor.putBoolean(Consts.DATABASE_ALREADY_POPULATED, true);
                    mEditor.commit();
                    System.out.println("Database's Banks table already exists and its data also.");
                }
            } else {
                // Reading a table from a database.
                System.out.println("Database's Banks table contents present, reading it.");
                mBanksDetailsList = new ArrayList<>();
                // This is the part where we download a banks detailed info from a database.
                mDbHelper = DBHelper.getInstance();
                mCursor = mDbHelper.getReadableDatabase().query(
                        false,
                        Consts.DATABASE_TABLE,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
                mCursor.moveToFirst();
                int i = 0;
                try {
                    while (i != mCursor.getCount()) {
                        mBanksDetailsList.add(mDbHelper.cursorToBankDetails(mCursor));
                        mCursor.moveToNext();
                        i++;
                    }
                } finally {
                    mCursor.close();
                }
                System.out.println("Database's Banks table's contents has been read to a mBanksDetailsList.");
            }
            // When downloading from DB is not used, one populates banks details at random:
        } else {
            System.out.println("Getting banks data at random.");
            mBanksDetailsList = createBanksDetailsAtRandom();
        }

    }

    private List<BankDetails> createBanksDetailsAtRandom() {

        List<BankDetails> banksDetails = new ArrayList<>();
        for (int i = 0; i < mBanksNumberDefault; i++) {

            BankDetails mbank = new BankDetails();
            mbank.setmID(i);
            mbank.setmEstimationMark(mSharedPreferences.getInt(Consts.ESTIMATION_MARK + mbank.getmID(), -1));
            mbank.setmAddress("Тукая, " + (int) (Math.random() * 100));
            mbank.setmLatitude((int) (Math.random() * 100) + "." + (int) (Math.random() * 10000));
            mbank.setmLongtitude((int) (Math.random() * 100) + "." + (int) (Math.random() * 10000));
            mbank.setmDistance((int) (Math.random() * 20) + "." + (int) (Math.random() * 100) + " км.");

            switch (i) {
                case 0: {
                    mbank.setmName("Банк Авангард");
                    break;
                }
                case 1: {
                    mbank.setmName("АкБарс Банк");
                    break;
                }
                case 2: {
                    mbank.setmName("СберБанк");
                    break;
                }
                case 3: {
                    mbank.setmName("Совком Банк");
                    break;
                }
                case 4: {
                    mbank.setmName("Райффайзен Банк");
                    break;
                }
                case 5: {
                    mbank.setmName("Аки Банк");
                    break;
                }
                case 6: {
                    mbank.setmName("ВТБ 24");
                    break;
                }
                case 7: {
                    mbank.setmName("Газпром Банк");
                    break;
                }
                case 8: {
                    mbank.setmName("Альфа Банк");
                    break;
                }
                case 9: {
                    mbank.setmName("Промсвязь Банк");
                    break;
                }
            }

            mbank.setmPhoneNumber("+7 (" + (int) (Math.random() * 10000) + ") "
                    + (int) (Math.random() * 1000)
                    + "-"
                    + (int) (Math.random() * 100)
                    + "-"
                    + (int) (Math.random() * 100));
            mbank.setmQualityControl("Прекрасно");
            int extraHours = (int) (Math.random() * 4);
            mbank.setmWorkingHours(extraHours + 7 + ".00-" + (extraHours + 7 + 9) + ".00");
            banksDetails.add(mbank);
        }

        return banksDetails;
    }

    public void printBanksDetailsByDefault() {
        for (int i = 0; i < mBanksNumberDefault; i++) {
            BankDetails mbank = mBanksDetailsList.get(i);
            System.out.println("Address is: " + mbank.getmAddress());
            System.out.println("Latitude is: " + mbank.getmLatitude());
            System.out.println("Longitude is: " + mbank.getmLongtitude());
            System.out.println("Name is: " + mbank.getmName());
            System.out.println("Phone number is: " + mbank.getmPhoneNumber());
            System.out.println("Quality control is: " + mbank.getmQualityControl());
            System.out.println("Work mode is: " + mbank.getmWorkingHours());
            System.out.println("Distance is: " + mbank.getmDistance());
            System.out.println();
        }

    }

    public List<BankDetails> getmBanksDetailsList() {
        return mBanksDetailsList;
    }

    public void setmBanksDetailsList(List<BankDetails> mBanksDetailsList) {
        this.mBanksDetailsList = mBanksDetailsList;
    }

}
