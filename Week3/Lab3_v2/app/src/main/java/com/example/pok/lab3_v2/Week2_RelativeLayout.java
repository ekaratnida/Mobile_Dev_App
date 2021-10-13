package com.example.pok.lab3_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Week2_RelativeLayout extends AppCompatActivity {

    ImageButton imgBtn;
    EditText nameEdt;
    CheckBox manuChk, livChk;
    String result = "";
    String chkValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week2__relative_layout);

        imgBtn = (ImageButton)findViewById(R.id.imageButton2);
        nameEdt = (EditText)findViewById(R.id.editText6);
        manuChk = (CheckBox)findViewById(R.id.checkBox1);
        livChk = (CheckBox)findViewById(R.id.checkBox2);

        manuChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              chkValue = ((CheckBox)view).getText().toString();

            }
        });




        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result += nameEdt.getText().toString() + " like "+chkValue;
                Toast.makeText(
                        getApplicationContext(),
                        result,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
