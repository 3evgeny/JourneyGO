package com.melself.journeygo.data.repository;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.melself.journeygo.data.model.Hotel;
import com.melself.journeygo.data.retrofit.HotelService;
import com.melself.journeygo.data.retrofit.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelRepository {

    //Retrofit
    MutableLiveData<List<Hotel>> list = new MutableLiveData<>();
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://engine.hotellook.com/api/")
            .build();
    private HotelService hotel = retrofit.create(HotelService.class);

    public LiveData<List<Hotel>> getAllHotels(String country){

        MutableLiveData<List<Hotel>> result = new MutableLiveData<>();

        hotel.listHotelAndPrice(country).enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.isSuccessful()){

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                         result.setValue(response.body().stream().map(hh -> {
                            Hotel hotel = new Hotel();
                            hotel.setName(hh.hotelName);
                            hotel.setPrice(String.valueOf(hh.priceAvg));
                            hotel.setDescription(hh.location.name + "\n"
                                    + hh.location.country);
                            return hotel;
                        }).collect(Collectors.toList()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        return result;
    }
}
