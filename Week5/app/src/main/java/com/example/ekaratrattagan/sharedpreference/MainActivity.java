package com.example.ekaratrattagan.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    EditText playerText;
    Button  button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv1);
        editText = findViewById(R.id.editText);
        playerText = findViewById(R.id.editText2);
        button = findViewById(R.id.button);

        final SharedPreferences sharePref = getApplicationContext().getSharedPreferences(
                "myShare", Context.MODE_PRIVATE
        );

        //กด save
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputScore = editText.getText().toString();
                String playerName = playerText.getText().toString();

                SharedPreferences.Editor editor =  sharePref.edit();
                editor.putInt("score",Integer.parseInt(inputScore));
                editor.putString("player",playerName);
                editor.commit();

            }
        });

        textView.setText("Mr."+sharePref.getString("player","no_one")+" get "+sharePref.getInt("score",-1));

    }
}
