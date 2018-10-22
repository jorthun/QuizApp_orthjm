package com.example.justi.quizapp_orthjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void goToRules(View view){
        Intent rulesScreen = new Intent(MainMenu.this, Rules.class);
        startActivity(rulesScreen);
    }
    public void goToLogIn(View view){
        Intent logInScreen = new Intent(MainMenu.this, LogIn.class);
        startActivity(logInScreen);
        finish();
    }
    public void gotoGame(View view){
        Intent gameScreen= new Intent(MainMenu.this, Game.class);
        startActivity(gameScreen);
        finish();
    }
    public void goToResults(View view){
        Intent resultsScreen = new Intent(MainMenu.this, Results.class);
        startActivity(resultsScreen);
    }
}
