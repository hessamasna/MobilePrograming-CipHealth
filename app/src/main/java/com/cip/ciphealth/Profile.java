package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.Diet;
import com.cip.ciphealth.model.LoggedInUser;
import com.cip.ciphealth.model.User;

import java.util.List;

public class Profile extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        User user = LoggedInUser.getLoggedInUser().getUser();

        EditText length = (EditText) findViewById(R.id.length);
        EditText weight = (EditText) findViewById(R.id.weight);
        EditText user_name = (EditText) findViewById(R.id.user_name);
        TextView score_profile = (TextView) findViewById(R.id.score_profile);
        TextView bmi = (TextView) findViewById(R.id.bmi);
        TextView weight_goal = (TextView) findViewById(R.id.weight_goal);
        ProgressBar bmi_progress_bar = (ProgressBar) findViewById(R.id.bmi_progress_bar);

        length.setText("" + user.getLength());
        weight.setText("" + user.getWeight());
        score_profile.setText("" + user.getScore());
        bmi.setText("" + user.getBmi());
        weight_goal.setText("" + user.getWeightGoal());
        user_name.setText(user.getName());
        bmi_progress_bar.setProgress((int) user.getBmi());
        setProgressBarColor((int) user.getBmi());
        setMedal(user.getScore());

        Spinner spinner_cat = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter<CharSequence> adapter_cat = ArrayAdapter.createFromResource(this,
                R.array.gender_choices, android.R.layout.simple_spinner_item);
        adapter_cat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cat.setAdapter(adapter_cat);
    }

    public void calculate(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        EditText length = (EditText) findViewById(R.id.length);
        EditText weight = (EditText) findViewById(R.id.weight);
        TextView bmi = (TextView) findViewById(R.id.bmi);
        TextView weight_goal = (TextView) findViewById(R.id.weight_goal);
        ProgressBar bmi_progress_bar = (ProgressBar) findViewById(R.id.bmi_progress_bar);
        TextView score_profile = (TextView) findViewById(R.id.score_profile);


        User user = LoggedInUser.getLoggedInUser().getUser();

        user.setWeight(Integer.parseInt(weight.getText().toString()));
        user.setLength(Integer.parseInt(length.getText().toString()));
        user.setWeightGoal((int) (Float.parseFloat(length.getText().toString()) % 100));
        float res = Float.parseFloat(weight.getText().toString()) / ((Float.parseFloat(length.getText().toString()) / 100) * (Float.parseFloat(length.getText().toString()) / 100));

        user.setBmi(res);
        Log.d("TAG", "calculate: " + LoggedInUser.getLoggedInUser().getUser().getBmi());
        bmi.setText("" + res);
        weight_goal.setText("" + (Float.parseFloat(length.getText().toString()) % 100));
        bmi_progress_bar.setProgress((int) res);
        if ((int) res > 18 && (int) res < 25) {
            user.setScore(user.getScore() + 50);
            score_profile.setText("" + user.getScore());
        }
        setProgressBarColor((int) res);
        setMedal(user.getScore());
        updateName();

        db.userDao().update(user);
    }

    public void setProgressBarColor(int num) {
        ProgressBar bmi_progress_bar = (ProgressBar) findViewById(R.id.bmi_progress_bar);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Diet> diets = db.dietDao().getAllDiets();
        User user = LoggedInUser.getLoggedInUser().getUser();



        if (num <= 18) {
            bmi_progress_bar.getProgressDrawable().setColorFilter(
                    Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
            user.setDietID(diets.get(0).getID());
        } else if (num < 25) {
            bmi_progress_bar.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            user.setDietID(diets.get(1).getID());
        } else if (num < 30) {
            bmi_progress_bar.getProgressDrawable().setColorFilter(
                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
            user.setDietID(diets.get(2).getID());
        } else {
            bmi_progress_bar.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            user.setDietID(diets.get(3).getID());
        }

        db.userDao().update(user);
    }

    public void setMedal(int score) {
        ImageView profile_medal = (ImageView) findViewById(R.id.profile_medal);

        if (score < 50) {
            profile_medal.setImageResource(R.drawable.bronz);

        } else if (score < 150) {
            profile_medal.setImageResource(R.drawable.silver);

        } else {
            profile_medal.setImageResource(R.drawable.gold);

        }

    }

    public void updateName() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        EditText user_name = (EditText) findViewById(R.id.user_name);

        LoggedInUser.getLoggedInUser().getUser().setName(user_name.getText().toString());

        db.userDao().update(LoggedInUser.getLoggedInUser().getUser());
    }

    public void GotoMainPage(View v) {
        updateName();

        Intent secondActivityIntent = new Intent(
                getApplicationContext(), MainPage.class
        );
        startActivity(secondActivityIntent);
    }
}