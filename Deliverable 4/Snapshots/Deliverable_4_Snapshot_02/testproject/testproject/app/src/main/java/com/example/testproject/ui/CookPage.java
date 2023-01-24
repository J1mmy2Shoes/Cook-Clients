package com.example.testproject.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.example.testproject.users.Cook;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CookPage extends AppCompatActivity {
    EditText Cook_firstName, Cook_lastName, Cook_email, Cook_addy, Cook_password, Cook_description, Cook_complaint;
    Button cookBtn;
    FirebaseAuth authenticator;
    FirebaseUser user;
    DatabaseReference Dbase;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cook);

        Cook_firstName = findViewById(R.id.Cook_firstName);
        Cook_lastName = findViewById(R.id.Cook_lastName);
        Cook_email = findViewById(R.id.Cook_email);
        Cook_addy = findViewById(R.id.Cook_addy);
        Cook_password = findViewById(R.id.Cook_password);
        Cook_description = findViewById(R.id.Cook_description);
        Cook_complaint = findViewById(R.id.cook_complaint);

        cookBtn = findViewById(R.id.Cook_Btn);

        authenticator = FirebaseAuth.getInstance();
        Dbase = FirebaseDatabase.getInstance().getReference();


        if (authenticator.getCurrentUser() != null) {
            startActivity(new Intent(CookPage.this, MainPage.class));
            finish();
        }

        cookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String first = Cook_firstName.getText().toString().trim();
                final String last = Cook_lastName.getText().toString().trim();

                final String email = Cook_email.getText().toString().trim();
                final String complaint = Cook_complaint.getText().toString().trim();
                String password = Cook_password.getText().toString().trim();
                final String accStatus = "Active";



                boolean error = false;

                if (TextUtils.isEmpty(email)) {
                    Cook_email.setError("Email is required...");
                    error = true;
                }

                if (TextUtils.isEmpty(password)) {
                    Cook_password.setError("Password is required...");
                    error = true;
                }

                if (password.length() < 6) {
                    Cook_password.setError("Password must be atleast 6 characters...");
                    error = true;
                }

                if (error) return;



                //puts the stuff in firebase

                authenticator.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = authenticator.getCurrentUser();
                            newCook(first, last, email, complaint, user.getUid());
                            Toast.makeText(CookPage.this, "Successfully created a cook account!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CookPage.this, MainPage.class));
                            finishAffinity();
                        } else {
                            Toast.makeText(CookPage.this, "ERROR! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
    }

    private void newCook(String first, String last, String email, String complaint, String userID) {
        Cook cook = new Cook(first, last, email, complaint, "Active");

        Dbase.child("users").child(userID).setValue(cook);
    }
}
