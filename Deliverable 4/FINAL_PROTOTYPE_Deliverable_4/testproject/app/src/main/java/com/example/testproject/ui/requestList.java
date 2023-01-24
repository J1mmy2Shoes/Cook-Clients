package com.example.testproject.ui;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testproject.R;

import java.util.List;

public class requestList extends ArrayAdapter<Request> {
    private Activity context;
    List<Request> reqs;

    public requestList(Activity context, List<Request> reqs) {
        super(context, R.layout.activity_request_list, reqs);
        this.context = context;
        this.reqs = reqs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_request_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textView7);
        TextView textViewStatus = (TextView) listViewItem.findViewById(R.id.textView6);


        Request request = reqs.get(position);
        textViewName.setText(request.getMealName());
        textViewStatus.setText(request.getStatus());

        return listViewItem;
    }
}
