package com.example.amr.firebaseregistrationloginexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    TextView name, negative, positive;
    private String userId;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("Vote");

        name = (TextView) findViewById(R.id.editTextName);
        negative = (TextView) findViewById(R.id.editTextPhone);
        positive = (TextView) findViewById(R.id.editTextEmail);

    }

    private void createUser(String name, String positive, String negative) {

        if (TextUtils.isEmpty(userId)) {
            userId = user.getUid();
        }

        Contact contact = new Contact(name, positive, negative);

        mFirebaseDatabase.child(userId).setValue(contact);

        addUserChangeListener();
    }

    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Contact contact = dataSnapshot.getValue(Contact.class);

                if (contact == null) {
                    Log.e(TAG, "Contact data is null!");
                    return;
                }
                Log.e(TAG, "Contact data is changed!" + contact.name + ", " + contact.positive + ", " + contact.negative);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e(TAG, "Failed to read user", error.toException());
            }
        });
    }

    public void run(View view) {

        String nname = name.getText().toString();
        String npositive = positive.getText().toString();
        String nnegative = negative.getText().toString();

        if (name.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please Try Again ... ", Toast.LENGTH_SHORT).show();
        } else {
            createUser(nname, npositive, nnegative);
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
        }
    }

    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }

    public void show(View view) {

        Intent intent = new Intent(getApplicationContext(), Show.class);
        startActivity(intent);

    }
}