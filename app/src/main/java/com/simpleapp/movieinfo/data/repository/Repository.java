package com.simpleapp.movieinfo.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.simpleapp.movieinfo.data.service.ApiService;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.model.MovieResponse;
import com.simpleapp.movieinfo.utils.cache.CacheManager;
import com.simpleapp.movieinfo.utils.retrofit.RetrofitClientInstance;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.core.Single;

public class Repository {
    private Context context;

    public Repository(Context context) {
        this.context = context;
    }

    public Single<List<Movie>> fetchMoviesFromApi() {
        return Single.create(emitter -> {
            ApiService service = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
            service.getMovies("star", "au", "movie")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<MovieResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            Log.d("SUBSCRIBING", d.toString());
                        }

                        @Override
                        public void onSuccess(MovieResponse movieResponse) {
                            List<Movie> movies = movieResponse.getMovies();
                            emitter.onSuccess(movies);
                        }

                        @Override
                        public void onError(Throwable e) {
                            emitter.onError(e);
                        }
                    });
        });
    }

    private Context getContext() {
        return context;
    }
}

