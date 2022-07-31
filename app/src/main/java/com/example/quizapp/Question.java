package com.example.quizapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

public class Question {

    private String titleQuestion;
    private String correctAnswer;
    private String incorrectAnswer0;
    private String incorrectAnswer1;
    private String incorrectAnswer2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Question(JSONObject questionJSON) throws JSONException {
        setTitleQuestion(Decoder.decodeBase64((String) questionJSON.get("question")));
        setCorrectAnswer(Decoder.decodeBase64((String) questionJSON.get("correct_answer")));
        setIncorrectAnswer0(Decoder.decodeBase64((String) questionJSON.getJSONArray("incorrect_answers").get(0)));
        setIncorrectAnswer1(Decoder.decodeBase64((String) questionJSON.getJSONArray("incorrect_answers").get(1)));
        setIncorrectAnswer2(Decoder.decodeBase64((String) questionJSON.getJSONArray("incorrect_answers").get(2)));
    }

    public String getTitleQuestion() {
        return titleQuestion;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getIncorrectAnswer0() {
        return incorrectAnswer0;
    }

    public String getIncorrectAnswer1() {
        return incorrectAnswer1;
    }

    public String getIncorrectAnswer2() {
        return incorrectAnswer2;
    }

    public void setTitleQuestion(String titleQuestion) {
        this.titleQuestion = titleQuestion;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setIncorrectAnswer0(String incorrectAnswer0) {
        this.incorrectAnswer0 = incorrectAnswer0;
    }

    public void setIncorrectAnswer1(String incorrectAnswer1) {
        this.incorrectAnswer1 = incorrectAnswer1;
    }

    public void setIncorrectAnswer2(String incorrectAnswer2) {
        this.incorrectAnswer2 = incorrectAnswer2;
    }


}
