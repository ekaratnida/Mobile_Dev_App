package itec1313.mut.it.quiz1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText inputEdt;
    Button chkScoreBtn;
    Button nextActBtn;
    Button clearScoreBtn;
    TextView showCurrentScore;

    public static final String myPref = "myPref";
    public static final String value = "highScore";

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEdt = (EditText)findViewById(R.id.editText);
        chkScoreBtn = (Button)findViewById(R.id.button);
        nextActBtn = (Button)findViewById(R.id.button2);
        clearScoreBtn = (Button)findViewById(R.id.button3);
        showCurrentScore = (TextView)findViewById(R.id.textView2);

        shared = getApplicationContext().getSharedPreferences(MainActivity.myPref, Context.MODE_PRIVATE);

        Util.init(getApplicationContext());

        showScoreTxt();

        chkScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String rawStr = inputEdt.getText().toString();
                String validStr = Util.validatedString(rawStr);

                if(!validStr.equals("-1")) {
                    int newScore = convertStringToInt(validStr);
                    int currentScore = Util.getCurrentHighScore();
                    int retStatus = Util.updateScore(newScore,currentScore);
                    showResultByStatus(retStatus);
                }
                else
                {
                    showResult("Please insert score.");
                }

                clearInputScore();
                showScoreTxt();
            }
        });

        nextActBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    goNextActivity();
                    clearInputScore();

            }
        });

        clearScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear current score
                SharedPreferences.Editor editor = shared.edit();
                editor.clear();
                editor.commit();
                clearInputScore();
                showScoreTxt();
            }
        });

    }

    private void showScoreTxt() {
        showCurrentScore.setText("Your score = " + String.valueOf(Util.getCurrentHighScore()));
    }

    private void clearInputScore() {
        inputEdt.getText().clear();
    }

    private int convertStringToInt(String validStr) {
        //convert string to int
        return Integer.parseInt(validStr);
    }

    private void goNextActivity() {

        //If current high score is more than 999,
        //          you can go to next activity (Main2Activity.class)
        //else
        //          show user "Your score is low"

        if(Util.getCurrentHighScore() > 999){
            Intent intent = new Intent(this.getApplicationContext(),Main2Activity.class);
            startActivity(intent);
        } else {
            showResult("Your score is low.");
        }
    }

    private void showResult(String output) {
        Toast.makeText(getApplicationContext(), output, Toast.LENGTH_SHORT).show();
    }

    private void showResultByStatus(int status){


        String output = "";

        if(status == 0){
            output = "Your score is not high enough";
        }else{
            output = "High score = "+status;
        }

        showScoreTxt();
        showResult(output);


    }
}
