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
import com.melself.journeygo.data.repository.VkRepository;

import java.util.List;

public class VkSignUpViewModel extends AndroidViewModel {
    private final CredoRepository credoRepository;
    private final VkRepository vkRepository;
    private final ProfileRepository profileRepository;
    public VkSignUpViewModel(@NonNull Application application) {
        super(application);
        credoRepository = new CredoRepository(application);
        profileRepository = new ProfileRepository(application);
        vkRepository = new VkRepository();
    }

    public void insertProfileCredo(Profile profile, DBCredo dbCredo){
        profileRepository.insertProfileCredo(profile, dbCredo);
    }
    public void insertProfile(Profile profile){
        profileRepository.insertProfile(profile);
    }

    public LiveData<List<Profile>> getAllProfileView(){
        return profileRepository.getAllProfileLive();
    }

    public DBCredo getUsr(String usr){
        return credoRepository.getUsr(usr);
    }

    public LiveData<Profile> getInfoProfile(String token, String userId){
        return vkRepository.getInfoProfile(token, userId);
    }

    public LiveData<DBCredo> getUsernameVk(String token, String userId){
        return vkRepository.getUsername(token, userId);
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

    public List<DBCredo> getAllCredo(){
        return credoRepository.getAllCredo();
    }

    public DBCredo getCredoId(long id){
        return credoRepository.getUserFromIdLive(id);
    }
}