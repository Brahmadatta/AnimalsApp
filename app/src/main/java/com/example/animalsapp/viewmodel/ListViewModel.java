package com.example.animalsapp.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.animalsapp.di.AppModule;
import com.example.animalsapp.di.DaggerApiComponent;
import com.example.animalsapp.di.DaggerViewModelComponent;
import com.example.animalsapp.di.TypeOfContext;
import com.example.animalsapp.model.AnimalApiService;
import com.example.animalsapp.model.AnimalModel;
import com.example.animalsapp.model.ApiKeyModel;
import com.example.animalsapp.utils.SharedPreferenceHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.animalsapp.di.TypeOfContext.CONTEXT_APP;

public class ListViewModel extends AndroidViewModel {


    @Inject
    AnimalApiService mAnimalApiService;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public MutableLiveData<List<AnimalModel>> animals = new MutableLiveData<List<AnimalModel>>();
    public MutableLiveData<Boolean> loadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();

    @Inject
    @TypeOfContext(CONTEXT_APP)
    SharedPreferenceHelper prefs;

    private Boolean invalidApiKey = false;
    private Boolean injected = false;



    public ListViewModel(Application application){
        super(application);

    }

    public ListViewModel(Application application,Boolean isTest){
        super(application);
        injected = true;
    }

    private void inject(){
        if (!injected){
            DaggerViewModelComponent.builder()
                    .appModule(new AppModule(getApplication()))
                    .build()
                    .inject(this);
        }
    }


    public void refresh() {
        inject();
        loading.setValue(true);
        invalidApiKey = false;
        String key = prefs.getApiKey();
        if (key == null || key.equals("")){
            getKey();
        }else {
            getAnimals(key);
        }

    }

    public void hardRefresh(){
        loading.setValue(false);
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
                                    prefs.saveApiKey(key.key);
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
                        if (!invalidApiKey){
                            invalidApiKey = true;
                            getKey();
                        }else {
                            e.printStackTrace();
                            loading.setValue(false);
                            loadError.setValue(true);
                        }

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
