package com.melself.journeygo.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.melself.journeygo.data.AppDatabase;
import com.melself.journeygo.data.CredoDAO;
import com.melself.journeygo.data.DBCredo;
import java.util.List;

public class CredoRepository {

    private CredoDAO credoDAO;

    public CredoRepository(Application application){
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        credoDAO = appDatabase.getCredoDAO();
    }

    public void insertCredo(DBCredo dbCredo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            credoDAO.insertCredo(dbCredo);
        });
    }

    public void updateCredo(DBCredo dbCredo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            credoDAO.updateCredo(dbCredo);
        });
    }


    public void deleteCredo(DBCredo dbCredo){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            credoDAO.deleteCredo(dbCredo);
        });
    }

    public LiveData<List<DBCredo>> getAllCredoLive(){
        return credoDAO.getAllCredo();
    }

    public List<DBCredo> getAllCredo(){
        return credoDAO.getAllCredoNonLive();
    }

    public DBCredo getUserFromIdLive(long id){
        return credoDAO.getCredo(id);
    }

    public DBCredo getUsr(String usr){
        return credoDAO.getUsername(usr);
    }

    public LiveData<DBCredo> getPsw(String psw){
        return credoDAO.getPassword(psw);
    }
}
