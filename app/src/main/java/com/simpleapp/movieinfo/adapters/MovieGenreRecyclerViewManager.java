package com.simpleapp.movieinfo.adapters;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieGenreRecyclerViewManager {

    private Context context;
    private LinearLayout parentLayout;
    private Map<String, List<Movie>> genreMoviesMap;

    public MovieGenreRecyclerViewManager(Context context, LinearLayout parentLayout) {
        this.context = context;
        this.parentLayout = parentLayout;
        this.genreMoviesMap = new HashMap<>();
    }

    public void addMovies(List<Movie> movies) {
        // Group movies by genre
        for (Movie movie : movies) {
            String genre = movie.getPrimaryGenreName();
            List<Movie> genreMovies = genreMoviesMap.get(genre);
            if (genreMovies == null) {
                genreMovies = new ArrayList<>();
                genreMoviesMap.put(genre, genreMovies);
            }
            genreMovies.add(movie);
        }

        // Create and populate RecyclerViews for each genre
        for (Map.Entry<String, List<Movie>> entry : genreMoviesMap.entrySet()) {
            String genre = entry.getKey();
            List<Movie> genreMovies = entry.getValue();

            RecyclerView recyclerView = createRecyclerView(genreMovies);
            parentLayout.addView(recyclerView);
        }
    }

    private RecyclerView createRecyclerView(List<Movie> movies) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        // Create and set adapter for RecyclerView
        HomeAdapter adapter = new HomeAdapter();
        adapter.setMovies(movies);
        recyclerView.setAdapter(adapter);

        return recyclerView;
    }
}

