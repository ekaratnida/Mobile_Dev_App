package com.example.ekaratrattagan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //1. Declare Views
    TextView tv;
    Button btn;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2. Find Views' id
        tv = (TextView)findViewById(R.id.tv1);
        btn = (Button)findViewById(R.id.button);
        edt = (EditText)findViewById(R.id.editText);

        //3. Event
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputText =  edt.getText().toString();

                tv.setText(inputText);

            }
        });

    }
}

//Lab1: Calculator
// 0,1,2,3,4,5,6,7,8,9 (+,-,*,/,%), clear ----> Button
// Show results                    ----> EditText
















