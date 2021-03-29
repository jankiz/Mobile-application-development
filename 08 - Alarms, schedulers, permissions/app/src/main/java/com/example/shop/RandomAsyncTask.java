package com.example.shop;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class RandomAsyncTask extends AsyncTask<Void,Void, String> {

    private WeakReference<TextView> mTextView;

    RandomAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    /**
     * Runs on the background thread.
     *
     * @param voids No parameters in this use case.
     * @return Returns the string including the amount of time that
     * the background thread slept.
     */
    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);
        int ms = n * 300;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Bejelentekezés vendégként " + ms + " ms után!";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
