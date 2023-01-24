package com.example.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

public class cookOrdersPage extends AppCompatActivity {
    DatabaseReference databaseRef;
    Button backBtn;
    String cookId;
    ListView orderList;
    List<Request> requests;
    List<String> requestIDs;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_orders);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        orderList = (ListView) findViewById(R.id.ordersListView);
        requests = new ArrayList<>();
        requestIDs = new ArrayList<>();
        backBtn = findViewById(R.id.backBtnOrders);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            cookId = null;
        } else {
            cookId = extras.getString("Id");
        }


        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Request request = requests.get(i);
                String requestId = requestIDs.get(i);

                    showRequest(requestId, request.getMealName(), request.getCook(),
                            request.getClient(), request.getStatus());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cookOrdersPage.this, MainCook.class);
                intent.putExtra("Id", cookId);
                startActivity(intent);

            }

        });
    }
    private void showRequest(final String id, String meal, String cook, String client, String status){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_cook_action_request, null);
        dialogBuilder.setView(dialogView);

        final TextView mealName = (TextView) dialogView.findViewById(R.id.meanNameText);
        final TextView orderId = (TextView) dialogView.findViewById(R.id.OrderIDtext);
        final TextView clientName = (TextView) dialogView.findViewById(R.id.clientNameText);
        final TextView stat = (TextView) dialogView.findViewById(R.id.statusText);

        final Button buttonProgress = (Button) dialogView.findViewById(R.id.progressBtn);
        final Button buttonDecline = (Button) dialogView.findViewById(R.id.declineBtn);
        final Button buttonDelivered = (Button) dialogView.findViewById(R.id.deliverBtn);
        final Button buttonSet = (Button) dialogView.findViewById(R.id.customBtn);
        final EditText custom = (EditText) dialogView.findViewById(R.id.customStatEdit);

        mealName.setText(meal);
        orderId.setText("Order ID:" + id);
        clientName.setText("User ID: " + client); // FOR NOW THE NAME IS JUST THE ID, FIX LATER
        stat.setText(status);
        dialogBuilder.setTitle("Order Request");

        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus("APPROVED", id);
                b.dismiss();
            }
        });

        buttonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus("DECLINED", id);
                b.dismiss();
            }
        });

        buttonDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatus("DELIVERED", id);
                b.dismiss();
            }
        });

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customStatus = custom.getText().toString();
                changeStatus(customStatus, id);
                b.dismiss();
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Requests");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                requests.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    String currentRequest = postSnapshot.getKey();
                    Request request = postSnapshot.getValue(Request.class);

                    if(request.getCook().equals(cookId)) {
                            requestIDs.add(currentRequest);
                            requests.add(request);
                    }

                    requestList requestAdapter = new requestList(cookOrdersPage.this, requests);
                    orderList.setAdapter(requestAdapter);
                }
            }

            @Override
            public void onCancelled( @androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });

    }

    private void changeStatus(String status, String id){
        databaseRef.child(id).child("status").setValue(status);
    }
}
