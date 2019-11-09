package com.example.animalsapp.model;

import com.example.animalsapp.di.DaggerApiComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnimalApiService {



     @Inject
     AnimalApi api;


     public AnimalApiService(){
         DaggerApiComponent.create().inject(this);
     }

//     AnimalApi api = new Retrofit.Builder()
//             .baseUrl(BASE_URL)
//             .addConverterFactory(GsonConverterFactory.create())
//             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//             .build()
//             .create(AnimalApi.class);

     public Single<ApiKeyModel> getApiKey(){
         return api.getApiKey();
     }

     public Single<List<AnimalModel>> getAnimals(String key){
         return api.getAnimals(key);
     }
}
