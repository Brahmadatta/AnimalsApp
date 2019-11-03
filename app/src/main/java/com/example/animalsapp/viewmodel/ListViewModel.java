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
        AnimalModel animal4 = new AnimalModel("lion");
        AnimalModel animal5 = new AnimalModel("zebra");
        AnimalModel animal6 = new AnimalModel("cheetah");
        AnimalModel animal7 = new AnimalModel("lion");
        AnimalModel animal8 = new AnimalModel("zebra");
        AnimalModel animal9 = new AnimalModel("cheetah");
        AnimalModel animal10 = new AnimalModel("lion");
        AnimalModel animal11 = new AnimalModel("zebra");
        AnimalModel animal12 = new AnimalModel("cheetah");

        List<AnimalModel> animalModelList = new ArrayList<>();
        animalModelList.add(animal1);
        animalModelList.add(animal2);
        animalModelList.add(animal3);
        animalModelList.add(animal4);
        animalModelList.add(animal5);
        animalModelList.add(animal6);
        animalModelList.add(animal7);
        animalModelList.add(animal8);
        animalModelList.add(animal9);
        animalModelList.add(animal10);
        animalModelList.add(animal11);
        animalModelList.add(animal12);

        animals.setValue(animalModelList);
        loadError.setValue(false);
        loading.setValue(false);

    }
}
