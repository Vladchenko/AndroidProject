package com.example.vladislav.androidtest.BanksOfficesList;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.CursorLoader;

import com.example.vladislav.androidtest.database.DBHelper;
import com.example.vladislav.androidtest.datasource.BanksDetailsOperating;
import com.example.vladislav.androidtest.beans.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 15.02.17.
 */

public class BankOfficesLoader extends CursorLoader {

    //    private List<BankDetails> mList;
    private Cursor mCursor;
    private DBHelper dbHelper;

    public BankOfficesLoader(Context context) {
        super(context);
        dbHelper = DBHelper.getInstance();
    }

    /**
     * в этом onStartLoading() проверяешь, есть ли у тебя старый результат(просто хранишь список
     * с твоими сущностями отделений банков и проверяешь на null). Если старый результат есть, то
     * вызываешь deliverResult(bankOfficeList). Иначе вызываешь forceLoad()
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        // When on wishes to see the estimation marks right the way when they are given, one has to
        // remove this condition and put only forceLoad(); method.
        if (null != mCursor) {
            deliverResult(mCursor);
        } else {
            forceLoad();
        }
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor =getContext().getContentResolver().query(Uri.parse("content://com.example.vladislav.androidtest/BANKS"), null, null, null, null);
        return cursor;
    }

}
