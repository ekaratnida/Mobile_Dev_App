package com.example.pok.lab3_v2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Week1_simple_app extends AppCompatActivity {

    EditText input1Edt;
    EditText input2Edt;
    Button sumBtn;
    TextView resultTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week1_simple_app);

        input1Edt = findViewById(R.id.input1Edt);
        input2Edt = findViewById(R.id.input2Edt);
        sumBtn = findViewById(R.id.sumBtn);
        resultTxt = findViewById(R.id.resultTxt);

        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input1Str = input1Edt.getText().toString();
                String input2Str = input2Edt.getText().toString();

                int resultInt = CalculateUtil.sumTwoNumber(Integer.parseInt(input1Str), Integer.parseInt(input2Str));
                resultTxt.setText(Integer.toString(resultInt));
            }
        });
    }



}
