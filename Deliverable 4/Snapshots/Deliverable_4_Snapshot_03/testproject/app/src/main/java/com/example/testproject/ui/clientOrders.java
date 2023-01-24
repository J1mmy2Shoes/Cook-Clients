package com.example.testproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class clientOrders extends AppCompatActivity {

    DatabaseReference databaseRef;

    String cookId;

    ListView menu;
    List<Request> meals;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_orders);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        menu = (ListView) findViewById(R.id.listViewTable);
        meals = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            cookId = null;
        } else {
            cookId = extras.getString("Id");
        }



    }





    @Override
    protected void onStart() {
        super.onStart();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull final DataSnapshot dataSnapshot) {

                meals.clear();
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    final Request holder = postSnapshot.getValue(Request.class);

                    meals.add(holder);

                    requestList mealAdapter = new requestList(clientOrders.this, meals);
                    menu.setAdapter(mealAdapter);

                }

            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });

    }
}
