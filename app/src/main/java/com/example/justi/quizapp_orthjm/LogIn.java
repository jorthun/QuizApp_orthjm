package com.example.justi.quizapp_orthjm;


import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class LogIn extends AppCompatActivity {
    EditText emailAddress, password;
    Button logIn, register;
    public static ArrayList<String[]> userInfo;
    public static ArrayList<String[]> gameInfo;
    public static String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        emailAddress = (EditText) findViewById(R.id.logInID);
        password = (EditText) findViewById(R.id.logInPassword);
        logIn = (Button) findViewById(R.id.logInButton);
        register = (Button) findViewById(R.id.goToRegisterButton);
    }
    /*
    Sends user to registration page
     */
    public void goToRegistration(View view){
        startActivity(new Intent(LogIn.this,Registration.class));
    }
    /*
    Attempts to log in the user with input information
     */
    public void testLogIn(View view){
        boolean loginFound = false;
        //loops through user information
        for (int i = 0; i < userInfo.size();i++){
            //attempts to find users email
            email = userInfo.get(i)[2];
            if(email.equals(emailAddress.getText().toString().trim())){
                //check if users email matches password if found
                String pass = userInfo.get(i)[4];
                if (pass.equals(password.getText().toString().trim()))
                    loginFound = true;
            }
        }
        //displays if the log in information was found or not
        if (loginFound == false){
            Toast.makeText(this,"Invalid Email or Password", Toast.LENGTH_LONG).show();
        }else {
            Intent mainMenu = new Intent(LogIn.this, MainMenu.class);
            startActivity(mainMenu);
            finish();
        }
    }

}

