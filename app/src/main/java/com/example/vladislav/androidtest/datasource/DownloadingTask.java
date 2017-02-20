package com.example.vladislav.androidtest.datasource;

import android.os.AsyncTask;

import com.example.vladislav.androidtest.beans.BankDetails;
import com.example.vladislav.androidtest.datasource.BanksDetailsOperating;

import java.util.List;

/**
 * Created by vladislav on 14.02.17.
 */
public class DownloadingTask extends AsyncTask<Void, Void, List<BankDetails>> {

    BanksDataSourceCallbacks mCallbacks;

    public DownloadingTask(BanksDataSourceCallbacks mCallbacks) {
        this.mCallbacks = mCallbacks;
    }

    // Doing something before running a task.
    @Override
    protected void onPreExecute() {
    }

    // Executing the task itself. In this case we get the list of a bank offices.
    @Override
    protected List<BankDetails> doInBackground(Void... params) {
        return new BanksDetailsOperating().getmBanksDetails();
    }

    // Doing something after running a task.
    @Override
    protected void onPostExecute(List<BankDetails> result) {
        super.onPostExecute(result);
        mCallbacks.onDownloadComplete(result);
    }

    // Interface for callbacks
    public interface BanksDataSourceCallbacks {
        void onDownloadComplete(List<BankDetails> list);
    }
}
