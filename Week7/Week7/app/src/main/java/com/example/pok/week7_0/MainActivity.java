package com.example.pok.week7_0;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHandler db = new DatabaseHandler(this);
        int numOfmembers = 5;

        for(int i=1; i<numOfmembers; i++) {
            db.addContact(new Contact("Ekarat_"+i, "00"+i));
        }

        //Call all data from database
        final List<Contact> contacts = db.getAllContacts();

        final CustomAdapter adapter = new CustomAdapter(getApplicationContext(), contacts);
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(adapter);

        //////////////dddddd////////
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Contact _contact = adapter._contacts.get(position);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Delete?");
                dialog.setMessage("Do you want to delete [" +
                        _contact._id + "] " +
                        _contact._name + "  " +
                        _contact._phone_number);
                dialog.setNegativeButton("Cancel", null);
                dialog.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        adapter._contacts.remove(position);
                        db.deleteContact(_contact);
                        adapter.notifyDataSetChanged();
                    }
                });

                dialog.show();
                return false;
            }
        });
    }
}
