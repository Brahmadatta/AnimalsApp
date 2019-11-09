package com.example.animalsapp.di;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;

import com.example.animalsapp.utils.SharedPreferenceHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.example.animalsapp.di.TypeOfContext.CONTEXT_ACTIVITY;
import static com.example.animalsapp.di.TypeOfContext.CONTEXT_APP;

@Module
public class PrefModule {

    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_APP)
    public SharedPreferenceHelper provideSharedPreferences(Application application){
        return new SharedPreferenceHelper(application);
    }

    @Provides
    @Singleton
    @TypeOfContext(CONTEXT_ACTIVITY)
    public SharedPreferenceHelper provideActivitySharedPreferences(AppCompatActivity appCompatActivity){
        return new SharedPreferenceHelper(appCompatActivity);
    }
}
