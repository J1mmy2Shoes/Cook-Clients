package com.example.deliverable_1_user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.google.android.gms.common.SignInButton;

import java.util.ArrayList;
import java.util.LinkedList;



public class signup extends AppCompatActivity {



    ArrayList<listObj> theList = new ArrayList<listObj>();

    EditText usernameSignupField, passwordSignupField;
    String username, password;
    Boolean ClientOrCook;
    Button SignupBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        Switch simpleSwitch = (Switch) findViewById(R.id.roleSwitch);

       // check current state of a Switch (true or false).
        ClientOrCook = simpleSwitch.isChecked();


        usernameSignupField = findViewById(R.id.usernameSignupField);
        passwordSignupField = findViewById(R.id.passwordSignupField);

        SignupBtn2 = (Button) findViewById(R.id.signupBtn2);


        SignupBtn2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                username = usernameSignupField.getText().toString();
                password = passwordSignupField.getText().toString();
                listObj thing;

                thing = new listObj(username, password, ClientOrCook);
                theList.add(thing);

                if (TextUtils.isEmpty(username)) {
                    passwordSignupField.setError("Username is required");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    passwordSignupField.setError("Password is required");
                    return;
                }

                else{
                    Intent i = new Intent();
                    getIntent().setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    i.putParcelableArrayListExtra("theList", ArrayList<>);
                    startActivity(i);
                    startActivity(new Intent(signup.this,  MainActivity.class));
                }

            }
        });


    }



}