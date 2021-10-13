package com.example.ekaratrattagan.week12_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    ArrayList<HashMap<String, String>> contactList;

   TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //tv = (TextView)findViewById(R.id.textView);
        //Client c = new Client();
        //c.tv = tv;
        //c.execute();

        lv = findViewById(R.id.list);
        contactList = new ArrayList<HashMap<String, String>>();
        new GetContacts(this, lv, contactList).execute();

    }
}
