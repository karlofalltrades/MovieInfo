package com.simpleapp.movieinfo.ui.moviebygenre;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.adapters.MovieGenreRecyclerViewManager;
import com.simpleapp.movieinfo.model.Movie;

import java.util.List;

public class MovieByGenreActivity extends AppCompatActivity {

    private List<Movie> movieList;
    private String genre;
    private MovieGenreRecyclerViewManager movieGenreRecyclerViewManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_by_genre);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        getMovieFromIntent();
        setTitle(genre);
        setupRecyclerView();
    }

    private void getMovieFromIntent(){
        genre = getIntent().getStringExtra("genre");
        movieList = (List<Movie>) getIntent().getSerializableExtra("movieList");
    }

    private void setupRecyclerView() {
        LinearLayout layout = findViewById(R.id.rv_genre);
        movieGenreRecyclerViewManager = new MovieGenreRecyclerViewManager(this, layout);
        movieGenreRecyclerViewManager.addMovies(movieList, 1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}