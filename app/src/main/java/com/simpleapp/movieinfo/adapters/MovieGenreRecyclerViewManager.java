package com.simpleapp.movieinfo.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.ui.details.MovieDetailsActivity;

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
            parentLayout.addView(relativeLayout);
            RecyclerView recyclerView = createRecyclerView(genreMovies);
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

    private RecyclerView createRecyclerView(List<Movie> movies) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
        ));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        HomeAdapter adapter = new HomeAdapter();
        adapter.setMovies(movies);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(movie -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            context.startActivity(intent);
        });

        return recyclerView;
    }
}

