package com.cip.ciphealth.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cip.ciphealth.model.CaloriesTracker;
import com.cip.ciphealth.model.Diet;
import com.cip.ciphealth.model.FoodRecipe;
import com.cip.ciphealth.model.HealthTips;
import com.cip.ciphealth.model.User;
import com.cip.ciphealth.model.WeightTracker;

@Database(entities = {User.class, Diet.class, WeightTracker.class, CaloriesTracker.class, HealthTips.class, FoodRecipe.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract DietDao dietDao();
    public abstract WeightDao weightDao();
    public abstract CaloriesDao caloriesDao();
    public abstract HealthTipsDao healthTipsDao();
    public abstract FoodRecipeDao foodRecipeDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }
}
