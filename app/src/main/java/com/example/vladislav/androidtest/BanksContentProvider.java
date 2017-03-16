package com.example.vladislav.androidtest;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.vladislav.androidtest.database.DBBanksContract;
import com.example.vladislav.androidtest.database.DBHelper;

public class BanksContentProvider extends ContentProvider {

    DBHelper dbHelper;

    UriMatcher uriMatcher = new UriMatcher(0);

    @Override
    public boolean onCreate() {
        dbHelper = DBHelper.getInstance();
        uriMatcher.addURI("com.example.testproject/banks", "banks", 0);
        uriMatcher.addURI("com.example.testproject/banks", "workhour", 0);
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // content://com.example.testproject/banks
        // content://com.example.testproject/workhour
        switch (uriMatcher.match(uri)) {
            case 0:
                return dbHelper.getReadableDatabase().query(DBBanksContract.BankEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            case 1:
                // TODO
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
