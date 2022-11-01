package com.melself.journeygo.data.repository;

import android.app.Application;
import android.os.Build;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.melself.journeygo.data.AppDatabase;
import com.melself.journeygo.data.CountryDAO;
import com.melself.journeygo.data.DBCountry;
import com.melself.journeygo.data.DBProfile;
import com.melself.journeygo.data.ProfileDAO;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProfileRepository {

    private ProfileDAO profileDAO;
    //private LiveData<List<DBProfile>> getAllProfileRepository;
    private LiveData<DBProfile> getProfileRepository;

    public ProfileRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        profileDAO = appDatabase.getProfileDAO();
    }

    public void insertProfile(Profile profile){
        DBProfile dbProfile = DBProfile.convertToDBProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.insertProfile(dbProfile);
        });
    }

    public void updateProfile(Profile profile){
        DBProfile dbProfile = DBProfile.convertToDBProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.updateProfile(dbProfile);
        });
    }

    public void deleteProfile(Profile profile){
        DBProfile dbProfile = DBProfile.convertToDBProfile(profile);
        AppDatabase.databaseWriteExecutor.execute(() -> {
            profileDAO.deleteProfile(dbProfile);
        });
    }

//    public LiveData<List<Country>> getAllCountriesLive(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return Transformations.map(countryDAO.getAllCountries(), dbCountries -> dbCountries.stream().map(dBCountry -> dBCountry.convertToCountry()).collect(Collectors.toList()));
//        }
//        return new MutableLiveData<List<Country>>(new ArrayList<>());
//    }

    public LiveData<Profile> getProfileLive(long id){
        //return Transformations.map(countryDAO.getCountry(id), dbCountries -> dbCountries.convertToCountry());
        return Transformations.map(profileDAO.getProfile(id), dbProfile -> dbProfile.convertToProfile());
    }
}
