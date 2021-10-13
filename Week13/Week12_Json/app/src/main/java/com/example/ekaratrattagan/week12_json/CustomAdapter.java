package com.example.ekaratrattagan.week12_json;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by pok on 2/15/2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context mContext;

    //List<String> strName;
    String[] strName = null;

    List<Movie> _movies = null;


    public CustomAdapter(Context context, String[] strName) //List<String> strName)
    {
        mContext = context;
        this.strName = strName;

    }


    public CustomAdapter(Context context, List<Movie> movies) //List<String> strName)
    {
        mContext = context;
        _movies = movies;

    }

    @Override
    public int getCount() {

        if(this.strName != null)
            return strName.length; // strName.size();
        else
            return _movies.size();
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
            view = mInflater.inflate(R.layout.list_item, parent, false);

        TextView textView = (TextView)view.findViewById(R.id.email);
        textView.setText(_movies.get(position)._title); //.get(position));

        ImageView imgView1 = (ImageView)view.findViewById(R.id.imageView);
        String imgPath = _movies.get(position)._imagePath;

        Picasso.get().load(imgPath).resize(300,300).into(imgView1);


        return view;
    }
}