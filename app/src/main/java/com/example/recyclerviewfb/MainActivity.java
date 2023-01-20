package com.example.recyclerviewfb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btninsert,btnview;
    EditText name,email,age;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btninsert = findViewById(R.id.btninsert);
        btnview = findViewById(R.id.btnview);
        name = findViewById(R.id.et_name);


        email = findViewById(R.id.et_email);
        age = findViewById(R.id.et_age);

        databaseUsers = FirebaseDatabase.getInstance().getReference();

        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,UserList.class));
                finish();
            }
        });


    }

    private void InsertData() {
        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userage = age.getText().toString();
        String id = databaseUsers.push().getKey();

        User user = new User(username,useremail,userage);
        databaseUsers.child("users").child(id).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, "User details inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}