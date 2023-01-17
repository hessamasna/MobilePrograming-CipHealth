package com.cip.ciphealth.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.ciphealth.model.WeightTracker;

import java.util.List;


@Dao
public interface WeightDao {
    @Query("SELECT * FROM weighttracker ")
    List<WeightTracker> getAllWeightTrackers();

    @Insert
    void insertWeightTracker(WeightTracker... WeightTrackers);

    @Update
    void update(WeightTracker weightTracker);

    @Query("DELETE FROM weighttracker")
    void deleteAll();
}
