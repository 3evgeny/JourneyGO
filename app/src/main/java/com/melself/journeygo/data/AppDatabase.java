package com.melself.journeygo.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {DBCountry.class, DBTicket.class, DBProfile.class, DBCredo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static final int NUMBER_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_THREADS);

    public abstract CountryDAO getCountryDAO();
    public abstract TicketDAO getTicketDAO();
    public abstract ProfileDAO getProfileDAO();
    public abstract CredoDAO getCredoDAO();

    private static volatile AppDatabase INSTANCE;
    public static AppDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "JourneyDB").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
