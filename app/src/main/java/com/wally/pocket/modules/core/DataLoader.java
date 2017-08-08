package com.wally.pocket.modules.core;

import android.os.AsyncTask;

/**
 * Created by MAV1GA on 08/08/2017.
 */

public class DataLoader extends AsyncTask<Void, Void, Void> {

    private LoadingDataListener callback;

    public DataLoader(LoadingDataListener callback){
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        callback.startLoading();

    }

    @Override
    protected Void doInBackground(Void... params) {
        callback.onLoading();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        callback.onLoadingDone();
    }
}
