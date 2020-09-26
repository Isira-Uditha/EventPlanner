package com.example.eventplanner;

import android.text.TextUtils;

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
