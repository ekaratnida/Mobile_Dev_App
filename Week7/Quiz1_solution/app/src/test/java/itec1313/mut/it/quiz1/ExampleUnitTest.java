package itec1313.mut.it.quiz1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void validateString() {
        assertEquals("-1",Util.validatedString(""));
    }

    @Test
    public void updateScore() {

        //String output1 = "Your score is not high enough";
        //String output2 = "Your score = ";

        int currentScore = 6;
        int newScore = 5;
        assertEquals(0,Util.updateScore(newScore,currentScore));

        currentScore = 6;
        newScore = 7;

        int result = Util.updateScore(newScore,currentScore);
        //output2 += result;
        assertEquals(7,result);

    }
}