package com.cip.ciphealth.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.ciphealth.model.CaloriesTracker;

import java.util.List;


@Dao
public interface CaloriesDao {
    @Query("SELECT * FROM caloriestracker ")
    List<CaloriesTracker> getAllCaloriesTracker();

    @Insert
    void insertCaloriesTracker(CaloriesTracker... CaloriesTrackers);

    @Update
    void update(CaloriesTracker caloriesTracker);

    @Query("DELETE FROM caloriestracker")
    void deleteAll();

    @Query("SELECT * FROM caloriestracker WHERE user_ID=:id AND date=:date")
    List<CaloriesTracker> getAllCaloriesTrackerByUserId(int id,String date);
}
