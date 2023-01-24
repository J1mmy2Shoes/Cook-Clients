package com.example.deliverable_1_user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deliverable_1_user_interface.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    private Button loginBtn;
    private Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //this code makes the buttons work when u click them
        loginBtn = (Button) findViewById(R.id.loginBtn);
        signupBtn = (Button) findViewById(R.id.signupBtn);

        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openSignUp();
            }
        });
    }

    //These are the methods that make the buttons on the main page open the login or the sign up page
    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openSignUp() {
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }


}