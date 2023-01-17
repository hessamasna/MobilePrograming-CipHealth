package com.cip.ciphealth.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "isLoggedIn")
    private Boolean isLoggedIn;
    @ColumnInfo(name = "score")
    private int score;
    @ColumnInfo(name = "weight")
    private int weight;
    @ColumnInfo(name = "weight_goal")
    private int weightGoal;
    @ColumnInfo(name = "length")
    private int length;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "dietID")
    private int dietID;
    @ColumnInfo(name = "bmi")
    private int bmi;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.name = "";
        this.phone = "";
        this.isLoggedIn = false;
        this.score = 0;
        this.weight = 0;
        this.length = 0;
        this.gender = "";
        this.dietID = 0;
        this.weightGoal = 0;
        this.bmi = 0;
    }

    public int getBmi() {
        return bmi;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }

    public int getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(int weightGoal) {
        this.weightGoal = weightGoal;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(Boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWeight() {
        return weight;
    }

    public int getDietID() {
        return dietID;
    }

    public void setDietID(int dietID) {
        this.dietID = dietID;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", score=" + score +
                ", weight=" + weight +
                ", weightGoal=" + weightGoal +
                ", length=" + length +
                ", gender='" + gender + '\'' +
                ", dietID=" + dietID +
                ", bmi=" + bmi +
                '}';
    }
}
