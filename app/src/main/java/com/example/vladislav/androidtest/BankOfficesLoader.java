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

    public BankOfficesLoader(Context context) {
        super(context);
    }

    @Override
    public List<BankDetails> loadInBackground() {
        return new BanksDetailsOperating().getBanksDetails();
    }
}
