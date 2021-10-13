package com.example.ekaratrattagan.week5_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button myBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBtn = findViewById(R.id.button);

        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Explicit
                Intent myIntent = new Intent(getApplicationContext(),Activity_B.class);
                myIntent.putExtra("item",5);


                //Implicit 1
                //Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tni.ac.th"));

                //Implicit 2
                //String geoCode = "geo:0,0?q=TNI+ พัฒนาการ";
                //Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
                //startActivity(myIntent);

                //Implicit 3
                //String geoCode = "google.streetview:cbll=13.7382872,100.6261643&cbp=0,30,0,0,-15";
                //Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));


                startActivity(myIntent);

            }
        });
    }
}
