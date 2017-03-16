package com.example.vladislav.androidtest;

import android.app.Application;

import com.example.vladislav.androidtest.database.DBHelper;

/**
 * Created by vladislav on 14.03.17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DBHelper.createInstance(getApplicationContext());
    }
}
