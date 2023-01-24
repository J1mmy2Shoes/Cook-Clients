package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testproject.R;

public class RegisterPage extends AppCompatActivity {
    Button registerCook, registerClient;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        registerCook = findViewById(R.id.register_cook);
        registerClient = findViewById(R.id.register_client);

        registerCook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPage.this, CookPage.class));
            }
        });

        registerClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterPage.this, ClientPage.class));
            }
        });
    }
}