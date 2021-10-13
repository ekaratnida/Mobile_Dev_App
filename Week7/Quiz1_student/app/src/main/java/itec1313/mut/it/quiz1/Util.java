package itec1313.mut.it.quiz1;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import static itec1313.mut.it.quiz1.MainActivity.myPref;

public class Util {

    public static SharedPreferences shared;

    public static void init(Context context){
        //Your code here
    }

    public static String validatedString(String s){
        //String s must not be "", empty and integer

        //Your code here
        return "-1";

    }

    public static boolean isInteger( String input ) {
        try
        {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e )
        {
            return false;
        }
    }

    public static int updateScore(int newScore, int currentHighScore) {

        //get the current score from preference
        //compare the new and current score
        // - if the new score is less than the current one,
        //          show "No new high score"
        // - else
        //          save new score to preference
        //          show "High score = " + newScore

        int ret = -1;

//        Your code here

        return ret;

    }

    public static int getCurrentHighScore() {
            //Hint get current high score
    		//Your code here
    		return -1;
    }
}
