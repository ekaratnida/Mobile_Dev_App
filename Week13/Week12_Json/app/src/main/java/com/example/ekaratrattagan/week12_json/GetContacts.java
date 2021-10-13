package com.example.ekaratrattagan.week12_json;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class GetContacts extends AsyncTask <Void,Void,Void> {

    Context mainActivity;
    ListView lv;
    ArrayList<HashMap<String, String>> contactList;
    ArrayList<Movie> movieList;

    GetContacts(Context c,
                ListView _lv,
                ArrayList<HashMap<String, String>> _contactList) {
        mainActivity = c;
        lv = _lv;
        contactList = _contactList;
        movieList = new ArrayList<Movie>();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mainActivity,"Json Data is downloading",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... arg0)
    {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String url = "http://showtimes.everyday.in.th/api/v2/movie/"; //
        //String url = "https://api.androidhive.info/contacts/";
        String jsonStr = sh.makeServiceCall(url);

        if (jsonStr != null)
        {
            try
            {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray contacts = jsonObj.getJSONArray("results");

                // looping through All Contacts
                for (int i = 0; i < contacts.length(); i++)
                {
                    JSONObject c = contacts.getJSONObject(i);
                    String title = c.getString("title");
                    JSONArray images = c.getJSONArray("images");
                    String image = "";
                    for( int j=0; j<images.length(); j++) {
                        JSONObject v = images.getJSONObject(j);
                        image = v.getString("url");
                        break;
                    }



                    /*String name = c.getString("name");
                    String email = c.getString("email");
                    String address = c.getString("address");
                    // Phone node is JSON Object
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    String home = phone.getString("home");
                    String office = phone.getString("office");*/

                    // tmp hash map for single contact
                    HashMap<String, String> contact = new HashMap<>();
                    // adding each child node to HashMap key => value
                    contact.put("title",title);
                    if (!image.isEmpty()) {
                        contact.put("image", image);
                    }

                    Movie m = new Movie(title,image);
                    movieList.add(m);


                    // adding contact to contact list
                    contactList.add(contact);
                } // end for
            } // end try
            catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }

        } else {
            Log.e(TAG, "Couldn't get json from server.");
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

       /* ListAdapter adapter = new SimpleAdapter(mainActivity, contactList,
                R.layout.list_item, new String[]{ "image","id","title"},
                new int[]{R.id.imageView, R.id.email, R.id.mobile});
        lv.setAdapter(adapter); */

        CustomAdapter adapter = new CustomAdapter(mainActivity, movieList);
        lv.setAdapter(adapter);
    }
}
//https://jsoneditoronline.org/
//แสดง ชื่อหนัง กับ รูปโปสเตอร์