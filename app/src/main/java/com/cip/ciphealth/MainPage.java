package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cip.ciphealth.adapter.HealthTipsAdapter;
import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.CaloriesTracker;
import com.cip.ciphealth.model.Diet;
import com.cip.ciphealth.model.FoodRecipe;
import com.cip.ciphealth.model.LoggedInUser;
import com.cip.ciphealth.model.User;
import com.cip.ciphealth.model.WeightTracker;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainPage extends AppCompatActivity {
    LineChart lineChart;
    ArrayList<Entry> list = new ArrayList<>();
    LineDataSet lineDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.health_tips_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        HealthTipsAdapter healthTipsAdapter = new HealthTipsAdapter(this, db.healthTipsDao().getAllHealthTips(), db);
        recyclerView.setAdapter(healthTipsAdapter);


        lineChart = findViewById(R.id.chart1);
        List<WeightTracker> weightTrackers = db.weightDao().getAllWeightTrackersOfUser(LoggedInUser.getLoggedInUser().getUser().getID());
        for (WeightTracker tracker : weightTrackers) {
            list.add(new Entry(Float.parseFloat(tracker.getDate()), Float.parseFloat(tracker.getWeight())));
        }


        lineDataSet = new LineDataSet(list, "Weight Tracker");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setDrawValues(false);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.getXAxis().setAxisMinimum(0);
        lineChart.getXAxis().setAxisMaximum(30);
        lineChart.getXAxis().setTextColor(Color.WHITE);
        lineChart.getXAxis().setAxisLineColor(Color.WHITE);
        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getAxisLeft().setTextColor(Color.WHITE);
        lineChart.getAxisLeft().setAxisMinimum(0);
        lineChart.getAxisLeft().setAxisMaximum(210);
        lineChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return value + " kg";
            }
        });

        lineChart.animateY(5000);
        lineChart.getDescription().setText("weight Tracker");
        lineChart.getDescription().setTextColor(Color.WHITE);
        lineChart.setBorderColor(Color.WHITE);
        lineChart.getLegend().setEnabled(false);

        UpdateCalorie();
    }

    public void RecordWeight(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Date date = new Date();
        EditText weight = (EditText) findViewById(R.id.weight_tracker_input);
        int day = date.getDate();
        list.add(new Entry((float) day, Float.parseFloat(weight.getText().toString())));

        lineDataSet = new LineDataSet(list, "Weight");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setDrawValues(false);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.notifyDataSetChanged(); // let the chart know it's data changed
        lineChart.invalidate();

        WeightTracker weightTracker = new WeightTracker(LoggedInUser.getLoggedInUser().getUser().getID(), weight.getText().toString(), String.valueOf(date.getDate()));
        db.weightDao().insertWeightTracker(weightTracker);

    }

    public void UpdateCalorie() {
        TextView calorie = (TextView) findViewById(R.id.calorie_result);
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Date date = new Date();

        List<CaloriesTracker> caloriesTrackers = db.caloriesDao().getAllCaloriesTrackerByUserId(LoggedInUser.getLoggedInUser().getUser().getID(), String.valueOf(date.getDate()));

        int sum = 0;
        for (CaloriesTracker tracker : caloriesTrackers) {
            sum += Integer.parseInt(tracker.getCalorie());
        }
        calorie.setText("" + sum);
    }

    public void RecordCalorie(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        EditText calorie = (EditText) findViewById(R.id.calorie_tracker_input2);
        Date date = new Date();

        CaloriesTracker caloriesTracker = new CaloriesTracker(LoggedInUser.getLoggedInUser().getUser().getID(), calorie.getText().toString(), "", String.valueOf(date.getDate()));
        db.caloriesDao().insertCaloriesTracker(caloriesTracker);
        UpdateCalorie();
    }

    public void GotoRecipeSocial(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), FoodRecipeSocial.class
        );
        startActivity(secondActivityIntent);
    }

    public void showPopupDiet(View v) {
        //for add new recipe

        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Dialog myDialog = new Dialog(this);
        myDialog.setContentView(R.layout.diet_popup);
        TextView diet_popup_title, diet_popup_goal, diet_popup_plan, diet_close, diet_link;

        Diet diet = db.dietDao().getDietWithId(LoggedInUser.getLoggedInUser().getUser().getDietID()).get(0);
        diet_popup_title = (TextView) myDialog.findViewById(R.id.diet_popup_title);
        diet_popup_goal = (TextView) myDialog.findViewById(R.id.diet_popup_goal);
        diet_popup_plan = (TextView) myDialog.findViewById(R.id.diet_popup_plan);
        diet_link = (TextView) myDialog.findViewById(R.id.diet_link);
        diet_close = (TextView) myDialog.findViewById(R.id.diet_close);

        diet_popup_title.setText(diet.getDietName());
        diet_popup_goal.setText(diet.getDietGoal());
        diet_popup_plan.setText(diet.getDietText());

        diet_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        diet_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(diet.getDietLink()));
                MainPage.this.startActivity(browserIntent);
            }
        });

        myDialog.show();
    }

    public void GotoProfile(View v) {
        Intent secondActivityIntent = new Intent(
                getApplicationContext(), Profile.class
        );
        startActivity(secondActivityIntent);
    }
}