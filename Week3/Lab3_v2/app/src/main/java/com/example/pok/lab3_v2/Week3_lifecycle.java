package com.example.pok.lab3_v2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Week3_lifecycle extends AppCompatActivity {

    private static final String[] CLUBS =
            {"Arsenal", "Chelsea", "Liverpool", "Man City", "Man Utd"};

    String msg1 = "Lab3";
    String msg2 = "Activity A ";
    Button button1;

    //สร้างตัวแปร, set ค่าตัวแปร ใน oncreate แล้วลองทดลอง onstop (home), ondestroy (back)
    // int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week3_lifecycle);

        //x = 1;
        Log.d(msg1, msg2 + "onCreate");

        button1 = findViewById(R.id.button9);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),Week3_Activity2.class);

                startActivity(myIntent);
                overridePendingTransition(R.anim.enter, R.anim.exit);


                /* AlertDialog.Builder builder =
                        new AlertDialog.Builder(Week3_lifecycle.this);
                builder.setTitle("Select Favorite Team");
                builder.setItems(CLUBS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selected = CLUBS[which];
                        Toast.makeText(getApplicationContext(), "คุณชอบ " +
                                selected, Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("ไม่ชอบซักทีม", null);
                builder.create();


                builder.show();*/
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg1, msg2 + "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg1, msg2 + "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg1, msg2 + "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg1, msg2 + "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(msg1, msg2 + "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(msg1, msg2 + "onRestart");
    }
}
