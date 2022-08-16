package com.tp.quizapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tp.quizapp.questions.Question;
import com.tp.quizapp.questions.QuestionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import quizapp.R;

public class QuestionActivity extends AppCompatActivity {

    private int questionAmount;
    private String questionCategory;
    private int currRound = 0;

    ArrayList<JSONObject> questions;
    Question currQuestion;
    QuestionManager questionManager;

    TextView questionTitle;
    Button button_answer_1;
    Button button_answer_2;
    Button button_answer_3;
    Button button_answer_4;

    Button button_correct_answer;

    Button continueButton;

    TextView pointsText;


    private int points;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionTitle = findViewById(R.id.question_text);
        button_answer_1 = findViewById(R.id.answer_1);
        button_answer_2 = findViewById(R.id.answer_2);
        button_answer_3 = findViewById(R.id.answer_3);
        button_answer_4 = findViewById(R.id.answer_4);

        continueButton = findViewById(R.id.continue_button);
        continueButton.setVisibility(View.GONE);

        pointsText = findViewById(R.id.points_text);


        //get questionAmount from HomeFragment
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            questionAmount = extras.getInt("questionAmount");
            questionCategory = extras.getString("questionCategory");
        }

        questionManager = new QuestionManager(questionAmount, questionCategory);
        questionManager.start();
        try {
            questionManager.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        updatePointsText();
        generateQuestion();
        randomizeAnswers();

        button_answer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_1);
            }
        });

        button_answer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_2);
            }
        });

        button_answer_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_3);
            }
        });

        button_answer_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForCorrectAnswer(button_answer_4);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextQuestion();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void generateQuestion() {
        questions = questionManager.getQuestionsJSON();

        try {

            currQuestion = new Question(questions.get(currRound));
            questionTitle.setText(currQuestion.getTitleQuestion());

            Log.d("Q1", currQuestion.getTitleQuestion());
            Log.d("Q1", "Correct: " + currQuestion.getCorrectAnswer());
            Log.d("Q1", currQuestion.getIncorrectAnswer0());
            Log.d("Q1", currQuestion.getIncorrectAnswer1());
            Log.d("Q1", currQuestion.getIncorrectAnswer2());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void randomizeAnswers() {
        int max = 3;
        int min = 0;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        ArrayList<Button> answers = new ArrayList<>();
        answers.add(button_answer_1);
        answers.add(button_answer_2);
        answers.add(button_answer_3);
        answers.add(button_answer_4);

        answers.get(randomNum).setText(currQuestion.getCorrectAnswer());
        button_correct_answer = answers.get(randomNum);
        answers.remove(randomNum);

        answers.get(0).setText(currQuestion.getIncorrectAnswer0());
        answers.get(1).setText(currQuestion.getIncorrectAnswer1());
        answers.get(2).setText(currQuestion.getIncorrectAnswer2());

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void checkForCorrectAnswer(Button button) {
        if (button.getText().equals(currQuestion.getCorrectAnswer())) {
            win();
        } else {
            lose();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void lose() {
        showButtonColors();
        continueButton.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void win() {
        points++;
        showButtonColors();
        continueButton.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void nextQuestion() {
        updatePointsText();
        if (currRound == questionAmount - 1) {
            gameFinished();
        } else {
            continueButton.setVisibility(View.GONE);
            currRound++;
            hideButtonColors();
            generateQuestion();
            randomizeAnswers();
        }


    }

    private void gameFinished() {
        Intent i = new Intent(QuestionActivity.this, PointsScreenActivity.class);
        i.putExtra("points", points);
        startActivity(i);

    }


    private void updatePointsText() {
        pointsText.setText("Points: " + points + "/" + questionAmount);
    }

    private void showButtonColors() {
        button_answer_1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_answer_2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_answer_3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_answer_4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_dark_errorContainer));
        button_correct_answer.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.myGreen));

    }


    private void hideButtonColors() {
        button_answer_1.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_light_surfaceTintColor));
        button_answer_2.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_light_surfaceTintColor));
        button_answer_3.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_light_surfaceTintColor));
        button_answer_4.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.md_theme_light_surfaceTintColor));


    }
}