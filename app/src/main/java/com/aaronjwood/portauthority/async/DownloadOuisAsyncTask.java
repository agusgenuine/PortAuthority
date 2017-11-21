package com.aaronjwood.portauthority.async;

import com.aaronjwood.portauthority.activity.MainActivity;
import com.aaronjwood.portauthority.db.Database;
import com.aaronjwood.portauthority.parser.Parser;
import com.aaronjwood.portauthority.response.MainAsyncResponse;

import java.lang.ref.WeakReference;

public class DownloadOuisAsyncTask extends DownloadAsyncTask {

    private static final String SERVICE = "https://code.wireshark.org/review/gitweb?p=wireshark.git;a=blob_plain;f=manuf";

    public DownloadOuisAsyncTask(Database database, Parser parser, MainAsyncResponse activity) {
        db = database;
        delegate = new WeakReference<>(activity);
        this.parser = parser;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        db.clearOuis();
        doInBackground(SERVICE, parser);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        MainAsyncResponse activity = delegate.get();
        if (activity != null) {
            ((MainActivity) activity).setupMac();
        }
    }

}