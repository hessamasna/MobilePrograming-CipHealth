package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.cip.ciphealth.adapter.FoodRecipeSocialAdapter;
import com.cip.ciphealth.adapter.HealthTipsAdapter;
import com.cip.ciphealth.db.AppDatabase;

public class FoodRecipeSocial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_recipe_social);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.food_recipe_social_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FoodRecipeSocialAdapter foodRecipeSocialAdapter = new FoodRecipeSocialAdapter(this, db.foodRecipeDao().getAllFoodRecipes(), db);
        recyclerView.setAdapter(foodRecipeSocialAdapter);
    }
}