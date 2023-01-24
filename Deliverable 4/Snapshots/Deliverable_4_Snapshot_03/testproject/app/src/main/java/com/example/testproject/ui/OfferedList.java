package com.example.testproject.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.testproject.R;

import java.util.List;

public class OfferedList extends ArrayAdapter<Meal> {
    private Activity context;
    List<Meal> meals;

    public OfferedList(Activity context, List<Meal> meals) {
        super(context, R.layout.activity_meal_list, meals);
        this.context = context;
        this.meals = meals;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_meal_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewMealName);
        TextView textViewType = (TextView) listViewItem.findViewById(R.id.textViewMealType);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewMealPrice);

        Meal meal = meals.get(position);
        textViewName.setText(meal.getMealName());
        textViewType.setText(meal.getMealType());
        textViewPrice.setText(meal.getPrice());
        return listViewItem;
    }
}
