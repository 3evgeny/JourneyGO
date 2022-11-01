package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.data.repository.ProfileRepository;

public class SignUpViewModel extends AndroidViewModel {

    private final ProfileRepository profileRepository;
    private LiveData<Profile> getCountry;

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
    }

    public void insert(Profile profile){
        profileRepository.insertProfile(profile);
    }

    public void update(Profile profile){
        profileRepository.updateProfile(profile);
    }

    public void delete(Profile profile){
        profileRepository.deleteProfile(profile);
    }

    public LiveData<Profile> getProfileFromView(long id){
        return profileRepository.getProfileLive(id);
    }
}