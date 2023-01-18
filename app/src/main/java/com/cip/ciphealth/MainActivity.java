package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3*1000);

                    Intent i=new Intent(getBaseContext(),Login.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }
}