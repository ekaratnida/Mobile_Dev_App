package com.example.pok.mythread;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by pok on 10/3/2561.
 * V.2 by pok on 18/8/2562
 */

public class MyTask extends AsyncTask <String, Integer,String> {

    TextView tv1;

    //Contructor
    MyTask(TextView tv){
        tv1 = tv;
    }

    @Override
    protected void onPreExecute() { }

    //Implement progressbar by using AsyncTask
    @Override
    protected String doInBackground(String... params) {
        String data1 = params[0];
        String data2 = params[1];
        String data3 = params[2];
        //String myString = params[1];
        for (int i = 1; i <= 100; i++) {
            publishProgress(i);
            setDelay(1000);
        }
        return "End loop";
    }

    private void setDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        tv1.setText(values[0].toString());
    }

    @Override
    protected void onPostExecute(String s) {
        tv1.setText(s);
    }
}