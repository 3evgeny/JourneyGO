package com.melself.journeygo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CredoDAO {

    @Insert
    void insertCredo(DBCredo dbCredo);

    @Update
    void updateCredo(DBCredo dbCred);

    @Delete
    void deleteCredo(DBCredo dbCred);

    @Query("SELECT * FROM credo WHERE _id ==:id ")
    DBCredo getCredo(long id);

    @Query("SELECT * FROM credo WHERE _username ==:username ")
    DBCredo getUsername(String username);

    @Query("SELECT * FROM credo WHERE _password ==:password ")
    LiveData<DBCredo> getPassword(String password);

    @Query("SELECT * FROM credo")
    LiveData<List<DBCredo>> getAllCredo();

    @Query("SELECT * FROM credo")
    List<DBCredo> getAllCredoNonLive();
}
