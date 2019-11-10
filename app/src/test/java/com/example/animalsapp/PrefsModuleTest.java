package com.example.animalsapp;

import android.app.Application;

import com.example.animalsapp.di.PrefModule;
import com.example.animalsapp.utils.SharedPreferenceHelper;

public class PrefsModuleTest extends PrefModule {

    SharedPreferenceHelper mockPrefs;

    public PrefsModuleTest(SharedPreferenceHelper mockPrefs){
        this.mockPrefs = mockPrefs;
    }

    @Override
    public SharedPreferenceHelper provideSharedPreferences(Application application) {
        return mockPrefs;
    }
}
