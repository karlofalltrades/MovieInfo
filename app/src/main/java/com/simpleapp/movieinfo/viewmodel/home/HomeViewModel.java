package com.simpleapp.movieinfo.viewmodel.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simpleapp.movieinfo.data.repository.Repository;
import com.simpleapp.movieinfo.data.service.ApiService;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.model.MovieResponse;
import com.simpleapp.movieinfo.utils.retrofit.RetrofitClientInstance;

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
//        apiService.getMovies("star", "au", "movie")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new SingleObserver<MovieResponse>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onSuccess(MovieResponse movieResponse) {
//                        moviesLiveData.setValue(movieResponse.getMovies());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        // Handle error
//                    }
//                });
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }
}

//public class HomeViewModel extends ViewModel {
//    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
//    private Repository repository; // Assume you have a Repository class to handle data operations
//
//    public HomeViewModel(Context context) {
//        repository = new Repository(context);
//    }
//
//    public LiveData<List<Movie>> getMoviesLiveData() {
//        return moviesLiveData;
//    }
//
//    public void fetchMovies() {
//        List<Movie> cachedMovies = repository.retrieveFromCache();
//        if (cachedMovies != null && !cachedMovies.isEmpty()) {
//            // Data exists in cache, update LiveData
//            moviesLiveData.setValue(cachedMovies);
//        } else {
//            // Data not in cache, fetch from API
//            repository.fetchMoviesFromApi(new Repository.MovieCallback() {
//                @Override
//                public void onSuccess(List<Movie> movies) {
//                    // Save to cache
//                    repository.saveToCache(movies);
//                    // Update LiveData
//                    moviesLiveData.setValue(movies);
//                }
//
//                @Override
//                public void onFailure() {
//                    // Handle failure
//                }
//            });
//        }
//    }
//}


