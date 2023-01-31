package com.cip.ciphealth.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.FoodRecipe;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static void fetchData(String food, AppDatabase db) {

        String url = "https://api.edamam.com/api/recipes/v2?type=public&app_id=b29116b7&app_key=324052b016205f683e1b82747c5dbe41&random=true&ingr=2";
        url = url + "&q=" + food;

        Request request = new Request.Builder().url(url).build();
        Log.d("Api url: ", url);


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                //todo zamani ke rid
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    Log.d("body", res);
                    readJson(res, db);
                } else {
                    //todo zamani ke rid
                }
            }
        });

    }

    private static void readJson(String recipe, AppDatabase db) {
        Random random = new Random();
        final String regexLabel = "\"uri\":\"((https:\\/\\/|http:\\/\\/)([a-zA-Z0-9.-_]|\\/|\\#|\\$)+)\",\"label\":\"((\\s|\\w)*)\"";
        final Pattern pattern = Pattern.compile(regexLabel, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(recipe);

        while (matcher.find()) {
            FoodRecipe foodRecipe = new FoodRecipe(matcher.group(4), matcher.group(1), "2023", "Edamam");
            foodRecipe.setRecipeLikes(random.nextInt(100));
            Log.d("tag", foodRecipe.toString());
            db.foodRecipeDao().insertFoodRecipe(foodRecipe);
        }

    }

}
