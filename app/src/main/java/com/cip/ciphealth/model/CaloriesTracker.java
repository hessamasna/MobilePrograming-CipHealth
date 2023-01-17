package com.cip.ciphealth.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CaloriesTracker {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "user_ID")
    private int userID;
    @ColumnInfo(name = "calorie")
    private String calorie;
    @ColumnInfo(name = "food_name")
    private String foodName;
    @ColumnInfo(name = "date")
    private String date;

    public CaloriesTracker(int userID, String calorie, String foodName, String date) {
        this.userID = userID;
        this.calorie = calorie;
        this.foodName = foodName;
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CaloriesTracker{" +
                "ID=" + ID +
                ", userID=" + userID +
                ", calorie='" + calorie + '\'' +
                ", foodName='" + foodName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
