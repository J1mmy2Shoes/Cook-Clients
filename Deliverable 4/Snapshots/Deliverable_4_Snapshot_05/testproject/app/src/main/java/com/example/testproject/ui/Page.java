package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.testproject.R;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Page extends AppCompatActivity {
    DatabaseReference databaseRef;
    String cookId;
    ListView orders;
    List<Request> requests;

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

        orders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /*
                Request request = requests.get(i);
                showRequest(request.getId(), request.getMealName(), request.getCook(),
                            request.getClient());


                 */

            }
        });
    }
}