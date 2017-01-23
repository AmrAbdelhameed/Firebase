package com.example.amr.firebaseregistrationloginexam;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NotificationShow extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_show);

        Bundle extras = getIntent().getExtras();
        String a = extras.getString("p");

        t = (TextView) findViewById(R.id.textView4);
        t.setText(a);
    }

}