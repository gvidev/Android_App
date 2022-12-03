package com.uni.plovdiv.hapnitopni.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uni.plovdiv.hapnitopni.MainActivity;
import com.uni.plovdiv.hapnitopni.R;
import com.uni.plovdiv.hapnitopni.entities.Users;
import com.uni.plovdiv.hapnitopni.repository.MyDBHandler;

public class RegisterActivity extends AppCompatActivity {

    //there I use to declare my input fields
    EditText fullNameET;
    EditText emailET;
    EditText passwordET;
    //And the database(db handler)
    MyDBHandler myDbHandler;
    Button registerB;
    TextView loginB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //initialize them
        fullNameET = findViewById(R.id.nameEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        myDbHandler = new MyDBHandler(this, null,null,1);
        registerB = findViewById(R.id.registerButton);
        loginB = findViewById(R.id.loginButton);

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            //Add a user-info to the database
            public void onClick(View view) {
                //get the info from the fields
                String password = passwordET.getText().toString();
                String email = emailET.getText().toString();
                String fullName = fullNameET.getText().toString();

                Users user  = new Users(password,email,fullName);

                //case that every field is empty
                if(email.equals("")||password.equals("")||fullName.equals("")){
                    Toast.makeText(RegisterActivity.this, "Моля въведете всички полета!", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkUserExist = myDbHandler.checkRegistrationExist(user);
                    if(checkUserExist == false){
                        Boolean insert =  myDbHandler.Registration(user);
                        if(insert == true){
                            Toast.makeText(RegisterActivity.this, "Успешно се регистрирахте!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(RegisterActivity.this, "Грешка по време на регистрация!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(RegisterActivity.this, "Вече има регистрация с този имейл!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }




}