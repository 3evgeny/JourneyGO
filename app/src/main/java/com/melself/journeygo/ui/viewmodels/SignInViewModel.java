package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Profile;
import com.melself.journeygo.data.repository.CredoRepository;
import com.melself.journeygo.data.repository.ProfileRepository;

import java.util.List;

public class SignInViewModel extends AndroidViewModel {
    private final ProfileRepository profileRepository;
    private final CredoRepository credoRepository;
    private LiveData<Profile> getCountry;

    public SignInViewModel(@NonNull Application application) {
        super(application);
        profileRepository = new ProfileRepository(application);
        credoRepository = new CredoRepository(application);
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

    public LiveData<List<Profile>> getAllProfile(){
        return profileRepository.getAllProfileLive();
    }

    public Profile getFirstNameView(String firstName){
        return profileRepository.getFirstNameLive(firstName);
    }

    public DBCredo getUsr(String usr){
        return credoRepository.getUsr(usr);
    }

    public LiveData<DBCredo> getPsw(String psw){
        return credoRepository.getPsw(psw);
    }
}