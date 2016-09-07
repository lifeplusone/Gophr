package com.example.khalilvanalphen.gophr;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;
import android.view.View;
import android.content.Context;
/**
 * Created by khalilvanalphen on 2016-09-07.
 */
public class ViewUtilities {
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager =(InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
