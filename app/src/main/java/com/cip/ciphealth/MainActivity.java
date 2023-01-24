package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.HealthTips;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        HealthTips healthTips = new HealthTips("test Title","Tips message","https://yahoo.com","2022");
        HealthTips healthTips1 = new HealthTips("test Title1","Tips messageTips messageTips messageTips message","https://yahoo.com","2022");
        HealthTips healthTips2 = new HealthTips("test Title2","Tips message3","https://yahoo1.com","2024");
        db.healthTipsDao().insertHealthTips(healthTips);
        db.healthTipsDao().insertHealthTips(healthTips1);
        db.healthTipsDao().insertHealthTips(healthTips2);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3*1000);

                    Intent i=new Intent(getBaseContext(),MainPage.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}