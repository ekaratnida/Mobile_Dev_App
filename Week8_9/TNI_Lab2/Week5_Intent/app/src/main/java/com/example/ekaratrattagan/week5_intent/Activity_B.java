package com.example.ekaratrattagan.week5_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity_B extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__b);

        tv = findViewById(R.id.textView2);

        Intent myIntent = getIntent();
        int item = myIntent.getIntExtra("item",0);

        tv.setText("item = "+item);
    }
}
