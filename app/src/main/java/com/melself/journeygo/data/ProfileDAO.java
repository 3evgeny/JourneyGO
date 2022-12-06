package com.melself.journeygo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfileDAO {

    @Insert
    void insertProfile(DBProfile dbProfile);

    @Insert
    void insertProfileCredo(DBCredo dbCredo);

    @Transaction
    default void transaction(DBProfile dbProfile, DBCredo dbCredo){
        insertProfile(dbProfile);
        insertProfileCredo(dbCredo);
    }

    @Update
    void updateProfile(DBProfile dbProfile);

    @Delete
    void deleteProfile(DBProfile dbProfile);

    @Query("SELECT * FROM profile WHERE _id ==:id ")
    LiveData<DBProfile> getProfile(long id);

    @Query("SELECT * FROM profile WHERE _firstName ==:firstName")
    DBProfile getFirstName(String firstName);

    @Query("SELECT * FROM profile")
    LiveData<List<DBProfile>> getAllProfile();
}
