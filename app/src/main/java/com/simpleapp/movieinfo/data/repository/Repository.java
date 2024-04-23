package com.simpleapp.movieinfo.data.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.simpleapp.movieinfo.data.service.ApiService;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.model.MovieResponse;
import com.simpleapp.movieinfo.utils.cache.CacheManager;
import com.simpleapp.movieinfo.utils.retrofit.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private CacheManager<List<Movie>> cacheManager; // Assume you have a CacheManager instance
    private Context context;

    public Repository(Context context) {
        cacheManager = new CacheManager<>(getContext());
        this.context = context;
    }

    public void fetchMoviesFromApi(MovieCallback callback) {
        ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<MovieResponse> call = service.getMovies("star","au","movie");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getMovies();
                    callback.onSuccess(movies);
                } else {
                    callback.onFailure();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Toast.makeText(context, "Failed to fetch movies", Toast.LENGTH_SHORT).show();
                callback.onFailure();
            }
        });
    }

    public void saveToCache(List<Movie> movies) {
        cacheManager.saveToDiskCache("movies", movies);
    }

    public List<Movie> retrieveFromCache() {
        return cacheManager.retrieveFromDiskCache("movies");
    }

    public interface MovieCallback {
        void onSuccess(List<Movie> movies);
        void onFailure();
    }

    private Context getContext() {
        // Implement this method to get the context (e.g., from application context)
        return context;
    }
}

