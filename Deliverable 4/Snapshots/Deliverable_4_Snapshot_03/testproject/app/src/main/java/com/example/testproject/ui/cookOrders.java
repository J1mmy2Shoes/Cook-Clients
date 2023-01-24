package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.testproject.R;

public class cookOrders extends AppCompatActivity {
    String cookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_orders);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            cookId = null;
        } else {
            cookId = extras.getString("Id");
        }

    }
}