package com.example.justi.quizapp_orthjm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.justi.quizapp_orthjm.LogIn.email;
import static com.example.justi.quizapp_orthjm.LogIn.gameInfo;

public class Results extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ArrayList<String> resultInfo = new ArrayList<String>();
        for (int i = 0; i < gameInfo.size(); i++){
            if(gameInfo.get(i)[0].equals(email)){
                resultInfo.add(gameInfo.get(i)[1]);
            }
        }
        String[] resultString = resultInfo.toArray(new String[resultInfo.size()]);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.results_listview, resultString);
        ListView scoreListView = (ListView)findViewById(R.id.ScoreList);
        scoreListView.setAdapter(adapter);
    }
}
