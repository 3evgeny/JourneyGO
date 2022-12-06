package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.DBProfile;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.data.repository.CredoRepository;
import com.melself.journeygo.data.repository.ProfileRepository;

import java.util.List;

public class SignUpViewModel extends AndroidViewModel {

    private final ProfileRepository profileRepository;
    private final CredoRepository credoRepository;
    private LiveData<Profile> getCountry;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        credoRepository = new CredoRepository(application);
    }

    public void insertProfileCredo(Profile profile, DBCredo dbCredo){
        profileRepository.insertProfileCredo(profile, dbCredo);
    }

    public void insertProfile(Profile profile){
        profileRepository.insertProfile(profile);
    }
    public void updateProfile(Profile profile){
        profileRepository.updateProfile(profile);
    }

    public void deleteProfile(Profile profile){
        profileRepository.deleteProfile(profile);
    }

    public void insert(DBCredo dbCredo){
        credoRepository.insertCredo(dbCredo);
    }

    public void update(DBCredo dbCredo){
        credoRepository.updateCredo(dbCredo);
    }

    public void delete(DBCredo dbCredo){
        credoRepository.deleteCredo(dbCredo);
    }

    public LiveData<Profile> getProfileFromView(long id){
        return profileRepository.getProfileLive(id);
    }

    public LiveData<List<Profile>> getAllProfileView(){
        return profileRepository.getAllProfileLive();
    }
}