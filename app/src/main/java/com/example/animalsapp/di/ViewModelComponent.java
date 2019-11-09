package com.example.animalsapp.di;

import com.example.animalsapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {ApiModule.class})
public interface ViewModelComponent {

    void inject(ListViewModel model);
}
