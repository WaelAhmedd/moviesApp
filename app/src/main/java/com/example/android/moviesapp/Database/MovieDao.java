package com.example.android.moviesapp.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.android.moviesapp.Model.MovieR;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * From  Movies")
    LiveData<List<MovieR>>getAllMovies();
    @Insert
    void insertMovie(MovieR movieR);

    @Query("DELETE FROM Movies WHERE id = :movieId")
    void deleteMovie(String movieId);

    @Query("SELECT * FROM Movies WHERE id=:movieId")
  LiveData<MovieR>loadById(String movieId);

}

