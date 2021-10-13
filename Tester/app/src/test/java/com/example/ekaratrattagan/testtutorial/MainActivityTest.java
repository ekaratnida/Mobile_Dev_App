package com.example.ekaratrattagan.testtutorial;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowView;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void clickingButton_shouldChangeResultsViewText() throws Exception {

        Activity activity = Robolectric.setupActivity(MainActivity.class);

        Button button = (Button) activity.findViewById(R.id.button);
        TextView tv = (TextView)activity.findViewById(R.id.textView);

        button.performClick();
        assertThat(tv.getText().toString(), equalTo("hello"));
    }
}
