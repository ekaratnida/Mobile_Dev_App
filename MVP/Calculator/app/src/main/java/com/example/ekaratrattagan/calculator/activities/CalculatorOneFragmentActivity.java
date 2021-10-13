package com.example.ekaratrattagan.calculator.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.ekaratrattagan.calculator.R;
import com.example.ekaratrattagan.calculator.mvp.PresenterManager;
import com.example.ekaratrattagan.calculator.mvp.view.ICalcView;


public class CalculatorOneFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_static);

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        //bind to the corresponding presenter
        PresenterManager.getInstance().initCalcPresenter((ICalcView)fragment);
    }
}
