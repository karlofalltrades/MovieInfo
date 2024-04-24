package com.simpleapp.movieinfo.viewmodel.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.simpleapp.movieinfo.data.repository.Repository;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.utils.cache.CacheManager;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private Repository repository;
    private CacheManager<List<Movie>> cacheManager;

    public void init(Context context) {
        repository = new Repository(context);
        cacheManager = new CacheManager<>(context);
    }

    public void fetchMovies() {
        List<Movie> cachedMovies = retrieveFromCache();
        if (cachedMovies != null && !cachedMovies.isEmpty()) {
            moviesLiveData.setValue(cachedMovies);
        } else {
            repository.fetchMoviesFromApi()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movies -> {
                        // Update the moviesLiveData with the fetched movies
                        moviesLiveData.setValue(movies);
                        saveToCache(movies);
                    }, throwable -> {
                        // Handle failure, if needed
                        Log.e("HomeViewModel", "Failed to fetch movies", throwable);
                    });
        }
    }

    public void saveToCache(List<Movie> movies) {
        cacheManager.saveToDiskCache("movies", movies);
    }

    public List<Movie> retrieveFromCache() {
        return cacheManager.retrieveFromDiskCache("movies");
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


