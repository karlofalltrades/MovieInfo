package com.simpleapp.movieinfo.viewmodel.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.reflect.TypeToken;
import com.simpleapp.movieinfo.data.repository.Repository;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.utils.cache.CacheManager;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private Repository repository;
    private Disposable disposable;
    private Context context;

    public void init(Context context) {
        this.context = context;
        repository = new Repository();
    }

    public void fetchMovies() {
        Type listType = new TypeToken<List<Movie>>() {}.getType();
        List<Movie> cachedMovies = CacheManager.getFromCacheDir(context, "movies_cache.json", listType);
        if (cachedMovies != null && !cachedMovies.isEmpty()) {
            moviesLiveData.setValue(cachedMovies);
        } else {
            disposable = repository.fetchMoviesFromApi()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movies -> {
                        moviesLiveData.setValue(movies);
                        CacheManager.saveToCacheDir(context, "movies_cache.json", movies);
                    }, throwable -> {
                        Log.e("HomeViewModel", "Failed to fetch movies", throwable);
                    });
        }
    }

    public LiveData<List<Movie>> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void disposeSubscription () {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
