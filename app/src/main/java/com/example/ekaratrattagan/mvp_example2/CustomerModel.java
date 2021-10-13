package com.example.ekaratrattagan.mvp_example2;

public class CustomerModel implements ICustomerModel {


    @Override
    public void setFirstName(String firstName) {

    }

    @Override
    public void setLastName(String lastName) {

    }

    @Override
    public String getFirstName() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public CustomerModel load(int id) {
        return this;
    }

    @Override
    public int getId() {
        return 0;
    }
}
