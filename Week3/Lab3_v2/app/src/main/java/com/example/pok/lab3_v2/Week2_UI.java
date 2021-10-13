package com.example.pok.lab3_v2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Week2_UI extends AppCompatActivity {

    CheckBox ch1, ch2;
    RadioButton rdb1, rdb2;
    Spinner teamSpinner;
    NumberPicker pickers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week2__ui);

        ch1 = findViewById(R.id.checkBox1);
        ch2 = findViewById(R.id.checkBox2);

        rdb1 = findViewById(R.id.radioButton1);
        rdb2 = findViewById(R.id.radioButton2);

        teamSpinner = findViewById(R.id.team);

        final String[] teamList = getResources().getStringArray(R.array.team);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, teamList);
        teamSpinner.setAdapter(adapter);

        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Week2_UI.this, "Select : " + teamList[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rdb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRadioButtonCheck(view);
            }
        });

        rdb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRadioButtonCheck(view);
            }
        });

        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the view now checked?    
                boolean checked = ((CheckBox) view).isChecked();
                Toast.makeText(getApplicationContext(), ((CheckBox) view).getText() + " is " + checked, Toast.LENGTH_SHORT).show();
            }
        });

        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Is the view now checked?    
                boolean checked = ((CheckBox) view).isChecked();
                Toast.makeText(getApplicationContext(), view.getId() + " is " + checked, Toast.LENGTH_SHORT).show();
            }
        });


        pickers = (NumberPicker) findViewById(R.id.numberPicker);

        final String[] arrayPicker = new String[]{"Red", "Blue", "Green", "Yellow", "Gray"};

        //set min value zero
        pickers.setMinValue(0);

        //set max value from length array string reduced 1
        pickers.setMaxValue(arrayPicker.length - 1);

        //implement array string to number picker
        pickers.setDisplayedValues(arrayPicker);

        //set wrap true or false, try it you will know the difference
        pickers.setWrapSelectorWheel(true);

        pickers.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                //result.setText(arrayPicker[picker.getValue()]);
                String color = arrayPicker[picker.getValue()];
                Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();

            }
        });
    }

    void testRadioButtonCheck(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        String result = "";
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton1:
                if (checked)
                    result = "rd 1";
                break;
            case R.id.radioButton2:
                if (checked)
                    result = "rd 2";
                break;
        }

        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
}
