package com.cip.ciphealth.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cip.ciphealth.R;
import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.FoodRecipe;

import java.util.List;

public class FoodRecipeSocialAdapter extends RecyclerView.Adapter<FoodRecipeSocialAdapter.FoodRecipeSocialHolder> {

    private Context context;
    private List<FoodRecipe> foodRecipeList;
    private AppDatabase db;


    public FoodRecipeSocialAdapter(Context context, List<FoodRecipe> foodRecipeList, AppDatabase db) {
        this.context = context;
        this.foodRecipeList = foodRecipeList;
        this.db = db;
    }

    @NonNull
    @Override
    public FoodRecipeSocialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_recipe_item, parent, false);
        return new FoodRecipeSocialHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecipeSocialHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodRecipe foodRecipe = foodRecipeList.get(position);
        holder.setData(foodRecipe);
    }

    @Override
    public int getItemCount() {
        return foodRecipeList.size();
    }

    /////////////

    class FoodRecipeSocialHolder extends RecyclerView.ViewHolder {

        private TextView food_recipe_title, food_recipe_author, food_recipe_date, food_recipe_like, food_recipe_btn;


        public FoodRecipeSocialHolder(@NonNull View itemView) {
            super(itemView);
            food_recipe_title = itemView.findViewById(R.id.food_recipe_title);
            food_recipe_author = itemView.findViewById(R.id.food_recipe_author);
            food_recipe_date = itemView.findViewById(R.id.health_tips_date);
            food_recipe_like = itemView.findViewById(R.id.food_recipe_like);
            food_recipe_btn = itemView.findViewById(R.id.food_recipe_btn);
        }

        public void setData(FoodRecipe foodRecipe) {
            food_recipe_title.setText(foodRecipe.getRecipeTitle());
            food_recipe_author.setText(foodRecipe.getRecipeAuthor());
            food_recipe_date.setText(foodRecipe.getTipsDate());
            food_recipe_like.setText(foodRecipe.getRecipeLikes());
            food_recipe_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(v, foodRecipe);
                }
            });
        }
    }

    public void showPopup(View v, FoodRecipe foodRecipe) {

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
    }
}
