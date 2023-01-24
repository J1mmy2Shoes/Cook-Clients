package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.testproject.R;

public class welcomePage extends AppCompatActivity {

    private String userType;
    //private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        Intent intent = getIntent();
        String userType = intent.getStringExtra("key");
        //TextView tv1 = (TextView)findViewById(R.id.userTypeText);
        //tv1.setText(userType);

        /*logoutButton = (Button) findViewById(R.id.logoutBtn);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcomedPage.this, MainPage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });*/
    }


}