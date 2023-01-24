package com.example.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class adminPage extends AppCompatActivity {

    /*
    Button actionBtn, dismissBtn;
    DatabaseReference databaseRef;
    ListView complaintList;
    List<Complaint> complaints;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);                               // Admin page to be added

        databaseRef = FirebaseDatabase.getInstance().getReference().child("users"); // Fix or verify reference to DB
        logoutBtn = findViewById(R.id.logout_button);;                              // Log out button on admin page to be added
        complaintList = (ListView) findViewById(R.id.listView);                     // ListView on admin page to be added
        complaints = new ArrayList<>();

        actionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add action (set suspension time to cook, then delete complaint)
            }
        });

        dismissBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add ignore button (delete complaint)
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
                    int days = Integer.parseInt(suspendField.getText().toString());
                        suspendCook(id, days);
                        b.dismiss();
                }

            });
            buttonDismiss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        dismissComplaint();
                        b.dismiss();
                }
            });

        }

    @Override
    protected void onStart(){
            super.onStart();
            // Add onStart implementation; update listView to have DataSnapshot update list of complaints

    }

    private void suspendCook(String id, int days){
        // Deal with suspending cook
        dismissComplaint();
    }

    private void dismissComplaint(){
        // Deal with removing the complaint from the database
    }

    */

}
