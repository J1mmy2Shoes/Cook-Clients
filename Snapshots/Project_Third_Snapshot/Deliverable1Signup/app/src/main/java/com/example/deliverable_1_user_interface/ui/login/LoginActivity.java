package com.example.deliverable_1_user_interface.ui.login;

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

import com.example.deliverable_1_user_interface.R;

import com.example.deliverable_1_user_interface.welcomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_password;
    Button login_button;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        login_email = findViewById(R.id.usernameSignupField);
        login_password = findViewById(R.id.passwordSignupField);
        login_button = findViewById(R.id.loginBtn2);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    login_email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    login_password.setError("Password is required");
                    return;
                }

                if (email.equals("admin") && password.equals("admin")){
                    startActivity(new Intent(LoginActivity.this, welcomePage.class));
                    finishAffinity();
                }

                else {
                    Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(LoginActivity.this, welcomePage.class));










                /*
                    authenticator.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                fireUser = authenticator.getCurrentUser();

                                referenceUser = FirebaseDatabase.getInstance().getReference().child("users").child(fireUser.getUid());

                                referenceUser.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        Toast.makeText(LoginPage.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                                        startActivity(new Intent(LoginPage.this, welcomePage.class));


                                        //Closes all activities
                                        finishAffinity();
                                    }
                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(LoginPage.this, "Error getting information", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else {
                                Toast.makeText(LoginPage.this, "ERROR! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    */
                }


            }
        });
    }
}
