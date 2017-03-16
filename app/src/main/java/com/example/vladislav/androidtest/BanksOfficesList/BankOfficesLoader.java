package com.example.vladislav.androidtest.BanksOfficesList;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import com.example.vladislav.androidtest.database.DBHelper;
import com.example.vladislav.androidtest.datasource.BanksDetailsOperating;
import com.example.vladislav.androidtest.beans.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 15.02.17.
 */

public class BankOfficesLoader extends AsyncTaskLoader<List<BankDetails>> {

    private List<BankDetails> mList;
//    private DBHelper mDbHelper;
//    private Cursor mCursor = null;
    private BankDetails mBankDetails;
//    private List<BankDetails> mBankDetailsList;

    public BankOfficesLoader(Context context) {
        super(context);
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
        if (null != mList) {
            deliverResult(mList);
        } else {
            forceLoad();
        }
    }

    @Override
    public List<BankDetails> loadInBackground() {

//        if (Consts.GET_DATA_FROM_DB) {
//            mBankDetailsList = new ArrayList<>();
//            // This is the part where we download a bank detailed info from a database.
//            mDbHelper = DBHelper.getInstance();
//            mCursor = mDbHelper.getReadableDatabase().query(
//                    false,
//                    DBHelper.DATABASE_TABLE,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null,
//                    null
//            );
//            mCursor.moveToFirst();
//            int i = 0;
//            try {
//                while (i != mCursor.getCount()) {
//                    mBankDetailsList.add(mDbHelper.cursorToBankDetails(mCursor));
//                    mCursor.moveToNext();
//                    i++;
//                }
//            } finally {
//                mCursor.close();
//            }
//            mList = mBankDetailsList;
//        } else {
            mList = new BanksDetailsOperating(getContext()).getmBanksDetailsList();
//        }
        return mList;
    }

}
