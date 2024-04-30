package com.simpleapp.movieinfo.viewmodel.search;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.reflect.TypeToken;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.utils.cache.CacheManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movies;
    private Context context;

    private List<Movie> cachedMovies;

    public void init(Context context) {
        this.context = context;
    }

    public LiveData<List<Movie>> getMovies() {
        if (movies == null) {
            movies = new MutableLiveData<>();
            loadMovies();
        }
        return movies;
    }

    private void loadMovies() {
        Type listType = new TypeToken<List<Movie>>() {}.getType();
        cachedMovies = CacheManager.getFromCacheDir(context, "movies_cache.json", listType);
        movies.postValue(cachedMovies);
    }

    public void searchMovies(String query) {
        if (movies != null) {
            List<Movie> filteredMovies = new ArrayList<>();
            for (Movie movie : movies.getValue()) {
                if (movie.getTrackName().contains(query)) {
                    filteredMovies.add(movie);
                }
            }
            if (!filteredMovies.isEmpty()) {
                movies.postValue(filteredMovies);
            }
        }
    }
}