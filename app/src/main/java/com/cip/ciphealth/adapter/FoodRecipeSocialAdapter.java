package com.cip.ciphealth.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
            food_recipe_date = itemView.findViewById(R.id.food_recipe_date);
            food_recipe_like = itemView.findViewById(R.id.food_recipe_like);
            food_recipe_btn = itemView.findViewById(R.id.food_recipe_btn);
        }

        public void setData(FoodRecipe foodRecipe) {
            food_recipe_title.setText(foodRecipe.getRecipeTitle());
            food_recipe_author.setText(foodRecipe.getRecipeAuthor());
            food_recipe_date.setText(foodRecipe.getTipsDate());
            food_recipe_like.setText("Likes: " + foodRecipe.getRecipeLikes() + "💘");
            food_recipe_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopup(v, foodRecipe, db);
                }
            });
        }
    }

    public void showPopup(View v, FoodRecipe foodRecipe, AppDatabase db) {

        Dialog myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.food_recipe_popup);
        TextView food_recipe_popup_title, food_recipe_popup_author, food_recipe_popup_date, food_recipe_popup_like, food_recipe_popup_close_btn, food_recipe_popup_message;

        food_recipe_popup_title = (TextView) myDialog.findViewById(R.id.food_recipe_popup_title);
        food_recipe_popup_author = (TextView) myDialog.findViewById(R.id.food_recipe_popup_author);
        food_recipe_popup_date = (TextView) myDialog.findViewById(R.id.food_recipe_popup_date);
        food_recipe_popup_like = (TextView) myDialog.findViewById(R.id.food_recipe_popup_like);
        food_recipe_popup_close_btn = (TextView) myDialog.findViewById(R.id.food_recipe_popup_close_btn);
        food_recipe_popup_message = (TextView) myDialog.findViewById(R.id.food_recipe_popup_message);


        food_recipe_popup_title.setText(foodRecipe.getRecipeTitle());
        food_recipe_popup_author.setText(foodRecipe.getRecipeAuthor());
        food_recipe_popup_date.setText(foodRecipe.getTipsDate());
        food_recipe_popup_like.setText("" + foodRecipe.getRecipeLikes() + "💘");
        food_recipe_popup_message.setText(foodRecipe.getRecipeText());


        food_recipe_popup_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodRecipe.setRecipeLikes(foodRecipe.getRecipeLikes() + 1);
                food_recipe_popup_like.setText("" + foodRecipe.getRecipeLikes() + "💘");
                db.foodRecipeDao().update(foodRecipe);
            }
        });


        food_recipe_popup_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodRecipeList = db.foodRecipeDao().getAllFoodRecipes();
                myDialog.dismiss();
            }
        });

        myDialog.show();
    }
}
