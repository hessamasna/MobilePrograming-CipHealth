package com.cip.ciphealth.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.ciphealth.model.FoodRecipe;

import java.util.List;


@Dao
public interface FoodRecipeDao {
    @Query("SELECT * FROM foodrecipe ")
    List<FoodRecipe> getAllFoodRecipes();

    @Insert
    void insertFoodRecipe(FoodRecipe... foodRecipes);

    @Update
    void update(FoodRecipe foodRecipe);

    @Query("DELETE FROM foodrecipe")
    void deleteAll();
}
