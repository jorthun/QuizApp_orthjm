package com.example.justi.quizapp_orthjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.justi.quizapp_orthjm.LogIn.gameInfo;

public class ScoreScreen extends AppCompatActivity {

    TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        score = (TextView) findViewById(R.id.txtScore);
        score.setText(gameInfo.get(gameInfo.size() - 1)[1]);
    }
    public void returnToMenuFromScore(View view){
        Intent mainMenu = new Intent(ScoreScreen.this, MainMenu.class);
        startActivity(mainMenu);
        finish();
    }
}
