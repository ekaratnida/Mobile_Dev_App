package com.example.ekaratrattagan.mvp_example2;

interface ICustomerModel {
    public void setFirstName(String firstName);
    public void setLastName(String lastName);
    public String getFirstName();
    public String getLastName();
    public CustomerModel load(int id);
    public int getId();
}
