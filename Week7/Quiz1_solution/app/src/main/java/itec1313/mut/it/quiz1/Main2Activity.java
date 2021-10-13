package itec1313.mut.it.quiz1;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    SharedPreferences shared;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView)findViewById(R.id.textView);

        shared = getApplicationContext().getSharedPreferences(MainActivity.myPref, Context.MODE_PRIVATE);
        int yourScore = shared.getInt(MainActivity.value,-1);

        textView.setText("Your score is "+String.valueOf(yourScore));
    }
}
