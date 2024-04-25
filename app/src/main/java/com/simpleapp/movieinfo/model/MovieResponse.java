package com.simpleapp.movieinfo.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class MovieResponse {
    @SerializedName("resultCount")
    private int resultCount;

    @SerializedName("results")
    private List<Movie> movies;

    public int getResultCount() {
        return resultCount;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}


