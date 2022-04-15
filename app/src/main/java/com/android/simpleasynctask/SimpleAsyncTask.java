package com.android.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {
    private WeakReference<TextView> mTextView;
    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }
    @Override
    protected String doInBackground(Void... voids) {
        int s = 60000;
        // Sleep for 1 minutes
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Return a String result
        return "Awake at last after sleeping for " + s + " seconds!";
    }
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }

}
