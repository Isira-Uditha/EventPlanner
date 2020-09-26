package com.example.eventplanner;

//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputValidatorHelper {

    public boolean isNullOrEmpty(String string){

        return TextUtils.isEmpty(string);
    }

    public boolean ischeckText(String string){

        if(string.length()<5){

            return true;
        }

        return false;
    }


    public boolean isNumeric(String string){
        return TextUtils.isDigitsOnly(string);
    }
}

  


