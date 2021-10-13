package com.example.ekaratrattagan.calculator.mvp.view;

/**
 * Created by javier on 20/10/15.
 */
public interface ICalcView {

    void updateDisplay(double value);

    double getOperand1();

    double getOperand2();

    void subscribe(IComputationListener listener);

    interface IComputationListener {
        void onAdd();

        void onMult();

        void onDiv();

        void onSub();
    }
}
