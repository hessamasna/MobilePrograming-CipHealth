package com.cip.ciphealth.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cip.ciphealth.model.HealthTips;

import java.util.List;


@Dao
public interface HealthTipsDao {
    @Query("SELECT * FROM healthtips ")
    List<HealthTips> getAllHealthTips();

    @Insert
    void insertHealthTips(HealthTips... healthTips);

    @Update
    void update(HealthTips healthTips);

    @Query("DELETE FROM healthtips")
    void deleteAll();
}
