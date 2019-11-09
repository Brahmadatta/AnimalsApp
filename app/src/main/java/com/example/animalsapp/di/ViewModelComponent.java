package com.example.animalsapp.di;

import com.example.animalsapp.viewmodel.ListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ApiModule.class,AppModule.class,PrefModule.class})
@Singleton
public interface ViewModelComponent {

    void inject(ListViewModel model);
}
