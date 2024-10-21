package com.upn.contactsapp.activities;

import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upn.contactsapp.R;
import com.upn.contactsapp.entities.Contact;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Button registro=findViewById(R.id.btn_regitro);
        registro.setOnClickListener(v -> {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            //database.getReference("message").setValue("Hello, World!");
            DatabaseReference myRef = database.getReference("contacts");

            Contact c3=new Contact("Carlos","123456789");
            Contact c4=new Contact("Juan","987654321");
            myRef.push().setValue(c3);
            myRef.push().setValue(c4);
        });
    }
}