package com.example.animalsapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface AnimalApi {

    @GET("getKey")
    public Single<ApiKeyModel> getApiKey();

    @POST("getAnimals")
    public Single<List<AnimalModel>> getAnimals(@Field("key") String key);


    /*@GET
    Single<Object> getObjects(@Url String url);*/



}
