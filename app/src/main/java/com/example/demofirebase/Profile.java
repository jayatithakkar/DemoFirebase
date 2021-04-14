package com.example.demofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Profile extends AppCompatActivity {

    EditText name, gndr, mail, country, contac;

    private final String TAG = this.getClass().getName().toUpperCase();
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private Map<String, String> userMap;
    private String userid;
    private static final String USERS = "Info";
    String nme1;
    String gndr1;
    String email1;
    long cnum1;
    String cntr1;

    Button sv;

    DatabaseReference ref;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        gndr = findViewById(R.id.gndr);
        country = findViewById(R.id.country);
        //contac = findViewById(R.id.contact);

        sv = findViewById(R.id.savebtm);
        ref = FirebaseDatabase.getInstance().getReference("Info");
        auth = FirebaseAuth.getInstance();

        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("Info");
        DatabaseReference userRef = reff.child(USERS);
        Log.v("USERID", userRef.getKey());


        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sname= snapshot.child("Name").getValue(String.class);
                name.setText(sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sname= snapshot.child("Gender").getValue(String.class);
                gndr.setText(sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sname= snapshot.child("Email").getValue(String.class);
                mail.setText(sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String sname= snapshot.child("Country").getValue(String.class);
                country.setText(sname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nme1=name.getText().toString();
                email1=mail.getText().toString();
                gndr1=gndr.getText().toString();
                cntr1=country.getText().toString();




                Data info = new Data(nme1, email1,  12345, cntr1, gndr1);

                FirebaseDatabase.getInstance().getReference().child("Info")
                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {



                    }
                });
            }
        });


    }


}