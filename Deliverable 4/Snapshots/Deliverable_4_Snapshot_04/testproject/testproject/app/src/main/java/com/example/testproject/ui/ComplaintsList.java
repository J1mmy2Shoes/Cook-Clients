package com.example.testproject.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testproject.R;

import java.util.List;

public class ComplaintsList extends ArrayAdapter<Complaint> {
    private Activity context;
    List<Complaint> complaints;

    public ComplaintsList(Activity context, List<Complaint> products) {
        super(context, R.layout.activity_complaintlist, products);
        this.context = context;
        this.complaints = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_complaintlist, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewMealName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewMealType);

        Complaint complaint = complaints.get(position);
        String name = complaint.getFirstName() + " " + complaint.getLastName();
        textViewName.setText(name);
        textViewEmail.setText(complaint.getEmail());
        return listViewItem;
    }
}