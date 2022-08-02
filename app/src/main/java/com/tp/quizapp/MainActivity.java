package com.tp.quizapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import quizapp.R;

public class MainActivity extends AppCompatActivity {


    private int questionAmount;

    TextView questionAmountTextField;

    FragmentContainerView navigationView;

    private ActionBar toolbar;

    private BottomNavigationView bottomNavigationView;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);


        questionAmountTextField = findViewById(R.id.questionAmountTextField);

        startButton();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;
            switch (item.getItemId()){
                case R.id.startGame:
                    fragment = new StartFragment();
                    //toolbar.setTitle(menus[0]);
                    loadFragment(fragment);
                    return true;
                case R.id.statsInformation:
                    //toolbar.setTitle(menus[1]);
                    fragment = new StatsFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.settings:
                    //toolbar.setTitle(menus[2]);
                    fragment = new SettingsFragment();
                    loadFragment(fragment);
                    return true;
            }
            return true;
        };
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        System.out.println("---------------------->"+ fragment);
    }


    private void startButton() {
        Button startButton = findViewById(R.id.start_btn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = questionAmountTextField.getText().toString();
                if(checkForCorrectInput(value)){
                    questionAmount = Integer.parseInt(value);
                    Intent i = new Intent(MainActivity.this, QuestionActivity.class);
                    i.putExtra("questionAmount", questionAmount);
                    startActivity(i);
                }
            }
        });
    }

    private boolean checkForCorrectInput(String value){
        if(value.equals("") || value.equals("0")){
            Toast.makeText(this, "Amount must be greater than 0", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
