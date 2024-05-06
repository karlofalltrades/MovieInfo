package com.simpleapp.movieinfo.viewmodel.favorites;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.reflect.TypeToken;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.utils.cache.CacheManager;

import java.lang.reflect.Type;
import java.util.List;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> moviesLiveData;
    private Context context;
    private List<Movie> movieList;

    public void setContext(Context context){
        this.context = context;
        getMovies();
    }

    public LiveData<List<Movie>> getMovies() {
        moviesLiveData = new MutableLiveData<>();
        loadMovies();
        return moviesLiveData;
    }

    private void loadMovies() {
        Type listType = new TypeToken<List<Movie>>() {}.getType();
        movieList = CacheManager.getFromCache(context, "movie_favorites", listType);
        moviesLiveData.postValue(movieList);
    }
}