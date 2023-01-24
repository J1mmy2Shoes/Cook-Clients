package com.example.testproject.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adminPage extends AppCompatActivity {


    DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    ListView complaintList;
    List<Complaint> complaints;

    Button logoutBtn;


    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);                               // Admin page to be added

        databaseRef = FirebaseDatabase.getInstance().getReference().child("users"); // Fix or verify reference to DB
        logoutBtn = findViewById(R.id.logout_button);;                              // Log out button on admin page to be added
        complaintList = (ListView) findViewById(R.id.listView);                     // ListView on admin page to be added
        complaints = new ArrayList<>();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backOut = new Intent(adminPage.this, MainPage.class);
                backOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                FirebaseAuth.getInstance().signOut();
                startActivity(backOut);
            }
        });

        complaintList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Complaint complaint = complaints.get(i);
                showAction(complaint.getId(), complaint.getComplaint(),
                        (complaint.getFirstName()+complaint.getLastName()), complaint.getEmail());
            }
        });
    }

    private void showAction(final String id, String complaint, String name, String email) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.activity_admin_action, null);
            dialogBuilder.setView(dialogView);

            final TextView compName = (TextView) dialogView.findViewById(R.id.textName);
            final TextView compEmail = (TextView) dialogView.findViewById(R.id.textEmail);
            final TextView complain = (TextView) dialogView.findViewById(R.id.textComplaint);
            final EditText suspendField  = (EditText) dialogView.findViewById(R.id.suspendField);
            final Button buttonSuspend = (Button) dialogView.findViewById(R.id.suspend_button);
            final Button buttonDismiss = (Button) dialogView.findViewById(R.id.dismiss_button);

            compName.setText(name);
            compEmail.setText(email);
            complain.setText(complaint);
            dialogBuilder.setTitle("COMPLAINT");
            // Builds dialog box

            final AlertDialog b = dialogBuilder.create();
            b.show();

            buttonSuspend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int days;
                    String value = suspendField.getText().toString();
                    if(value.matches("")){
                        days = -1;
                    }
                    else{
                        days = Integer.parseInt(suspendField.getText().toString());
                    }

                    suspendCook(id, days);
                    b.dismiss();
                }

            });

            buttonDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        dismissComplaint(id);
                        b.dismiss();
                }
            });

        }

    @Override
    protected void onStart(){
        super.onStart();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                complaints.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Complaint comps = postSnapshot.getValue(Complaint.class);
                    complaints.add(comps);

                    ComplaintsList complaintsAdapter = new ComplaintsList(adminPage.this,complaints);
                    complaintList.setAdapter(complaintsAdapter);
                }
            }

            @Override
            public void onCancelled( @androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });

    }

    private void suspendCook(String userid, int days){
        // Deal with suspending cook
        FirebaseUser user1 = mAuth.getCurrentUser();
        if (user1 != null) {
            mAuth = FirebaseAuth.getInstance();
            String currentUserID = mAuth.getCurrentUser().getUid();
            databaseRef = FirebaseDatabase.getInstance().getReference();

            if(days == -1){
                databaseRef.child("users").child(userid).child("accStatus").setValue("disabled");
            }
            else{
                long time = System.currentTimeMillis();
                time = time + (86400000L * days);

                String s = Long.toString(time);
                databaseRef.child("users").child(userid).child("accStatus").setValue(s);
            }

            databaseRef.child("users").child(userid).child("complaint").setValue("");
        }
    }

    private void dismissComplaint(String userid){
        // Deal with removing the complaint from the database
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {

            databaseRef = FirebaseDatabase.getInstance().getReference();

            databaseRef.child("users").child(userid).child("complaint").setValue("");

        }

    }



}
