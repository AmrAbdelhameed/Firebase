package com.example.amr.firebaseregistrationloginexam;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Contact {

    String name;
    String positive;
    String negative;

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public String getPositive() {
        return positive;
    }

    public String getNegative() {
        return negative;
    }


    public Contact(String name, String positive, String negative) {
        this.name = name;
        this.positive = positive;
        this.negative = negative;
    }

}
