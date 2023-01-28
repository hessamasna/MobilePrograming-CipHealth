package com.cip.ciphealth.api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.cip.ciphealth.db.AppDatabase;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Api {
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static void fetchData(String difficulty, int questionsCount, String category, AppDatabase db) {

        String url = "#";
        // todo params
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
                    String[] data = res.split("\"results\":");

                    readJson(data[1].substring(0, data[1].length() - 1), db);
                } else {
                    //todo zamani ke rid
                }
            }
        });

    }

    private static void readJson(String questions, AppDatabase db) {
        Gson gson = new Gson();

        RecipeAPIModel[] questionsList = gson.fromJson(questions, RecipeAPIModel[].class);
        //todo

    }

    public class RecipeAPIModel {
        //todo api struct
    }
}
