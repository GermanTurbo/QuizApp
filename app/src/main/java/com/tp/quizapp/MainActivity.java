package com.tp.quizapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import quizapp.R;

public class MainActivity extends AppCompatActivity {

    private int questionAmount;

    TextView questionAmountTextField;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         questionAmountTextField = findViewById(R.id.questionAmountTextField);

        startButton();
    }

    private void startButton() {
        Button startButton = findViewById(R.id.start_btn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = questionAmountTextField.getText().toString();
                questionAmount =Integer.parseInt(value);
                Intent i = new Intent(MainActivity.this,QuestionActivity.class);
                i.putExtra("questionAmount",questionAmount);
                startActivity(i);
            }
        });
    }
}
