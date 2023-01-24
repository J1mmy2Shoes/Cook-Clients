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
import com.example.testproject.users.Client;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ClientPage extends AppCompatActivity {
    EditText client_cardNum, client_firstName, client_lastName, client_email,
            client_password, client_addy;
    Button ClientBtn;

    FirebaseAuth authenticator;
    FirebaseUser user;
    DatabaseReference Dbase;

    String cardNum, firstName, lastName, email, addy, password, complaint, accStatus;

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

        ClientBtn = findViewById(R.id.Client_Btn);

        authenticator = FirebaseAuth.getInstance();
        Dbase = FirebaseDatabase.getInstance().getReference();


        if (authenticator.getCurrentUser() != null) {
            startActivity(new Intent(ClientPage.this, MainPage.class));
            finish();
        }

        ClientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardNum = client_cardNum.getText().toString();
                firstName = client_firstName.getText().toString().trim();
                lastName = client_lastName.getText().toString().trim();
                addy = client_addy.getText().toString().trim();
                email = client_email.getText().toString().trim();
                password = client_password.getText().toString().trim();
                complaint = "";
                accStatus = "Active";


                // error handling
                boolean error = false;

                if (TextUtils.isEmpty(email)) {
                    client_email.setError("Email is required...");
                    error = true;
                }

                if (TextUtils.isEmpty(password)) {
                    client_password.setError("Password is required...");
                    error = true;
                }

                if (password.length() < 6) {
                    client_password.setError("Password must be atleast 6 characters...");
                    error = true;
                }


                if (error) return;



                // firebase stuff

                authenticator.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = authenticator.getCurrentUser();
                            newClient(firstName, lastName, email, cardNum, complaint, accStatus, user.getUid());
                            Toast.makeText(ClientPage.this, "Successfully created a Client account!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ClientPage.this,  MainPage.class));
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

    private void newClient(String firstName, String lastName, String userName, String cardNum, String complaint, String accStatus, String userID) {
        Client client = new Client(firstName, lastName, userName, cardNum, complaint, accStatus);

        Dbase.child("users").child(userID).setValue(client);

    }
}