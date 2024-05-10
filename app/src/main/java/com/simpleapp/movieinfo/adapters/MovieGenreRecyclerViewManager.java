package com.simpleapp.movieinfo.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;
import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.ui.details.MovieDetailsActivity;
import com.simpleapp.movieinfo.ui.moviebygenre.MovieByGenreActivity;
import com.simpleapp.movieinfo.utils.cache.CacheManager;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieGenreRecyclerViewManager {

    private Context context;
    private LinearLayout parentLayout;
    private Map<String, List<Movie>> genreMoviesMap;
    private List<Movie> favoriteList;
    private Type listType;

    public MovieGenreRecyclerViewManager(Context context, LinearLayout parentLayout) {
        this.context = context;
        this.parentLayout = parentLayout;
        this.genreMoviesMap = new HashMap<>();
        listType = new TypeToken<List<Movie>>() {}.getType();
        this.favoriteList = getFavoritesListCache();
    }

    public void addMovies(List<Movie> movies, int orientation) {
        if (parentLayout.getChildCount() > 0) {
            RecyclerView existingRecyclerView = (RecyclerView) parentLayout.getChildAt(parentLayout.getChildCount() - 1);
            existingRecyclerView.setAdapter(null);
            parentLayout.removeViewAt(parentLayout.getChildCount() - 1);
        }
        if (favoriteList != null && !favoriteList.isEmpty()) {
            for (int i = 0; i < movies.size(); i++) {
                for (int j = 0; j < favoriteList.size(); j++) {
                    if (movies.get(i).getTrackName().trim().equalsIgnoreCase(favoriteList.get(j).getTrackName().trim())) {
                        movies.get(i).setChecked(true);
                    }
                }
            }
        }

        for (Movie movie : movies) {
            String genre = movie.getPrimaryGenreName();
            if (!genreMoviesMap.containsKey(genre)) {
                genreMoviesMap.put(genre, new ArrayList<>());
            }
            genreMoviesMap.get(genre).add(movie);
        }

        for (Map.Entry<String, List<Movie>> entry : genreMoviesMap.entrySet()) {
            String genre = entry.getKey();
            List<Movie> genreMovies = entry.getValue();

            RelativeLayout relativeLayout = getRelativeLayout(genre);
            relativeLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, MovieByGenreActivity.class);
                intent.putExtra("movieList", (Serializable) genreMovies);
                intent.putExtra("genre", genre);
                context.startActivity(intent);
            });
            parentLayout.addView(relativeLayout);
            RecyclerView recyclerView = createRecyclerView(genreMovies, orientation);
            parentLayout.addView(recyclerView);
        }
    }

    private RelativeLayout getRelativeLayout(String genre) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(30, 0, 0, 0);

        TextView genreTextView = getTextView(genre, layoutParams);
        ImageView genreIcon = getImageView();

        RelativeLayout genreLayout = new RelativeLayout(context);
        layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        genreLayout.setLayoutParams(layoutParams);
        genreLayout.addView(genreTextView);
        genreLayout.addView(genreIcon);

        return genreLayout;
    }

    private TextView getTextView(String genre, RelativeLayout.LayoutParams layoutParams) {
        TextView genreTextView = new TextView(context);
        genreTextView.setLayoutParams(layoutParams);
        genreTextView.setText(genre);
        genreTextView.setTextSize(20);
        genreTextView.setTypeface(Typeface.DEFAULT_BOLD);
        return genreTextView;
    }

    private ImageView getImageView() {
        ImageView genreIcon = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.setMargins(0, 0, 16, 0);
        genreIcon.setLayoutParams(layoutParams);
        genreIcon.setImageResource(R.drawable.ic_chevron_right_24px);
        return genreIcon;
    }

    private RecyclerView createRecyclerView(List<Movie> movies, int orientation) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));
        if (orientation == 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, orientation, false);
            recyclerView.setLayoutManager(layoutManager);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        }
        HomeAdapter adapter = new HomeAdapter();
        adapter.setMovies(movies);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            }

            @Override
            public void onImageButtonClick(Movie movie) {
                if (movie.isChecked()) {
                    Toast.makeText(context, "Removed from favorites", Toast.LENGTH_SHORT).show();
                    removeFromFavorites(movie);
                } else {
                    Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show();
                    addToFavorites(movie);
                }
            }
        });

        return recyclerView;
    }

    private List<Movie> getFavoritesListCache() {
        return CacheManager.getFromCache(context, "movie_favorites", listType);
    }

    public void addToFavorites(Movie movie) {
        if (favoriteList == null) {
            favoriteList = new ArrayList<>();
        }
        if (!movie.isChecked()) {
            favoriteList.add(movie);
            saveToCache();
        }
    }

    public void saveToCache() {
        CacheManager.saveToCache(context, "movie_favorites", favoriteList);
    }

    public void removeFromFavorites(Movie movie) {
        if (favoriteList != null && !favoriteList.isEmpty()) {
            for (int i = 0; i < favoriteList.size(); i++) {
                if (favoriteList.get(i).getTrackName().trim().equalsIgnoreCase(movie.getTrackName().trim())) {
                    favoriteList.remove(i);
                }
            }
        }
        saveToCache();
    }
}

