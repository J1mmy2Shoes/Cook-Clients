package com.example.testproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
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

public class OfferedCookPage extends AppCompatActivity {
    private Button backButton, addButton, deleteButton;
    DatabaseReference databaseRef;
    private FirebaseAuth mAuth;
    String cookId;
    ListView menu;
    List<Meal> meals;
    FirebaseUser User;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_list);

        menu = (ListView) findViewById(R.id.listViewOffers);
        meals = new ArrayList<>();

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent goBack = new Intent(OfferedCookPage.this, MainCook.class);
                goBack.putExtra("Id", cookId);
                startActivity(goBack);
                startActivity(goBack);
            }
        });

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Meal meal = meals.get(i);
                if (meal.isOffered()) {
                    showMeal(meal.getId(), meal.getMealName(), meal.getMealType(),
                            meal.getCuisineType(), meal.getIngredients(),
                            meal.getAllergies(), meal.getPrice(), meal.getDescription());
                }
            }
        });
    }

        private void showMeal(final String id, final String meal, final String type, final String cuisine,
                              final String ingredients, final String allergens, final String price,
                              final String desc){

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.activity_offered_view, null);
            dialogBuilder.setView(dialogView);

            final TextView mealName = (TextView) dialogView.findViewById(R.id.textMealName);
            final TextView mealType = (TextView) dialogView.findViewById(R.id.textMealType);
            final TextView cuisineType = (TextView) dialogView.findViewById(R.id.textCuisineType);
            final TextView ingredientList = (TextView) dialogView.findViewById(R.id.textIngredients);
            final TextView allergies = (TextView) dialogView.findViewById(R.id.textAllergens);
            final TextView mealPrice = (TextView) dialogView.findViewById(R.id.textPrice);
            final TextView description = (TextView) dialogView.findViewById(R.id.textDescription);

            final Button buttonRemoveOffered = (Button) dialogView.findViewById(R.id.OfferedBtn);

            mealName.setText(meal);
            mealType.setText(type);
            cuisineType.setText(cuisine);
            ingredientList.setText(ingredients);
            allergies.setText(allergens);
            mealPrice.setText(price);
            description.setText(desc);
            dialogBuilder.setTitle("OFFERED MEAL DETAILS");

            final AlertDialog b = dialogBuilder.create();
            b.show();

            buttonRemoveOffered.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeOffered(id);


                    b.dismiss();

                }
            });

        }

        @Override
        protected void onStart(){
            super.onStart();

            databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");

            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                    meals.clear();
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        String currentUser = postSnapshot.getKey();
                        //String currentUser = dataSnapshot.getValue().toString();
                        Meal meal = postSnapshot.getValue(Meal.class);


                        if (meal.isOffered()) { // Checking to see if the item is offered
                            meals.add(meal);
                            MenuList mealAdapter = new MenuList(OfferedCookPage.this, meals);
                            menu.setAdapter(mealAdapter);
                        }

                    }
                }

                @Override
                public void onCancelled( @androidx.annotation.NonNull DatabaseError databaseError) {

                }
            });

        }

    private void removeOffered(String userid){

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");

        databaseRef.child(userid).child("offered").setValue(false);


    }
    // Offered list UI ADD button and REMOVE button need to be implemented
    // Firebase integration
}
