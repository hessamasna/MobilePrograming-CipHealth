package com.cip.ciphealth.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HealthTips {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    @ColumnInfo(name = "tips_title")
    private String tipsTitle;
    @ColumnInfo(name = "tips_text")
    private String tipsText;
    @ColumnInfo(name = "tips_link")
    private String tipsLink;
    @ColumnInfo(name = "tips_date")
    private String tipsDate;

    public HealthTips(String tipsTitle, String tipsText, String tipsLink, String tipsDate) {
        this.tipsTitle = tipsTitle;
        this.tipsText = tipsText;
        this.tipsLink = tipsLink;
        this.tipsDate = tipsDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTipsTitle() {
        return tipsTitle;
    }

    public void setTipsTitle(String tipsTitle) {
        this.tipsTitle = tipsTitle;
    }

    public String getTipsText() {
        return tipsText;
    }

    public void setTipsText(String tipsText) {
        this.tipsText = tipsText;
    }

    public String getTipsLink() {
        return tipsLink;
    }

    public void setTipsLink(String tipsLink) {
        this.tipsLink = tipsLink;
    }

    public String getTipsDate() {
        return tipsDate;
    }

    public void setTipsDate(String tipsDate) {
        this.tipsDate = tipsDate;
    }

    @Override
    public String toString() {
        return "HealthTips{" +
                "ID=" + ID +
                ", tipsTitle='" + tipsTitle + '\'' +
                ", tipsText='" + tipsText + '\'' +
                ", tipsLink='" + tipsLink + '\'' +
                ", tipsDate='" + tipsDate + '\'' +
                '}';
    }
}
