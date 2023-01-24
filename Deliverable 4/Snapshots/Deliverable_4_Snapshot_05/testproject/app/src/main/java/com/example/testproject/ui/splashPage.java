package com.example.testproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testproject.R;
import com.example.testproject.users.Cook;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class splashPage extends AppCompatActivity {

    EditText textViewName,textViewType, textViewCuisine;

    private Button logoutButton;
    private Button searchButton;
    private Button orderButton;

    DatabaseReference databaseRef;
    DatabaseReference databaseRefCooks;
    DatabaseReference DRef;
    String cookId;
    ListView menu;
    List<Meal> meals;
    Button purchaseBtn;
    TextView textMealName, textMealType5;
    FirebaseAuth auth;
    FirebaseUser Client;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        Intent intent = getIntent();
        String typeOfUser = intent.getStringExtra("key");

        textViewName = findViewById(R.id.searchMealName);
        textViewType = findViewById(R.id.searchMealType);
        textViewCuisine = findViewById(R.id.searchCuisineType);

        textMealName = findViewById(R.id.textMealName);
        textMealType5 = findViewById(R.id.textMealType5);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Menu");
        databaseRefCooks = FirebaseDatabase.getInstance().getReference().child("users");
        DRef= FirebaseDatabase.getInstance().getReference().child("Requests");

        final String idnew = DRef.push().getKey();

        auth = FirebaseAuth.getInstance();

        menu = (ListView) findViewById(R.id.listViewTable);
        meals = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            cookId = null;
        } else {
            cookId = extras.getString("Id");
        }


        searchButton = findViewById(R.id.SearchBtn);
        logoutButton = findViewById(R.id.logoutBtn2);
        orderButton = findViewById(R.id.Orders);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent backOut = new Intent(splashPage.this, MainPage.class);
                backOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                FirebaseAuth.getInstance().signOut();
                startActivity(backOut);
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){


                final String name = textViewName.getText().toString().trim();
                final String type = textViewType.getText().toString().trim();
                final String cuisine = textViewCuisine.getText().toString().trim();



                databaseRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull final DataSnapshot dataSnapshot) {

                        meals.clear();
                        for(final DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                            final Meal holder = postSnapshot.getValue(Meal.class);

                            if(holder.getMealName().equals(name) || holder.getMealType().equals(type) || holder.getCuisineType().equals(cuisine)){
                                meals.add(holder);
                            }

                            MenuList mealAdapter = new MenuList(splashPage.this, meals);
                            menu.setAdapter(mealAdapter);
                        }
                    }
                    @Override
                    public void onCancelled( @androidx.annotation.NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Meal meal = meals.get(i);



                databaseRefCooks.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String cookID = meal.getCook();

                        String firstname = snapshot.child(cookID).child("firstName").getValue().toString();
                        String lasttname = snapshot.child(cookID).child("lastName").getValue().toString();
                        String rating = snapshot.child(cookID).child("rating").getValue().toString();
                        String description = snapshot.child(cookID).child("description").getValue().toString();
                        String email = snapshot.child(cookID).child("email").getValue().toString();

                        showMeal(meal.getId(),meal.getMealName(), meal.getMealType(),
                                meal.getCuisineType(), meal.getIngredients(),
                                meal.getAllergies(), meal.getPrice(),
                                meal.getDescription(),
                                firstname + " " + lasttname, description, rating, email ,meal.getCook() ,idnew, meal.isOffered());


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                //Toast.makeText(splashPage.this, name, Toast.LENGTH_SHORT).show();

            }
        });

        orderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(splashPage.this, clientOrders.class);

                startActivity(intent);

            }

        });

    }

    private void showMeal(final String id, String meal, String type, String cuisine,
                          String ingredients, String allergens, String price,
                          String desc, String name, String Cdescript, String rating, String email, final String CookID, final String realID, final Boolean offered){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.activity_chefmeal_view, null);
        dialogBuilder.setView(dialogView);

        final TextView mealName = (TextView) dialogView.findViewById(R.id.textMealName);
        final TextView ingredientList = (TextView) dialogView.findViewById(R.id.textIngredients);
        final TextView allergies = (TextView) dialogView.findViewById(R.id.textAllergens);
        final TextView mealPrice = (TextView) dialogView.findViewById(R.id.textPrice);
        final TextView description = (TextView) dialogView.findViewById(R.id.textDescription);

        final TextView chefName = (TextView) dialogView.findViewById(R.id.textMealType5);
        final TextView chefRating = (TextView) dialogView.findViewById(R.id.textMealType3);
        final TextView chefDescription = (TextView) dialogView.findViewById(R.id.textMealType4);
        final TextView chefEmail = (TextView) dialogView.findViewById(R.id.textMealType6);

        final Button purchaseBtn = (Button) dialogView.findViewById(R.id.button);


        mealName.setText(meal);
        ingredientList.setText(ingredients);
        allergies.setText(allergens);
        mealPrice.setText(price);
        description.setText(desc);

        chefName.setText(name);
        chefRating.setText(rating);
        chefDescription.setText(Cdescript);
        chefEmail.setText(email);

        dialogBuilder.setTitle("MEAL DETAILS");

        final AlertDialog b = dialogBuilder.create();
        b.show();

        Client = auth.getCurrentUser();
        //Toast.makeText(getApplicationContext(),Client.getUid(),Toast.LENGTH_SHORT).show();

        final String Meal_name = mealName.getText().toString().trim();

        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final String Meal_name = textMealName.getText().toString().trim();
                //Client = auth.getCurrentUser();

                NewRequest(Meal_name, CookID, chefName.getText().toString(), Client.getUid(), realID);

                //Toast.makeText(getApplicationContext(),chefName.getText().toString(),Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(),"Your order has been placed",Toast.LENGTH_SHORT).show();

                b.dismiss();

            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull final DataSnapshot dataSnapshot) {

                meals.clear();
                for(final DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    final Meal holder = postSnapshot.getValue(Meal.class);


                    //Broken code to only add to menu if the cook is not suspended
                    /*
                    databaseRefCooks.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@androidx.annotation.NonNull final DataSnapshot snapshot) {
                            for (DataSnapshot postSnapshot2 : snapshot.getChildren()){
                                String userid = postSnapshot2.getKey().toString();
                                Toast.makeText(splashPage.this, userid, Toast.LENGTH_SHORT).show();
                                if(postSnapshot2.child("role").equals("COOK")) {
                                    //Toast.makeText(splashPage.this, postSnapshot2.getValue().toString(), Toast.LENGTH_SHORT).show();

                                    if (userid.equals(holder.getCook()) && postSnapshot2.child("accStatus").getValue().toString().equals("Active")) {

                                        meals.add(holder);
                                    }
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    */



                    meals.add(holder);


                    MenuList mealAdapter = new MenuList(splashPage.this, meals);
                    menu.setAdapter(mealAdapter);
                }


            }

            @Override
            public void onCancelled( @androidx.annotation.NonNull DatabaseError databaseError) {

            }
        });

    }
    private void NewRequest(String Name, String CookId, String cookName, String ClientId,String UserId){
            Request request = new Request(Name,CookId, cookName, ClientId);
            DRef.child(UserId).setValue(request);
        }
}