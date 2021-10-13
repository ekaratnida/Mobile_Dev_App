package com.example.ekaratrattagan.week12_json;

import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Client extends AsyncTask {

    public TextView tv;

    @Override
    protected Object doInBackground(Object[] objects) {

        HttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost("http://10.0.2.2/test.php");

        try {

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            InputStream is = entity.getContent();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(is,"UTF-8"),8);

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        // <uses-permission android:name="android.permission.INTERNET"/>
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
