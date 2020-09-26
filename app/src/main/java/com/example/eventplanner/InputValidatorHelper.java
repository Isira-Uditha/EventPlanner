package com.example.eventplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorHelper extends AppCompatActivity {


    public boolean isNullOrEmpty(String string){
        return TextUtils.isEmpty(string);
    }



    public boolean isNumeric(String string){
        return TextUtils.isDigitsOnly(string);
    }

}