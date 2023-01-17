package com.cip.ciphealth.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.cip.ciphealth.model.User;

import java.util.List;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user ")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE email LIKE :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM user WHERE isLoggedIn = 1")
    User getLoggedInUser();

    @Query("SELECT * FROM user ORDER BY score DESC LIMIT 1; ")
    User getMVP();


    @Query("SELECT * FROM user ORDER BY score DESC; ")
    List<User> getUserByScoreOrder();

    @Insert
    void insertUser(User... Users);

    @Update
    void update(User user);

    @Query("DELETE FROM user")
    void deleteAll();
}
