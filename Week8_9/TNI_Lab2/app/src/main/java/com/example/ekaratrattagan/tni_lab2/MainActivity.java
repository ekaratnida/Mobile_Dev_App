package com.example.ekaratrattagan.tni_lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //Step1: Declare
    RadioButton rd1;
    RadioButton rd2;
    ToggleButton toggleButton;
    Switch aSwitch;
    NumberPicker np;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step2: Find
        rd1 = findViewById(R.id.radioButton1);
        rd2 = findViewById(R.id.radioButton2);
        toggleButton = findViewById(R.id.toggleButton);
        aSwitch = findViewById(R.id.switch1);
        np = findViewById(R.id.numberPicker);


        final String[] arrayPickers = new String[]{"Red","Green","Blue","Yellow","Gray"};
        np.setMinValue(0);
        np.setMaxValue(arrayPickers.length-1);
        np.setDisplayedValues(arrayPickers);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                String color = arrayPickers[numberPicker.getValue()];
                Toast.makeText(getApplicationContext(),color,Toast.LENGTH_SHORT).show();
            }
        });


        //Step3: Set Event
        rd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRadioButtonChecked(view);
            }
        });

        rd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRadioButtonChecked(view);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //Enable
                    Toast.makeText(getApplicationContext(),"ON...",Toast.LENGTH_SHORT).show();
                }else{
                    //Disable
                    Toast.makeText(getApplicationContext(),"OFF...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void testRadioButtonChecked(View view){

        boolean isChecked = ((RadioButton)view).isChecked();

        String result = "";

        switch (view.getId()){
            case R.id.radioButton1:
                if(isChecked){
                    result = "I picked rd1";
                    break;
                }
            case R.id.radioButton2:
                if(isChecked){
                    result = "I picked rd2";
                    break;
                }
        }

        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

    }
}


//bo.komchan_st@tni.ac.th , tr.waraporn_st@tni.ac.th