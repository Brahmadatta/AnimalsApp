package com.example.animalsapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class SharedPreferenceHelper {

    private static final String API_KEY = "Api key";

    private SharedPreferences prefs;

    public SharedPreferenceHelper(Context context){
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveApiKey(String key){
        prefs.edit().putString(API_KEY,key).apply();
    }

    public String getApiKey(){
        return prefs.getString(API_KEY,null);
    }
}
