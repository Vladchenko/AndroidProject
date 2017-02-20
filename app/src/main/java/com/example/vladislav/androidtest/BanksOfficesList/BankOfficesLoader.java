package com.example.vladislav.androidtest.BanksOfficesList;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.vladislav.androidtest.datasource.BanksDetailsOperating;
import com.example.vladislav.androidtest.beans.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 15.02.17.
 */

public class BankOfficesLoader extends AsyncTaskLoader<List<BankDetails>> {

    List<BankDetails> mList;

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
        if (null != mList) {
            deliverResult(mList);
        } else {
            forceLoad();
        }
    }

    @Override
    public List<BankDetails> loadInBackground() {
        return new BanksDetailsOperating().getmBanksDetails();
    }

}
