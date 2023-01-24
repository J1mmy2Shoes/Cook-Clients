package com.example.deliverable_1_user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.deliverable_1_user_interface.data.LoginDataSource;
import com.example.deliverable_1_user_interface.ui.login.LoginActivity;

public class welcomePage extends AppCompatActivity {

    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        logoutButton = (Button) findViewById(R.id.logoutBtn);

        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                logOutPage();
            }
        });
    }

    public void logOutPage() {
        LoginDataSource.logout();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}