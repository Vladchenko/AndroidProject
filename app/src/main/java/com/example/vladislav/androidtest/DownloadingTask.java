package com.example.vladislav.androidtest;

import android.os.AsyncTask;

import com.example.vladislav.androidtest.entities.BankDetails;

import java.util.List;

/**
 * Created by vladislav on 14.02.17.
 */
public class DownloadingTask extends AsyncTask<Void, Void, List<BankDetails>> {

    BanksDataSourceCallbacks callbacks;

    public DownloadingTask(BanksDataSourceCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected List<BankDetails> doInBackground(Void... params) {
        return new BanksDetailsOperating().getBanksDetails();
    }

    @Override
    protected void onPostExecute(List<BankDetails> result) {
        super.onPostExecute(result);
        callbacks.onDownloadComplete(result);
    }



    public interface BanksDataSourceCallbacks {
        void onDownloadComplete(List<BankDetails> list);
    }
}
