package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cip.ciphealth.adapter.FoodRecipeSocialAdapter;
import com.cip.ciphealth.adapter.HealthTipsAdapter;
import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.FoodRecipe;
import com.cip.ciphealth.model.LoggedInUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class FoodRecipeSocial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recipe_social);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
//        FoodRecipe foodRecipe = new FoodRecipe("Food Recipe", "Meet & pizzaMeet & pizzaMeet & pizzaMeet & pizzaMeet & pizza", "2022", "admin");
//        db.foodRecipeDao().insertFoodRecipe(foodRecipe);
        RecyclerView recyclerView = findViewById(R.id.food_recipe_social_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FoodRecipeSocialAdapter foodRecipeSocialAdapter = new FoodRecipeSocialAdapter(this, db.foodRecipeDao().getAllFoodRecipes(), db);
        recyclerView.setAdapter(foodRecipeSocialAdapter);
    }

    public void showPopup(View v) {
        //for add new recipe

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.add_food_recipe_popup);
        TextView add_recipe_title, add_recipe_text, add_recipe_share_btn, add_recipe_close_btn, add_recipe_error_message;

        add_recipe_title = (EditText) myDialog.findViewById(R.id.add_recipe_title);
        add_recipe_text = (EditText) myDialog.findViewById(R.id.add_recipe_text);
        add_recipe_error_message = (TextView) myDialog.findViewById(R.id.add_recipe_error_message);
        add_recipe_share_btn = (Button) myDialog.findViewById(R.id.add_recipe_share_btn);
        add_recipe_close_btn = (TextView) myDialog.findViewById(R.id.add_recipe_close_btn);

        add_recipe_share_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_recipe_title.getText().toString().equals("") || add_recipe_text.getText().toString().equals("")) {
                    add_recipe_error_message.setText("ERROR: FILL INPUTS");
                    return;
                }
                Date date = new Date();
                FoodRecipe foodRecipe = new FoodRecipe(add_recipe_title.getText().toString(), add_recipe_text.getText().toString(), String.valueOf(date.getDate()), LoggedInUser.getLoggedInUser().getUser().getName());
                db.foodRecipeDao().insertFoodRecipe(foodRecipe);

            }
        });


        add_recipe_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }
}