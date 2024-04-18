package com.simpleapp.movieinfo.api;

import com.simpleapp.movieinfo.model.MovieResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("search")
    Single<MovieResponse> getMovies(
            @Query("term") String term,
            @Query("country") String country,
            @Query("media") String media
    );
}

