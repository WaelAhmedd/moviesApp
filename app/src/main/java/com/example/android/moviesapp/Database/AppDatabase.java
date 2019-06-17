package com.example.android.moviesapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.android.moviesapp.Model.MovieR;

@Database(entities = {MovieR.class},version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    private static final String DatabaseName="MoviesDatabase";
    private static AppDatabase sInstance;

    public static AppDatabase getsInstance(Context context)
    {
        if(sInstance==null)
        {
            sInstance= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DatabaseName).fallbackToDestructiveMigration()
                    .build();

        }
        return sInstance;
    }
    public abstract MovieDao movieDao();

}
