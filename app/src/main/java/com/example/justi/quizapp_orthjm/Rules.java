package com.example.justi.quizapp_orthjm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Rules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
    }
    public void returnToMenuFromRules(View view){
        finish();
    }
}
