package com.simpleapp.movieinfo.viewmodel.moviedetails;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.reflect.TypeToken;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.utils.cache.CacheManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailsViewModel extends ViewModel {
    private Context context;
    private List<Movie> movieList;
    private MediatorLiveData<String> mediatorLiveData = new MediatorLiveData<>();
    private Type listType;
    private boolean isMovieFavorite = false;

    public void init(Context context) {
        this.context = context;
        getMovieFavoritesList();
    }

    public boolean isMovieInFavorites(Movie movie) {
        if (movieList != null && !movieList.isEmpty()) {
            for (Movie m : movieList) {
                if (m.getTrackName().trim().equalsIgnoreCase(movie.getTrackName().trim())) {
                    isMovieFavorite = true;
                }
            }
            return isMovieFavorite;
        } else return false;
    }

    public void addToFavorites(Movie movie) {
        if (movieList == null) {
            movieList = new ArrayList<>();
        }
        if (isMovieInFavorites(movie)) {
        } else {
            movieList.add(movie);
            saveToCache();
        }
    }

    public void saveToCache() {
        CacheManager.saveToCacheDir(context, "movie_favorites.json", movieList);
        movieList = null;
    }

    public void removeFromFavorites(Movie movie) {
        movieList.remove(movie);
        saveToCache();
    }

    public void getMovieFavoritesList() {
        listType = new TypeToken<List<Movie>>() {}.getType();
        movieList = CacheManager.getFromCacheDir(context, "movie_favorites.json", listType);
        if (movieList != null && !movieList.isEmpty()) {
            int count = 0;
            for (Movie movie : movieList) {
                Log.d("MOVIE:" , movie.getTrackName());
                Log.d("MOVIE INDEX:", movieList.indexOf(movie) + "");
                Log.d("COUNT", ""+count++);
            }

        }
    }
}
