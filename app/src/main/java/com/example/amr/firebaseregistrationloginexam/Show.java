package com.example.amr.firebaseregistrationloginexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

public class Show extends AppCompatActivity {

    private String room_name;
    ArrayAdapter<String> adapter;
    private DatabaseReference root;
    ListView lv;
    ArrayList<String> array_negative, array_positive, array_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        room_name = "Vote";
        lv = (ListView) findViewById(R.id.listView1);

        array_negative = new ArrayList<>();
        array_positive = new ArrayList<>();
        array_Name = new ArrayList<>();

        root = FirebaseDatabase.getInstance().getReference().child(room_name);

        root.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);
                adapter = new ArrayAdapter<String>(Show.this, android.R.layout.simple_list_item_1, array_Name);
                lv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                append_chat_conversation(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

                Toast.makeText(Show.this, "Positive Opinion : " + array_positive.get(arg2) + " , Negative Opinion : " + array_negative.get(arg2), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private String negati, namee, positiv;

    private void append_chat_conversation(DataSnapshot dataSnapshot) {

        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            negati = (String) ((DataSnapshot) i.next()).getValue();
            array_Name.add(negati);
            namee = (String) ((DataSnapshot) i.next()).getValue();
            array_negative.add(namee);
            positiv = (String) ((DataSnapshot) i.next()).getValue();
            array_positive.add(positiv);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
