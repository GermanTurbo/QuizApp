package com.tp.quizapp.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CategoryId extends Thread {

    private String category;

    private long id;

    public CategoryId(String category){
        this.category = category;
    }

    @Override
    public long getId() {
        return id;
    }

    public long fetchId()  {
        try{
            String data = "";
            ArrayList<JSONObject> categories = new ArrayList<>();

            URL url = new URL("https://opentdb.com/api_category.php");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }
            if (!data.isEmpty()) {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray trivia_categories = jsonObject.getJSONArray("trivia_categories");
                for (int i = 0; i < trivia_categories.length(); i++) {
                    JSONObject q = trivia_categories.getJSONObject(i);
                    if (q.getString("name").equals(category)) {
                        return q.getInt("id");
                    }
                }
            }

        }catch (IOException | JSONException e){
            e.getMessage();
        }

        return -1;
    }

    @Override
    public void run() {
        id = fetchId();
    }
}
