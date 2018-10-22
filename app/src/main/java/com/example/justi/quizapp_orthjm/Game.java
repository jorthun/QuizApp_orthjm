package com.example.justi.quizapp_orthjm;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.justi.quizapp_orthjm.LogIn.email;
import static com.example.justi.quizapp_orthjm.LogIn.gameInfo;

public class Game extends AppCompatActivity {

    CheckBox choiceOne, choiceTwo, choiceThree, choiceFour;
    TextView questionNumber, questionTxt;
    questionObject quiz[];
    int quizQuestions = 5;
    int currentQuestion = 1;
    int quizPoints = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        choiceOne = (CheckBox) findViewById(R.id.choice1);
        choiceTwo = (CheckBox) findViewById(R.id.choice2);
        choiceThree = (CheckBox) findViewById(R.id.choice3);
        choiceFour = (CheckBox) findViewById(R.id.choice4);
        questionNumber = (TextView) findViewById(R.id.questionNumberDisplay);
        questionTxt = (TextView) findViewById(R.id.questionDisplay);
        //make a new quiz with 5 questions
        quiz = new questionObject[quizQuestions];
            quiz[0] = new questionObject(1, "16 + 8 =", "28", "30" , "32", "24" , 1000);
            quiz[1] = new questionObject(2,"Select all numbers that are divisible by 10", "40", "12", "60", "26", 101);
            quiz[2] = new questionObject(3, "6 * 8 =", "58", "48", "44", "56",10);
            quiz[3] = new questionObject(4,"66 / 2 =", "33", "12","39", "29", 1);
            quiz[4] = new questionObject(5, "102 - 39 =","57", "74", "63", "71", 100);
            //reload variables lost from screen rotation
            if(savedInstanceState != null){
                currentQuestion = savedInstanceState.getInt("currentQues");
                quizPoints = savedInstanceState.getInt("currentPoints");
            }
        questionDisplay(quiz[currentQuestion - 1]);
    }

    /*
    Displays the info for a question
     */
    public void questionDisplay(questionObject question){
        questionNumber.setText("Question " + question.questionNumb);
        questionTxt.setText(question.questionText);
        choiceOne.setText(question.answerOne);
        choiceTwo.setText(question.answerTwo);
        choiceThree.setText(question.answerThree);
        choiceFour.setText(question.answerFour);
    }
/*
checks if it is the end of the quiz, if it is go to the score screen, if not bring up the next question
 */
    public void submitPressed(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(Game.this);
        builder.setMessage("Are you sure about this Awnser?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        answerChecker(quiz[currentQuestion - 1]);
                        //go to the next question
                        currentQuestion++;
                        //reset the quiz and display the results screen if the last question is finished
                        if(currentQuestion > quizQuestions){
                            currentQuestion = 1;
                            gameInfo.add(new String[]{email, Integer.toString(quizPoints)});
                            saveArrayList(gameInfo, "gameData");
                            quizPoints = 0;
                            Intent scoreScreen = new Intent(Game.this, ScoreScreen.class);
                            startActivity(scoreScreen);
                            finish();
                        }
                        questionDisplay(quiz[currentQuestion - 1]);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    /*
    checks answer of the user, by creating a number the same way question object stores numbers by building a number from user input
     */
    public void answerChecker(questionObject question){
        int userAnswer = 0;
        if(choiceOne.isChecked()){
            userAnswer += 1;
            choiceOne.toggle();
        }
        if(choiceTwo.isChecked()){
            userAnswer += 10;
            choiceTwo.toggle();
        }
        if(choiceThree.isChecked()){
            userAnswer += 100;
            choiceThree.toggle();
        }
        if(choiceFour.isChecked()){
            userAnswer += 1000;
            choiceFour.toggle();
        }
        if(userAnswer == question.correctAnswers){
            quizPoints++;
        }
    }

    /*
    Save an arraylist to the phone
     */
    public void saveArrayList(ArrayList<String[]> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

    /*
    Save variables so they arent lost on rotation
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentQues", currentQuestion);
        outState.putInt("currentPoints", quizPoints);
    }
}
/*
Stores information for a question
 */
class questionObject{
    int questionNumb;
    String questionText, answerOne, answerTwo, answerThree, answerFour;

    //Answers are ints that contain binary code for each answer, 1=1, 10=2, 100=3, 1000=4
    //if answer 1 and 4 were correct, the number input would be 1001
    int correctAnswers;
    public questionObject(int qNumb, String qText, String aOne, String aTwo, String aThree, String aFour, int cAwnsers){
        questionNumb = qNumb;
        questionText = qText;
        answerOne = aOne;
        answerTwo = aTwo;
        answerThree = aThree;
        answerFour = aFour;
        correctAnswers = cAwnsers;
    }
}
