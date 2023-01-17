package com.cip.ciphealth.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Diet {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "diet_name")
    private String dietName;
    @ColumnInfo(name = "diet_goal")
    private String dietGoal;
    @ColumnInfo(name = "diet_text")
    private String dietText;

    public Diet(String dietName, String dietGoal, String dietText) {
        this.dietName = dietName;
        this.dietGoal = dietGoal;
        this.dietText = dietText;
    }

    public String getDietGoal() {
        return dietGoal;
    }

    public void setDietGoal(String dietGoal) {
        this.dietGoal = dietGoal;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDietText() {
        return dietText;
    }

    public void setDietText(String dietText) {
        this.dietText = dietText;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "ID=" + ID +
                ", dietName='" + dietName + '\'' +
                ", dietText='" + dietText + '\'' +
                '}';
    }
}
