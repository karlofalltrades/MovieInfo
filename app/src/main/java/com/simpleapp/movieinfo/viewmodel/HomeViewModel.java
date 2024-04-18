package com.simpleapp.movieinfo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simpleapp.movieinfo.api.RetrofitClientInstance;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.model.MovieResponse;
import com.simpleapp.movieinfo.api.ApiService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private ApiService apiService;

    public HomeViewModel() {
        apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
    }

    public void fetchMovies() {
        apiService.getMovies("star", "au", "movie")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(MovieResponse movieResponse) {
                        moviesLiveData.setValue(movieResponse.getMovies());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // Handle error
                    }
                });
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }
}


