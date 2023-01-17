package com.cip.ciphealth.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WeightTracker {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "user_ID")
    private int userID;
    @ColumnInfo(name = "weight")
    private String weight;
    @ColumnInfo(name = "date")
    private String date;

    public WeightTracker(int userID, String weight, String date) {
        this.userID = userID;
        this.weight = weight;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeightTracker{" +
                "ID=" + ID +
                ", userID=" + userID +
                ", weight='" + weight + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
