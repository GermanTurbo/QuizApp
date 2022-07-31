package com.example.quizapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Base64;

public class Decoder {


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decodeBase64(String stringToDecode){
        byte[] decodedBytes = Base64.getDecoder().decode(stringToDecode);
        return new String(decodedBytes);
    }
}
