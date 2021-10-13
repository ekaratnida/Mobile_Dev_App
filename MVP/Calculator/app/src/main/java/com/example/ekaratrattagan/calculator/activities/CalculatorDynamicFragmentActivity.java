package com.example.ekaratrattagan.calculator.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.example.ekaratrattagan.calculator.R;
import com.example.ekaratrattagan.calculator.fragments.CalculatorFirstLevelFragment;
import com.example.ekaratrattagan.calculator.fragments.CalculatorSecondLevelFragment;
import com.example.ekaratrattagan.calculator.fragments.CalculatorUserFriendly;
import com.example.ekaratrattagan.calculator.mvp.PresenterManager;
import com.example.ekaratrattagan.calculator.mvp.view.ICalcView;


public class CalculatorDynamicFragmentActivity extends BaseActivity implements CalculatorFirstLevelFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_dynamic);

        Fragment newFragment = new CalculatorUserFriendly();

        //bind to the corresponding presenter
        PresenterManager.getInstance().initCalcPresenter((ICalcView)newFragment);

        navigateTo(newFragment);
    }

    public void showResult(double result){
        //show the fragment that displays the result
        Fragment newFragment = CalculatorSecondLevelFragment.newInstance(Double.toString(result));

        navigateTo(newFragment);
    }

    private void navigateTo(Fragment newFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
