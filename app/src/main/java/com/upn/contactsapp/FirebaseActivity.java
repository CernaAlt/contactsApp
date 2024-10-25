package com.upn.contactsapp;

import android.content.SyncRequest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.contactsapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kotlin.collections.ArrayDeque;

public class FirebaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        Button btn = findViewById(R.id.btnCreateOnFirebase);
        EditText nombre=findViewById(R.id.btn_register);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ContactmyRef = database.getReference("N00288641").child("contacts");

        List<Contact> contacts=new ArrayList<>();
        ContactmyRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child:snapshot.getChildren()){
                    Contact c=child.getValue(Contact.class);
                    contacts.add(c);
                    Log.i("app",child.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btn.setOnClickListener(v -> {
            // Obtener el nombre ingresado en el EditText
            String name = nombre.getText().toString();

            if (!name.isEmpty()) {
                // Crear un nuevo contacto
                Contact newContact = new Contact(name, "12345678");
                newContact.uuid = UUID.randomUUID().toString();

                // Guardar
                ContactmyRef.child(newContact.uuid).setValue(newContact)
                        .addOnSuccessListener(aVoid -> Log.i("app", "Contacto guardado con éxito"))
                        .addOnFailureListener(e -> Log.e("app", "Error al guardar contacto: " + e.getMessage()));
            } else {
                Log.e("app", "El campo nombre está vacío");
            }
        });
    }
}