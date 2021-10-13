package com.example.ekaratrattagan.mvp_example2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ICustomerView, View.OnClickListener {

    EditText edt_firstname, edt_lastname, edt_userid;
    Button btn_save, btn_load;
    CustomerPresenter mCustomerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomerPresenter = new CustomerPresenter(this);
        btn_save.setOnClickListener(this);
        btn_load.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save_id:
                mCustomerPresenter.saveCustomer(
                        edt_firstname.getText().toString(),
                        edt_lastname.getText().toString()
                );
                break;
            case R.id.btn_load_id:
                mCustomerPresenter.loadCustomer(Integer.parseInt(edt_userid.getText().toString()));
                break;
        }
    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setId(int id) {

    }
}
