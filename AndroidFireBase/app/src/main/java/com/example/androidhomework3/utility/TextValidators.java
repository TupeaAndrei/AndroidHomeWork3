package com.example.androidhomework3.utility;

import android.text.TextUtils;

public class TextValidators {
    public static boolean isValidTitle(String title){
        return !TextUtils.isEmpty(title);
    }

    public static boolean isValidAuthor(String author){
        return !TextUtils.isDigitsOnly(author);
    }

    public static boolean isValidDescription(String description){
        return !TextUtils.isEmpty(description);
    }

    /*Even tough the methods are the same i kept them separate in case i need some more
    validators in the future!
     */
}
