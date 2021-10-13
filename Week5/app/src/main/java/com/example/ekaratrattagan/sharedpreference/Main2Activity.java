package com.example.ekaratrattagan.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    //Step1
    Button btnLogin;
    EditText edtUserName, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Step2
        btnLogin = findViewById(R.id.btnLogin);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);


        final SharedPreferences sh = getApplicationContext().
                getSharedPreferences("myData", Context.MODE_PRIVATE);

        final SharedPreferences.Editor editor = sh.edit();
        //Register


        //Step3
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String inputUserName = edtUserName.getText().toString();
                String inputPassWord = edtPassword.getText().toString();

                String getUserName = sh.getString("username","not found");
                String getPassword = sh.getString("password","not found");

                if(inputUserName.equals(getUserName) && inputPassWord.equals(getPassword)){
                    //Go to Main UI
                    //Intent
                    //startActivity
                    //overridePendingTransition(enter, exit)
                } else {
                    //Toast Not pass
                    //Register button
                } */


                String inputUserName = edtUserName.getText().toString();
                String inputPassWord = edtPassword.getText().toString();
                String inputConfirmPassword = edtConfirmPassword.getText().toString();

                if(inputPassWord.equals(inputConfirmPassword)){

                    //save
                    editor.putString("username",inputUserName);
                    editor.putString("password",inputPassWord);
                    editor.commit();

                    //Go to Main UI
                    //Intent
                    //StartActivity
                    //overridePending...(enter,exit)

                } else {
                    //Toast
                    //Pass != Confirm not match.
                }

            }
        });

    }
}


































