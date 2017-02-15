package com.example.vladislav.androidtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.example.vladislav.androidtest.entities.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 15.02.17.
 */

public class BankOfficesLoader extends Loader<List<BankDetails>> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */

    BanksDataSourceCallbacks callbacks;

    public BankOfficesLoader(BanksDataSourceCallbacks callbacks,
                             Context context, Bundle bundle) {
        super(context);
        this.callbacks = callbacks;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();

    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
//        deliverResult(new BanksDetailsOperating().getBanksDetails());
        callbacks.onDownloadComplete(new BanksDetailsOperating().getBanksDetails());
    }

    @Override
    protected void onAbandon() {
        super.onAbandon();

    }

    @Override
    protected void onReset() {
        super.onReset();

    }

//    void getResultFromTask(String result) {
//        deliverResult(result);
//    }



    public interface BanksDataSourceCallbacks {
        void onDownloadComplete(List<BankDetails> list);
    }
}
