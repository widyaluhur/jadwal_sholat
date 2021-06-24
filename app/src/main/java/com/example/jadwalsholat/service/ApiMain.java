package com.example.jadwalsholat.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMain {

    private Retrofit retrofit;

    public JadwalSholatRepository getApiJadwalSholat(){
        String BASE_URL = "https://api.aladhan.com/";
        if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(JadwalSholatRepository.class);
    }
}
