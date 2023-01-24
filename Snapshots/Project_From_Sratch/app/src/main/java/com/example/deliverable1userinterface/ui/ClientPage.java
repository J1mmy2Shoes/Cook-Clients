package com.example.deliverable1userinterface.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.deliverable1userinterface.R;
import com.example.deliverable1userinterface.accTypes.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ClientPage extends AppCompatActivity {
    EditText client_cardNum, client_firstName, client_lastName, client_email,
            client_password, client_addy;

    Button client_btn;

    FirebaseAuth authenticator;
    FirebaseUser user;
    DatabaseReference mDatabase;

    String cardNum, firstName, lastName, email, addy, password;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        client_cardNum = findViewById(R.id.Client_cardNum);
        client_firstName = findViewById(R.id.Client_firstName);
        client_lastName = findViewById(R.id.Client_lastName);
        client_email = findViewById(R.id.Client_email);
        client_addy = findViewById(R.id.Client_addy);
        client_password = findViewById(R.id.Client_password);

        client_btn = findViewById(R.id.create_client);

        authenticator = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        if (authenticator.getCurrentUser() != null) {
            startActivity(new Intent(ClientPage.this, MainPage.class));
            finish();
        }

        client_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNum = client_cardNum.getText().toString();
                firstName = client_firstName.getText().toString().trim();
                lastName = client_lastName.getText().toString().trim();
                addy = client_addy.getText().toString().trim();
                email = client_email.getText().toString().trim();
                password = client_password.getText().toString().trim();


                // Validating fields
                boolean error = false;

                if (TextUtils.isEmpty(email)) {
                    client_email.setError("Email is required...");
                    error = true;
                }

                if (TextUtils.isEmpty(password)) {
                    client_password.setError("Password is required...");
                    error = true;
                }

                if (password.length() <= 5) {
                    client_password.setError("Password must be atleast 6 characters...");
                    error = true;
                }


                if (error) return;



                // Registering user in Firebase Auth

                authenticator.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = authenticator.getCurrentUser();
                            newClient(firstName, lastName, email, cardNum, user.getUid());
                            Toast.makeText(ClientPage.this, "Successfully created a Client account!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ClientPage.this,  welcomePage.class));
                            finishAffinity();
                        }
                        else {
                            Toast.makeText(ClientPage.this, "ERROR! Employee creation unsuccessful...", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    private void newClient(String firstName, String lastName, String userName, String cardNum, String userID) {
        Client client = new Client(firstName, lastName, userName, cardNum);

        mDatabase.child("users").child(userID).setValue(client);

    }
}