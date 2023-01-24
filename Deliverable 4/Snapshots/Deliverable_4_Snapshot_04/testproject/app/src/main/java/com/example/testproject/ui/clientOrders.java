package com.example.testproject.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class clientOrders extends AppCompatActivity {

    DatabaseReference databaseRef;
    DatabaseReference dbase;

    FirebaseAuth auth;
    FirebaseUser Client;

    EditText complaintField, rateField;

    String cookId;

    ListView menu;
    List<Request> meals;


    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_list);
        auth = FirebaseAuth.getInstance();

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Requests");
        dbase = FirebaseDatabase.getInstance().getReference().child("users");

        menu = (ListView) findViewById(R.id.listViewTable);
        meals = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            cookId = null;
        } else {
            cookId = extras.getString("Id");
        }






        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Request req = meals.get(i);

                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        final String cookID = req.getCookName();
                        final String cookThingy = req.getCook();
                        final String status = req.getStatus();


                        showMeal(cookID, cookThingy, status);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });



    }


    private void showMeal(String meal, final String cookId, final String status){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_ratecook, null);
        dialogBuilder.setView(dialogView);


        final TextView CookName = (TextView) dialogView.findViewById(R.id.textView8);

        final EditText complaintField = (EditText) dialogView.findViewById(R.id.textView21);
        final EditText ratingField = (EditText) dialogView.findViewById(R.id.textView25);


        CookName.setText(meal);

        dialogBuilder.setTitle("MEAL DETAILS");
        final AlertDialog b = dialogBuilder.create();
        b.show();


        final Button complainBTN = (Button) dialogView.findViewById(R.id.button5);
        final Button rateBTN = (Button) dialogView.findViewById(R.id.button6);

        complainBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String complaint = complaintField.getText().toString().trim();


                if(status.equals("DELIVERED")) {
                    if (!complaint.equals("")) {


                        dbase.child(cookId).child("complaint").setValue(complaint);


                    }
                    Toast.makeText(getApplicationContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getApplicationContext(), "How can you complain about food you never ate?", Toast.LENGTH_SHORT).show();
                }


            }
        });

        rateBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String rating = ratingField.getText().toString().trim();

                final double theRating = Double.parseDouble(rating);


                if(status.equals("DELIVERED")) {
                    if (!rating.equals("")) {

                        /*
                        dbase.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                double newrating = 0.0;
                                double rating = 0.0;
                                int ratingNum = 0;

                                for (DataSnapshot postsnapshot : snapshot.getChildren()){
                                    //Toast.makeText(getApplicationContext(), postsnapshot.getKey(), Toast.LENGTH_SHORT).show();

                                    if(postsnapshot.getKey().equals(cookId)) {
                                        rating = Double.parseDouble(postsnapshot.child("rating").getValue().toString());
                                        ratingNum = Integer.parseInt(postsnapshot.child("ratingNum").getValue().toString());
                                        break;
                                    }
                                }
                                newrating = (rating * ratingNum + theRating) / (ratingNum + 1);
                                Toast.makeText(getApplicationContext(), cookId, Toast.LENGTH_SHORT).show();
                                //dbase.child(cookId).child("rating").setValue(newrating);
                                dbase.child(cookId).child("ratingNum").setValue(ratingNum+1);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                        */

                        dbase.child(cookId).child("rating").setValue(rating);

                    }

                    Toast.makeText(getApplicationContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "How can you rate a chef if you never ate their food?", Toast.LENGTH_SHORT).show();
                }
                b.dismiss();


            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();
        Client = auth.getCurrentUser();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull final DataSnapshot dataSnapshot) {

                meals.clear();
                for (final DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    final Request holder = postSnapshot.getValue(Request.class);

                    if (holder.getClient().equals(Client.getUid())){
                        meals.add(holder);
                    }


                    
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
