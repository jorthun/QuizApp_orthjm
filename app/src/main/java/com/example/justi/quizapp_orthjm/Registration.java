package com.example.justi.quizapp_orthjm;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.example.justi.quizapp_orthjm.LogIn.userInfo;

public class Registration extends AppCompatActivity {
    EditText fName, lName, emailAddress, DOB, passwordOne, passwordTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        fName = (EditText) findViewById(R.id.firstName);
        lName = (EditText) findViewById(R.id.lastName);
        emailAddress = (EditText) findViewById(R.id.email);
        DOB = (EditText) findViewById(R.id.dateOfBirth);
        passwordOne = (EditText) findViewById(R.id.password1);
        passwordTwo = (EditText) findViewById(R.id.password2);
    }
    /*
    Checks new user details and registers the user
     */
    public void registerUser(View view){
        //check first name or clear text and display invalid input
        if(fName.getText().toString().trim().length() > 2 && fName.getText().toString().trim().length() < 30){
            //check last name or clear text and display invalid input
            if (lName.getText().toString().trim().length() > 2 && lName.getText().toString().trim().length() < 30){
                //check email or clear text and display invalid input
                if(emailAddress.getText().toString().trim().matches("^(.+)@(.+)$")){
                    //check if email is already registered
                    boolean emailIsntInDB = true;
                    for (int i = 0; i < userInfo.size();i++){
                        String email = userInfo.get(i)[2];
                        if(email.equals(emailAddress.getText().toString().trim())){
                            emailIsntInDB = false;
                        }
                    }
                    if(emailIsntInDB) {
                        //check DOB or clear text and display invalid input
                        if (DOB.getText().toString().trim().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
                            //check passwords or clear text and display invalid input
                            if (passwordOne.getText().toString().trim().length() > 4) {
                                //check if passwords match
                                if (passwordOne.getText().toString().trim().equals(passwordTwo.getText().toString().trim())) {
                                    //write data to array
                                    String[] info = {fName.getText().toString().trim(), lName.getText().toString().trim(),
                                            emailAddress.getText().toString().trim(), DOB.getText().toString().trim(),
                                            passwordOne.getText().toString().trim()};
                                    userInfo.add(info);
                                    saveArrayList(userInfo, "logInData");
                                    Toast.makeText(this, "User successfully registered", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(this, "Invalid Password, password must be at least 5 characters long", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(this, "Invalid Date Of Birth please format mm/dd/yyyy example", Toast.LENGTH_LONG).show();
                            DOB.setText("");
                        }
                    }else{
                        Toast.makeText(this,"Email already registered", Toast.LENGTH_LONG).show();
                        emailAddress.setText("");
                    }
                }else{
                    Toast.makeText(this,"Invalid Email", Toast.LENGTH_LONG).show();
                    emailAddress.setText("");
                }
            }else{
                Toast.makeText(this,"Invalid Last Name", Toast.LENGTH_LONG).show();
                lName.setText("");
            }
        }else{
            Toast.makeText(this,"Invalid First Name", Toast.LENGTH_LONG).show();
            fName.setText("");
        }
        passwordOne.setText("");
        passwordTwo.setText("");
    }
    /*
    Returns the user to the log in screen
     */
    public void returnToLogIn(View view){
        finish();
    }
    public void saveArrayList(ArrayList<String[]> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }
}

