package com.example.pok.week7_0;

import android.widget.ImageView;

/**
 * Created by pok on 2/15/2017.
 */

public class Contact
{
    public int _id;
    public String _name;
    public String _phone_number;
    public String _imagePath;

    public Contact(){}

    public Contact(String name, String _phone_number) {
        this._name = name;
        this._phone_number = _phone_number;
    }

    public Contact(int id, String name, String _phone_number) {
        this._id = id;
        this._name = name;
        this._phone_number = _phone_number;
    }
}