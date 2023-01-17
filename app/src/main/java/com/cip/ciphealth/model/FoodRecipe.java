package com.cip.ciphealth.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FoodRecipe {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "recipe_title")
    private String recipeTitle;
    @ColumnInfo(name = "recipe_text")
    private String recipeText;
    @ColumnInfo(name = "recipe_likes")
    private int recipeLikes;
    @ColumnInfo(name = "recipe_date")
    private String tipsDate;
    @ColumnInfo(name = "recipe_author")
    private String recipeAuthor;

    public FoodRecipe(String recipeTitle, String recipeText, String tipsDate, String recipeAuthor) {
        this.recipeTitle = recipeTitle;
        this.recipeText = recipeText;
        this.tipsDate = tipsDate;
        this.recipeAuthor = recipeAuthor;
        this.recipeLikes = 0;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }

    public String getRecipeText() {
        return recipeText;
    }

    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }

    public int getRecipeLikes() {
        return recipeLikes;
    }

    public void setRecipeLikes(int recipeLikes) {
        this.recipeLikes = recipeLikes;
    }

    public String getTipsDate() {
        return tipsDate;
    }

    public void setTipsDate(String tipsDate) {
        this.tipsDate = tipsDate;
    }

    public String getRecipeAuthor() {
        return recipeAuthor;
    }

    public void setRecipeAuthor(String recipeAuthor) {
        this.recipeAuthor = recipeAuthor;
    }

    @Override
    public String toString() {
        return "FoodRecipe{" +
                "ID=" + ID +
                ", recipeTitle='" + recipeTitle + '\'' +
                ", recipeText='" + recipeText + '\'' +
                ", recipeLikes='" + recipeLikes + '\'' +
                ", tipsDate='" + tipsDate + '\'' +
                ", recipeAuthor='" + recipeAuthor + '\'' +
                '}';
    }
}
