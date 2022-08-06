package com.tp.quizapp.questions;

import com.tp.quizapp.questions.Question;
import com.tp.quizapp.utility.CategoryId;

import org.json.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class QuestionManager extends Thread {

    ArrayList<JSONObject> questionsJSON = new ArrayList<>();
    ArrayList<Question> questions = new ArrayList<>();

    private int questionAmount;
    private String questionCategory;

    public QuestionManager(int questionAmount,String questionCategory) {
        this.questionAmount = questionAmount;
        this.questionCategory = questionCategory;
    }



    private void fetchJson() throws IOException, JSONException, InterruptedException {
        String data = "";
        CategoryId categoryId = new CategoryId(questionCategory);
        categoryId.start();
        categoryId.join();
        try {
            URL url = new URL("https://opentdb.com/api.php?amount=" +
                    questionAmount +
                    "&category=" +
                    categoryId.getId() +
                    "&type=multiple&encode=base64");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }

            if (!data.isEmpty()) {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray results = jsonObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {
                    JSONObject q = results.getJSONObject(i);
                    questionsJSON.add(q);
                }
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JSONObject> getQuestionsJSON() {
        return questionsJSON;
    }




    @Override
    public void run() {
        try {
            fetchJson();
        } catch (JSONException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
