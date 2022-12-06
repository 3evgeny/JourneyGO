package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.model.Hotel;
import com.melself.journeygo.data.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;

public class HotelViewModel extends ViewModel {
    private final HotelRepository hotelRepository = new HotelRepository();

    public LiveData<List<Hotel>> getHotels(String country){
        return hotelRepository.getAllHotels(country);
    }
}