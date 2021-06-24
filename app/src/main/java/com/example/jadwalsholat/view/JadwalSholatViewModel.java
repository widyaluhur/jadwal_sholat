package com.example.jadwalsholat.view;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.jadwalsholat.model.DataItem;
import com.example.jadwalsholat.model.JadwalSholatResponse;
import com.example.jadwalsholat.service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalSholatViewModel extends ViewModel {

    private ApiMain apiMain;

    private MutableLiveData<ArrayList<DataItem>> listJadwalSholat = new MutableLiveData<>();

    public void setJadwalSholat(){
        if(this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiJadwalSholat().getJadwalSholat().enqueue(new Callback<JadwalSholatResponse>() {
            @Override
            public void onResponse(Call<JadwalSholatResponse> call, Response<JadwalSholatResponse> response) {
                JadwalSholatResponse responseJadwalSholat = response.body();
                if(responseJadwalSholat != null && responseJadwalSholat.getData() != null){
                    ArrayList<DataItem> jadwalsholatItems = responseJadwalSholat.getData();
                    listJadwalSholat.postValue(jadwalsholatItems);
                    Log.d("JadwalSholatViewModel", "onSuccess: "+response.body());
                }
            }

            @Override
            public void onFailure(Call<JadwalSholatResponse> call, Throwable t) {
                Log.e("JadwalSholatViewModel", "onFailure: "+t.getMessage());

            }
        });
    }

    public LiveData<ArrayList<DataItem>> getJadwalSholat(){
        return listJadwalSholat;
    }
}
