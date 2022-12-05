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
import com.uni.plovdiv.hapnitopni.Session.SessionManager;
import com.uni.plovdiv.hapnitopni.entities.Users;
import com.uni.plovdiv.hapnitopni.repository.MyDBHandler;

public class LoginActivity extends AppCompatActivity {


    //declare
    EditText emailET;
    EditText passwordET;
    MyDBHandler myDbHandler;
    SessionManager session;

    Button loginB;
    TextView registerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        myDbHandler = new MyDBHandler(this, null, null,1);
        loginB = findViewById(R.id.loginButton);
        registerTV = findViewById(R.id.registrationButton);
        session = new SessionManager(getApplicationContext());

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();


                Users user  = new Users(email,password);
                String id = myDbHandler.getUser(email,password);

                if(email.equals("")||password.equals("")){
                    Toast.makeText(LoginActivity.this, "Моля въведете всички полета!", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkLoginParametersExist = myDbHandler.checkLoginParametersExist(user);
                    if(checkLoginParametersExist == true){
                        Toast.makeText(LoginActivity.this, "Успешно се логнахте!", Toast.LENGTH_SHORT).show();
                        session.saveSession(Integer.parseInt(id));
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Невалидни данни!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }





    //this redirects to registration-activity
    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }


    //this is used for
}