package com.uni.plovdiv.hapnitopni;


import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;


import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uni.plovdiv.hapnitopni.Session.SessionManager;

import com.uni.plovdiv.hapnitopni.activities.StartActivity;
import com.uni.plovdiv.hapnitopni.adapters.productsAdapter;
import com.uni.plovdiv.hapnitopni.databinding.ActivityMainBinding;

import com.uni.plovdiv.hapnitopni.entities.Products;
import com.uni.plovdiv.hapnitopni.repository.MyDBHandler;


import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    MyDBHandler myDbHandler;
    Button exitButton;
    TextView currentDateTV;
    TextView emailForHeaderTV;
    TextView nameForHeaderTV;



    String currentDate ;

    //use this to get user_id from the login activity
    SessionManager session;

    //this should be the retrieved data from db
    String[] emailFromDB;
    String[] nameFromDB;


    int current_user_id;
    //


    RecyclerView recyclerView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new SessionManager(this);

        myDbHandler = new MyDBHandler(this, null,null, 1);
        currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        current_user_id = session.getSession();


        emailFromDB = myDbHandler.getUserEmail(String.valueOf(current_user_id));
        nameFromDB = myDbHandler.getUserName(String.valueOf(current_user_id));


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //

        recyclerView = findViewById(R.id.recycleView);
        ArrayList<Products> products = new ArrayList<>();
        products.add(new Products
                (R.drawable.pizza, "Пица Верона", "12.00лв.", "Много вкусна направо не знам!"));
        products.add(new Products
                (R.drawable.fish, "Крехко месце", "14.90лв.", "Приготвено на плоча с любов!"));

//        productsAdapter adapter = new productsAdapter(products,this);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);





        setSupportActionBar(binding.appBarMain.toolbar);


        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_menu, R.id.nav_favourite, R.id.nav_my_cart)
                .setOpenableLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        //here i initialize the optionMenu button- exit
        exitButton = findViewById(R.id.exitButton);

        emailForHeaderTV = findViewById(R.id.emailFromCurrentLoginTV);
        currentDateTV = findViewById(R.id.currentDateFull);
        nameForHeaderTV = findViewById(R.id.fullNameFromCurrentLoginTV);

        currentDateTV.setText(currentDate);
        emailForHeaderTV.setText(emailFromDB[0]);
        nameForHeaderTV.setText(nameFromDB[0]);





        //and set to go from one activity to start
        //and with that i reset the current state of program
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        });
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




}