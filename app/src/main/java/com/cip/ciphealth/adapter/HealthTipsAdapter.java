package com.cip.ciphealth.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cip.ciphealth.R;
import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.HealthTips;

import java.util.List;

public class HealthTipsAdapter extends RecyclerView.Adapter<HealthTipsAdapter.HealthTipsHolder> {

    private Context context;
    private List<HealthTips> healthTipsList;
    private AppDatabase db;


    public HealthTipsAdapter(Context context, List<HealthTips> healthTipsList, AppDatabase db) {
        this.context = context;
        this.healthTipsList = healthTipsList;
        this.db = db;
    }

    @NonNull
    @Override
    public HealthTipsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.health_tips_item, parent, false);
        return new HealthTipsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthTipsHolder holder, @SuppressLint("RecyclerView") int position) {
        HealthTips healthTips = healthTipsList.get(position);
        holder.setData(healthTips);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                showPopup(v, UserArrayList.get(position));
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return healthTipsList.size();
    }

    /////////////

    class HealthTipsHolder extends RecyclerView.ViewHolder {

        private TextView health_tips_title, health_tips_message, health_tips_date, health_tips_author;
        private Button health_tips_btn;


        public HealthTipsHolder(@NonNull View itemView) {
            super(itemView);
            health_tips_title = itemView.findViewById(R.id.health_tips_title);
            health_tips_message = itemView.findViewById(R.id.health_tips_message);
            health_tips_date = itemView.findViewById(R.id.health_tips_date);
            health_tips_author = itemView.findViewById(R.id.health_tips_author);
            health_tips_btn = itemView.findViewById(R.id.health_tips_btn);
        }

        public void setData(HealthTips healthTips) {
            health_tips_title.setText(healthTips.getTipsTitle());
            health_tips_message.setText(healthTips.getTipsText());
            health_tips_date.setText(healthTips.getTipsDate());
            health_tips_author.setText("Author: Admin");
            health_tips_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(healthTips.getTipsLink()));
                    context.startActivity(browserIntent);
                }
            });
        }
    }

//    public void showPopup(View v, User user) {
//
//        TextView popup_username;
//        TextView popup_email;
//        TextView popup_phone;
//        Button cancelBtn;
//
//        Dialog myDialog = new Dialog(context);
//        myDialog.setContentView(R.layout.scoreboard_popup);
//        myDialog.getWindow().setBackgroundDrawableResource(R.drawable.shap);
//
//        popup_username = (TextView) myDialog.findViewById(R.id.popup_userame);
//        popup_email = (TextView) myDialog.findViewById(R.id.popup_email);
//        popup_phone = (TextView) myDialog.findViewById(R.id.popup_phone);
//        cancelBtn = (Button) myDialog.findViewById(R.id.cancelBtn);
//
//        popup_username.setText(user.getName());
//        popup_email.setText(user.getEmail());
//        popup_phone.setText(user.getPhone());
//
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myDialog.dismiss();
//            }
//        });
//        myDialog.show();
//    }
}
