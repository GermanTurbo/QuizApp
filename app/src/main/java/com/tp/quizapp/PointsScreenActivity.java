package com.tp.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import quizapp.R;

public class PointsScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points_screen);

        pointsText();
        returnToMenuButton();
    }

    private void pointsText() {
        TextView pointsTxt = findViewById(R.id.points_text2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            pointsTxt.setText("Points: " + extras.getInt("points"));
        }
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