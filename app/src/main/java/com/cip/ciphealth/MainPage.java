package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.cip.ciphealth.adapter.HealthTipsAdapter;
import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.LoggedInUser;
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
                return value+" kg";
            }
        });

        lineChart.animateY(5000);
        lineChart.getDescription().setText("weight Tracker");
        lineChart.getDescription().setTextColor(Color.WHITE);
        lineChart.setBorderColor(Color.WHITE);
        lineChart.getLegend().setEnabled(false);



    }
    public void RecordWeight(View v) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        Date date = new Date();
        EditText weight = (EditText) findViewById(R.id.weight_tracker_input);
        int day = date.getDate();
        list.add(new Entry((float) day, Float.parseFloat(weight.getText().toString())));

        lineDataSet = new LineDataSet(list,"Weight");
        lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        lineDataSet.setDrawValues(false);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.notifyDataSetChanged(); // let the chart know it's data changed
        lineChart.invalidate();

        WeightTracker weightTracker = new WeightTracker(LoggedInUser.getLoggedInUser().getUser().getID(),weight.getText().toString(),String.valueOf(date.getDate()));
        db.weightDao().insertWeightTracker(weightTracker);

    }
}