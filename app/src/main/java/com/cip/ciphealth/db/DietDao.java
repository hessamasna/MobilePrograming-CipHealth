package com.cip.ciphealth.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.cip.ciphealth.model.Diet;

import java.util.List;


@Dao
public interface DietDao {
    @Query("SELECT * FROM diet ")
    List<Diet> getAllDiets();

    @Query("SELECT * FROM diet WHERE ID =:id  ")
    List<Diet> getDietWithId(int id);

    @Insert
    void insertDiet(Diet... Diets);

    @Update
    void update(Diet diet);

    @Query("DELETE FROM diet")
    void deleteAll();
}
