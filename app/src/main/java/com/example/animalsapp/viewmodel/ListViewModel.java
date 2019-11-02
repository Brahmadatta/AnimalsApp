package com.example.animalsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animalsapp.model.AnimalModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {


    public MutableLiveData<List<AnimalModel>> animals = new MutableLiveData<List<AnimalModel>>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();


    public void refresh(){
        AnimalModel animal1 = new AnimalModel("lion");
        AnimalModel animal2 = new AnimalModel("zebra");
        AnimalModel animal3 = new AnimalModel("cheetah");

        List<AnimalModel> animalModelList = new ArrayList<>();
        animalModelList.add(animal1);
        animalModelList.add(animal2);
        animalModelList.add(animal3);

        animals.setValue(animalModelList);
        loadError.setValue(false);
        loading.setValue(false);

    }
}
