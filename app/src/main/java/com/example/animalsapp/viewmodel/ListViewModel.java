package com.example.animalsapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animalsapp.model.AnimalApiService;
import com.example.animalsapp.model.AnimalModel;
import com.example.animalsapp.model.ApiKeyModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {

    private AnimalApiService mAnimalApiService = new AnimalApiService();

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public MutableLiveData<List<AnimalModel>> animals = new MutableLiveData<List<AnimalModel>>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();


    public void refresh() {
        loading.setValue(true);
        getKey();
    }

    private void getKey() {

        mDisposable.add(

                mAnimalApiService.getApiKey()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ApiKeyModel>() {
                            @Override
                            public void onSuccess(ApiKeyModel key) {

                                if (key.key.isEmpty()){
                                    loadError.setValue(true);
                                    loading.setValue(false);
                                }else {
                                    getAnimals(key.key);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                                e.printStackTrace();
                                loading.setValue(false);
                                loadError.setValue(true);
                            }
                        })
        );

    }

    private void getAnimals(String key) {

        mDisposable.add(
                mAnimalApiService.getAnimals(key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<AnimalModel>>() {
                    @Override
                    public void onSuccess(List<AnimalModel> animalModels) {

                        loadError.setValue(false);
                        animals.setValue(animalModels);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        loading.setValue(false);
                        loadError.setValue(true);
                    }
                })

        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }
}
