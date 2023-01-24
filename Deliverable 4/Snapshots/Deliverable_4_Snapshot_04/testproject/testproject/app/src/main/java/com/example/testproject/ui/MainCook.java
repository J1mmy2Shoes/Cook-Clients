package com.example.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainCook extends AppCompatActivity {
    private Button logoutButton, offersButton, addButton, deleteButton;
    DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    String cookId;
    ListView menu;
    List<Meal> meals;
    FirebaseUser User;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cook);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");

        offersButton = findViewById(R.id.OfferedListBtn);
        logoutButton = findViewById(R.id.cook_logout_button);
        addButton = findViewById(R.id.AddMealBtn);
        menu = (ListView) findViewById(R.id.listViewTable);
        meals = new ArrayList<>();




        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                cookId = null;
            } else {
                cookId = extras.getString("Id");
            }
        } else {
            cookId= (String) savedInstanceState.getSerializable("Id");
        }



        offersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToOffers = new Intent(MainCook.this, OfferedCookPage.class);
                goToOffers.putExtra("Id", cookId);
                startActivity(goToOffers);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backOut = new Intent(MainCook.this, MainPage.class);
                backOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                FirebaseAuth.getInstance().signOut();
                startActivity(backOut);
            }
        });

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal meal = meals.get(i);
                showMeal(meal.getId(),meal.getMealName(), meal.getMealType(),
                        meal.getCuisineType(), meal.getIngredients(),
                        meal.getAllergies(), meal.getPrice(), meal.getDescription(), meal.isOffered());
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goToOffers = new Intent(MainCook.this, addMeal.class);
                startActivity(goToOffers);
            }

        });

    }

    private void showMeal(final String id, String meal, String type, String cuisine,
                          String ingredients, String allergens, String price,
                          String desc, final Boolean offered){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_meal_view, null);
        dialogBuilder.setView(dialogView);

        final TextView mealName = (TextView) dialogView.findViewById(R.id.textMealName);
        final TextView mealType = (TextView) dialogView.findViewById(R.id.textMealType);
        final TextView cuisineType = (TextView) dialogView.findViewById(R.id.textCuisineType);
        final TextView ingredientList = (TextView) dialogView.findViewById(R.id.textIngredients);
        final TextView allergies = (TextView) dialogView.findViewById(R.id.textAllergens);
        final TextView mealPrice = (TextView) dialogView.findViewById(R.id.textPrice);
        final TextView description = (TextView) dialogView.findViewById(R.id.textDescription);

        final Button buttonAddToOffered = (Button) dialogView.findViewById(R.id.OfferedBtn);
        final Button deleteButton = (Button) dialogView.findViewById(R.id.deleteBtn);

        mealName.setText(meal);
        mealType.setText(type);
        cuisineType.setText(cuisine);
        ingredientList.setText(ingredients);
        allergies.setText(allergens);
        mealPrice.setText(price);
        description.setText(desc);
        dialogBuilder.setTitle("MEAL DETAILS");

        final AlertDialog b = dialogBuilder.create();
        b.show();

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeal(id, offered);
                b.dismiss();

            }
        });

        buttonAddToOffered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToOffered(id, offered);


                b.dismiss();

            }
        });



    }

    @Override
    protected void onStart(){
        super.onStart();

        //databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {

                meals.clear();
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    String currentUser = postSnapshot.getKey();
                    currentUser = dataSnapshot.getValue().toString();
                    Meal holder = postSnapshot.getValue(Meal.class);


                    meals.add(holder);



                    MenuList mealAdapter = new MenuList(MainCook.this, meals);
                    menu.setAdapter(mealAdapter);
                }


            }

            @Override
            public void onCancelled( @androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });

    }


    private void deleteMeal(String userid, boolean test){


        databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");


        if(test == false){
            databaseRef.child(userid).removeValue();
        }
        else{
            Toast.makeText(MainCook.this, "you cannot delete a meal that is being offered", Toast.LENGTH_SHORT).show();
        }

    }

    private void addToOffered(String userid, boolean test){

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");

        if(test == true){
            Toast.makeText(MainCook.this, "This meal is already being offered", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseRef.child(userid).child("offered").setValue(true);
        }




    }

        // Main cook UI ADD button and REMOVE button needs to be implemented
        // Firebase integration

}
