package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testproject.R;

public class splashPage extends AppCompatActivity {

    private Button logoutButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        Intent intent = getIntent();
        String typeOfUser = intent.getStringExtra("key");
        TextView tv1 = (TextView)findViewById(R.id.userTypeText);
        tv1.setText(typeOfUser);

        logoutButton = findViewById(R.id.logoutBtn);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backOut = new Intent(splashPage.this, MainPage.class);
                backOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backOut);
            }
        });
    }
}