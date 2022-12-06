package com.melself.journeygo.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.melself.journeygo.data.DBCredo;
import com.melself.journeygo.data.model.Country;
import com.melself.journeygo.data.repository.CredoRepository;

import java.util.List;

public class AdminViewModel extends AndroidViewModel {
    private final CredoRepository credoRepository;
    public AdminViewModel(@NonNull Application application) {
        super(application);
        credoRepository = new CredoRepository(application);
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