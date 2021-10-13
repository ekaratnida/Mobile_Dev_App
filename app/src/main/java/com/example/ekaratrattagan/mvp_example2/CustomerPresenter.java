package com.example.ekaratrattagan.mvp_example2;

public class CustomerPresenter {

    ICustomerView mCustomerView;
    ICustomerModel mCustomerModel;

    public CustomerPresenter(ICustomerView view) {
        mCustomerView = view;
        mCustomerModel = new CustomerModel();
    }

    public void saveCustomer(String firstName, String lastName) {
        mCustomerModel.setFirstName(firstName);
        mCustomerModel.setLastName(lastName);
    }

    public void loadCustomer(int id) {
        CustomerModel model = mCustomerModel.load(id);
        mCustomerView.setId(model.getId());
        mCustomerView.setFirstName(model.getFirstName());
        mCustomerView.setLastName(model.getLastName());
    }
}
