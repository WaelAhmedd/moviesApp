package com.example.android.moviesapp.Network;

import com.example.android.moviesapp.Model.MovieR;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebMovieResponse {
    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<MovieR> movies = null;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<MovieR> getMovies() {
        return movies;
    }
}
