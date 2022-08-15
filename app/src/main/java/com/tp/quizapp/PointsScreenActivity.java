package com.tp.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

import quizapp.R;

public class PointsScreenActivity extends AppCompatActivity {

    private String filename = "pointsHistory.txt";
    private int points;
    private boolean allCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_screen);

        Bundle extras = getIntent().getExtras();
        points = extras.getInt("points");

        pointsText();
        saveLatestRound();
        returnToMenuButton();
    }

    private void pointsText() {
        TextView pointsTxt = findViewById(R.id.points_text2);
        pointsTxt.setText("Points: " + points);
    }

    private void saveLatestRound() {
        SharedPreferences latestPoints = getApplicationContext().getSharedPreferences("LATEST_POINTS", 0);
        SharedPreferences.Editor editor = latestPoints.edit();
        editor.putInt("homeScore", points);
        editor.apply();

    }

    private void returnToMenuButton() {
        Button returnToMenuBtn = findViewById(R.id.return_to_menu_btn);
        returnToMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PointsScreenActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}