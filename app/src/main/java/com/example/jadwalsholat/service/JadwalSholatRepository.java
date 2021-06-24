package com.example.jadwalsholat.service;

import com.example.jadwalsholat.model.JadwalSholatResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JadwalSholatRepository {
    @GET("v1/calendarByAddress?address=Yogyakarta&method=2&month=Juni&year=2021")
    Call<JadwalSholatResponse> getJadwalSholat();
}
