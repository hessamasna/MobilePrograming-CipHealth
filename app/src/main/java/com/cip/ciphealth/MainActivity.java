package com.cip.ciphealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.cip.ciphealth.db.AppDatabase;
import com.cip.ciphealth.model.Diet;
import com.cip.ciphealth.model.FoodRecipe;
import com.cip.ciphealth.model.HealthTips;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        addData();

        Thread background = new Thread() {
            public void run() {
                try {
                    sleep(3 * 1000);

                    Intent i = new Intent(getBaseContext(), Login.class);
                    startActivity(i);

                    finish();
                } catch (Exception e) {
                }
            }
        };
        background.start();
    }

    public void addData() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        db.healthTipsDao().deleteAll();
        db.dietDao().deleteAll();

        HealthTips healthTips = new HealthTips("Do not skip breakfast", "Skipping breakfast will not help you lose weight. You could miss out on essential nutrients and you may end up snacking more throughout the day because you feel hungry.", "https://www.nhs.uk/better-health/lose-weight/", "2023");
        HealthTips healthTips1 = new HealthTips("Eat plenty of fruit and veg", "Fruit and veg are low in calories and fat, and high in fibre – 3 essential ingredients for successful weight loss. They also contain plenty of vitamins and minerals.", "https://www.nhs.uk/live-well/eat-well/5-a-day/", "2023");
        HealthTips healthTips2 = new HealthTips("Drink plenty of water", "People sometimes confuse thirst with hunger. You can end up consuming extra calories when a glass of water is really what you need.", "https://www.nhs.uk/live-well/eat-well/food-guidelines-and-food-labels/water-drinks-nutrition/", "2023");
        HealthTips healthTips3 = new HealthTips("Read food labels", "Knowing how to read food labels can help you choose healthier options. Use the calorie information to work out how a particular food fits into your daily calorie allowance on the weight loss plan.", "https://www.nhs.uk/live-well/eat-well/food-guidelines-and-food-labels/how-to-read-food-labels/", "2023");
        HealthTips healthTips4 = new HealthTips("Do not ban foods", "Do not ban any foods from your weight loss plan, especially the ones you like. Banning foods will only make you crave them more. There's no reason you cannot enjoy the occasional treat as long as you stay within", "https://www.nhs.uk/live-well/healthy-weight/managing-your-weight/12-tips-to-help-you-lose-weight/", "2023");
        db.healthTipsDao().insertHealthTips(healthTips);
        db.healthTipsDao().insertHealthTips(healthTips1);
        db.healthTipsDao().insertHealthTips(healthTips2);
        db.healthTipsDao().insertHealthTips(healthTips3);
        db.healthTipsDao().insertHealthTips(healthTips4);

        Diet diet = new Diet("DASH Diet", "Weight Loss Diets", "The DASH diet, which stands for dietary approaches to stop hypertension, is a flexible, balanced and heart-healthy eating plan promoted by the National Heart, Lung and Blood Institute to do exactly that: to stop (or prevent) hypertension, also referred to as high blood pressure.", "https://health.usnews.com/best-diet/dash-diet");
        Diet diet1 = new Diet("Mediterranean Diet", "Diabetes Diets", "The Mediterranean diet is based on the traditional way of eating in the 21 countries that border the Mediterranean Sea, including Greece, Italy, Croatia, Lebanon, Turkey and Monaco. Obviously, the people who live in this diverse area eat different types of food. The bottom line is that, according to years of research and evidence, primarily eating plant-based foods, such as fruits and vegetables, while incorporating whole grains, beans, nuts, seafood, lean poultry and unsaturated fat from extra-virgin olive oil is incredibly good for overall well-being.", "https://health.usnews.com/best-diet/mediterranean-diet");
        Diet diet2 = new Diet("3-Day Diet", "Fast Diets", "The military diet, also called the 3-day diet or the 3-day military diet, is a low-calorie plan that’s designed for quick weight loss. The military diet claims that you can lose 10 pounds in a week. The first three days of the diet follow an extremely low-calorie menu, while during the following four days, you are free to eat as you normally would.", "https://health.usnews.com/best-diet/3-day-diet");
        Diet diet3 = new Diet("Flexitarian Diet", "Healthy Eating Diets", "Flexitarian is a marriage of two words: flexible and vegetarian. The term was coined more than a decade ago by registered dietitian Dawn Jackson Blatner in her 2009 book, \"The Flexitarian Diet: The Mostly Vegetarian Way to Lose Weight, Be Healthier, Prevent Disease and Add Years to Your Life.\"", "https://health.usnews.com/best-diet/flexitarian-diet");
        db.dietDao().insertDiet(diet);
        db.dietDao().insertDiet(diet1);
        db.dietDao().insertDiet(diet2);
        db.dietDao().insertDiet(diet3);
    }
}