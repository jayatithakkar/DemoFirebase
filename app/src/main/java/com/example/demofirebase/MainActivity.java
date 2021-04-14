package com.example.demofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText name,mail,pass,ctnum;
    RadioButton mle,fml,other;
    Button signup;
    DatabaseReference ref;
    String gndr;
    FirebaseAuth auth;

    String nme;
    String email;
    String pas;
    long cnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=(EditText)findViewById(R.id.su_name);
        mail=(EditText)findViewById(R.id.et_mail);
        pass=(EditText)findViewById(R.id.et_pass);
        ctnum=(EditText)findViewById(R.id.et_num);
        mle=(RadioButton)findViewById(R.id.male);
        fml=(RadioButton)findViewById(R.id.female);
        other=(RadioButton)findViewById(R.id.radio_other);
        signup=(Button)findViewById(R.id.btn);

        ref=FirebaseDatabase.getInstance().getReference("Info");
        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nme = name.getText().toString();
                email = mail.getText().toString();
                pas = pass.getText().toString();
                cnum = Long.parseLong(ctnum.getText().toString());

                if (mle.isChecked()) {
                    gndr = "Male";
                }
                if (fml.isChecked()) {
                    gndr = "Female";
                }
                if (other.isChecked()) {
                    gndr = "Other";
                }

                Data info = new Data(nme, email,  cnum, pas,gndr);

                FirebaseDatabase.getInstance().getReference("Info")
                        .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Intent i = new Intent(MainActivity.this, Profile.class);
                        startActivity(i);

                    }
                });


            }

        });
}
}