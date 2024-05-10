package com.simpleapp.movieinfo.viewmodel.moviedetails;

import android.content.Context;
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
    private final Type listType = new TypeToken<List<Movie>>() {}.getType();
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
        CacheManager.saveToCache(context, "movie_favorites", movieList);
        movieList = null;
    }

    public void removeFromFavorites(Movie movie) {
        if (movieList != null && !movieList.isEmpty()) {
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getTrackName().trim().equalsIgnoreCase(movie.getTrackName().trim())) {
                    movieList.remove(i);
                }
            }
        }
        saveToCache();
    }

    public void getMovieFavoritesList() {
        movieList = CacheManager.getFromCache(context, "movie_favorites", listType);
    }
}
