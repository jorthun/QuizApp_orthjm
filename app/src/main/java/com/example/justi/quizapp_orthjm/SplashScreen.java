package com.example.justi.quizapp_orthjm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.justi.quizapp_orthjm.LogIn.userInfo;
import static com.example.justi.quizapp_orthjm.LogIn.gameInfo;

public class SplashScreen extends AppCompatActivity {

    private static int TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Convert data, if the data does not exist, make a blank arraylist
        userInfo = getArrayList("logInData");
        if(userInfo == null){
            userInfo = new ArrayList<String[]>();
        }
        gameInfo = getArrayList("gameData");
        if(gameInfo == null){
            gameInfo = new ArrayList<String[]>();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeScreen = new Intent(SplashScreen.this, LogIn.class);
                startActivity(homeScreen);
                finish();
            }
        },TIME_OUT);
    }
    /*
    Converts data on phone to an ArrayList
     */
    public ArrayList<String[]> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String[]>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
