package com.example.pok.mythread;

import android.os.AsyncTask;
import android.widget.TextView;

public class MyTask2 extends AsyncTask {

    public TextView tv;

    @Override
    protected Object doInBackground(Object[] objects) {

        for (int i = 1; i <= 10; i++) {
            //print i
            publishProgress(i);
            //delay
            doSleepFor(1000);
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);

        String result = values[0].toString();
        //setText
        tv.setText(result);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        tv.setText("success.");
    }

    private void doSleepFor(int delayTime) {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
