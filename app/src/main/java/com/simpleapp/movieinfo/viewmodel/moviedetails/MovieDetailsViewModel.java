package com.simpleapp.movieinfo.viewmodel.moviedetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simpleapp.movieinfo.model.Movie;

public class MovieDetailsViewModel extends ViewModel {
    private final MutableLiveData<Movie> movieMutableLiveData;

    public MovieDetailsViewModel() {
        movieMutableLiveData = new MutableLiveData<>();
    }

    public void setMovie(Movie movie) {
        movieMutableLiveData.postValue(movie);
    }

    public LiveData<Movie> getMovie() {
        return movieMutableLiveData;
    }
}
