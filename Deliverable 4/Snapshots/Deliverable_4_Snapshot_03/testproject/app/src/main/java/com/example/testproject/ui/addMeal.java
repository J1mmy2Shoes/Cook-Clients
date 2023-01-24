package com.example.testproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addMeal extends AppCompatActivity {

    EditText editTextTextPersonName2,
            editTextTextPersonName3,
            editTextTextPersonName4,
            editTextTextPersonName5,
            editTextTextPersonName6,
            editTextTextPersonName7,
            editTextTextPersonName8;
    Button mealBtn, backBtn;
    FirebaseAuth authenticator;
    FirebaseUser user;
    DatabaseReference Dbase;


    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);


        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7);
        editTextTextPersonName8 = findViewById(R.id.editTextTextPersonName8);

        mealBtn = findViewById(R.id.button2);
        backBtn = findViewById(R.id.button1);

        authenticator = FirebaseAuth.getInstance();
        Dbase = FirebaseDatabase.getInstance().getReference("Menu");

        final String id = Dbase.push().getKey();
        user = authenticator.getCurrentUser();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextTextPersonName2.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Meal name cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editTextTextPersonName3.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Meal type cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editTextTextPersonName4.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Cuisine type cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editTextTextPersonName5.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Ingredients cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editTextTextPersonName6.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Allergens cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editTextTextPersonName7.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Price cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(editTextTextPersonName8.getText().toString().trim().equals("")) {
                    Toast.makeText(addMeal.this, "Description cannot be left blank", Toast.LENGTH_SHORT).show();
                    return;
                }

                final String Meal_name = editTextTextPersonName2.getText().toString().trim();
                final String Meal_type = editTextTextPersonName3.getText().toString().trim();
                final String Cuisine_type = editTextTextPersonName4.getText().toString().trim();
                final String Ingredients = editTextTextPersonName5.getText().toString().trim();
                final String Allergens = editTextTextPersonName6.getText().toString().trim();
                final String Price = editTextTextPersonName7.getText().toString().trim();
                final String Description = editTextTextPersonName8.getText().toString().trim();


                newMeal(Meal_name, Meal_type, Cuisine_type, Ingredients, Allergens, Price, Description, id, user.getUid());

                Toast.makeText(addMeal.this, "A meal has been added to the menu", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(addMeal.this, MainCook.class);
                intent.putExtra("Id", user.getUid());
                FirebaseAuth.getInstance().signOut();
                startActivity(intent);


            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addMeal.this, MainCook.class);
                intent.putExtra("Id", user.getUid());
                startActivity(intent);

            }

        });


    }

    private void newMeal(String mealname, String mealtype, String cuisinetype, String ingredients, String allergens, String price, String description, String userID, String CookUserID) {
        Meal meal = new Meal(mealname,mealtype, cuisinetype, ingredients, allergens, price, description, CookUserID, userID);

        Dbase.child(userID).setValue(meal);
    }
}