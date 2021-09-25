package com.example.tugas14_anisahasna.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigAPI {
    public static APIHandler getApiHandler(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.115/Android/employee/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIHandler apiHandler = retrofit.create(APIHandler.class);
        return apiHandler;
    }
}
