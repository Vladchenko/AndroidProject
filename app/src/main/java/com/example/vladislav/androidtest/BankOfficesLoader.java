package com.example.vladislav.androidtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.example.vladislav.androidtest.entities.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 15.02.17.
 */

public class BankOfficesLoader extends AsyncTaskLoader<List<BankDetails>> {

    List<BankDetails> list;

    public BankOfficesLoader(Context context) {
        super(context);
    }

    @Override
    public List<BankDetails> loadInBackground() {
        return new BanksDetailsOperating().getBanksDetails();
    }

//    @Override
//    public void deliverResult(List<BankDetails> data) {
//        super.deliverResult(data);
//    }

    /**
     * в этом onStartLoading() проверяешь, есть ли у тебя старый результат(просто хранишь список
     * с твоими сущностями отделений банков и проверяешь на null). Если старый результат есть, то
     * вызываешь deliverResult(bankOfficeList). Иначе вызываешь forceLoad()
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (null != list) {
            deliverResult(list);
        } else {
            forceLoad();
        }
    }
}
