package itec1313.mut.it.quiz1;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import static itec1313.mut.it.quiz1.MainActivity.myPref;

public class Util {

    public static SharedPreferences shared;

    public static void init(Context context){
        shared = context.getSharedPreferences(MainActivity.myPref, Context.MODE_PRIVATE);
    }

    public static String validatedString(String s){
        //String s must not be "" and empty and must be integer
        if(!s.equals("") && !s.isEmpty() && isInteger(s))
            return s;
        else
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

        if( newScore <= currentHighScore ){
            ret = 0;
        }else{
            ret = newScore;
            SharedPreferences.Editor editor = shared.edit();
            editor.putInt(MainActivity.value,ret);
            editor.commit();
        }

        return ret;

    }

    public static int getCurrentHighScore() {

    		int i = shared.getInt(MainActivity.value,-1);
    		return i;
    }
}
