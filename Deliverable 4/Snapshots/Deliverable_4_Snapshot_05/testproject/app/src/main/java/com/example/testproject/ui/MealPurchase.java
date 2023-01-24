package com.example.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MealPurchase extends AppCompatActivity {
    TextView textMealName,textMealType5;

    Button purchaseBtn;
    DatabaseReference DRef;
    FirebaseUser user;
    FirebaseAuth auth;

    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.activity_chefmeal_view);

        purchaseBtn=findViewById(R.id.button);
        textMealName= findViewById(R.id.textMealName);
        textMealType5= findViewById(R.id.textMealType5);

        auth= FirebaseAuth.getInstance();
        DRef= FirebaseDatabase.getInstance().getReference("Request");


        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Meal_name = textMealName.getText().toString().trim();
                final String Cook_name= textMealType5.getText().toString().trim();

                //new Request(Meal_name,Cook_name, cooktst, user.getUid());

                Toast.makeText(getApplicationContext(),"Your order has been placed",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MealPurchase.this,splashPage.class);
                startActivity(intent);
            }
        });
    }


}
