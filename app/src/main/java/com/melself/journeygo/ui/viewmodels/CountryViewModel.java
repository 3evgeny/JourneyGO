package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.repository.CountryRepository;
import com.melself.journeygo.data.repository.CredoRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private final CountryRepository countryRepository;
    private final CredoRepository credoRepository;
    private LiveData<List<Country>> getAllCountries;
    private LiveData<Country> getCountry;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
        getAllCountries = countryRepository.getAllCountriesLive();
        credoRepository = new CredoRepository(application);
    }

    public void insert(Country country){
        countryRepository.insertCountry(country);
    }

    public void update(Country country){
        countryRepository.updateCountry(country);
    }

    public void delete(Country country){
        countryRepository.deleteCountry(country);
    }

    public LiveData<List<Country>> getAllCountriesFromView(){
        return getAllCountries;
    }

    public LiveData<Country> getCountryFromView(long id){
        return countryRepository.getCountryLive(id);
    }

    public DBCredo getCredoId(long id){
        return credoRepository.getUserFromIdLive(id);
    }
}