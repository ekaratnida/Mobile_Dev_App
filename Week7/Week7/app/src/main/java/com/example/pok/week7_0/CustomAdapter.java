package com.example.pok.week7_0;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by pok on 2/15/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context mContext;

    //List<String> strName;
    String[] strName = null;

    List<Contact> _contacts = null;


    public CustomAdapter(Context context, String[] strName) //List<String> strName)
    {
        mContext = context;
        this.strName = strName;

    }


    public CustomAdapter(Context context, List<Contact> contacts) //List<String> strName)
    {
        mContext = context;
        _contacts = contacts;

    }

    @Override
    public int getCount() {

        if(this.strName != null)
            return strName.length; // strName.size();
        else
            return _contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.listview_row, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.textView1);
        textView.setText(_contacts.get(position)._name); //.get(position));

        TextView textView2 = (TextView)view.findViewById(R.id.textView2);
        textView2.setText(_contacts.get(position)._phone_number); //.get(position));

        ImageView imgView1 = (ImageView)view.findViewById(R.id.imageView1);
        imgView1.setImageResource(R.drawable.img1);

        return view;
    }
}