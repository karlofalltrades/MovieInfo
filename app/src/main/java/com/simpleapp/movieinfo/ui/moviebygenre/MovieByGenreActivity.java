package com.simpleapp.movieinfo.ui.moviebygenre;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.model.Movie;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieByGenreActivity extends AppCompatActivity {

    private Map<String, List<Movie>> genreMoviesMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_by_genre);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private HashMap<String, List<Movie>> getMovieFromIntent(){
        return (HashMap<String, List<Movie>>) getIntent().getSerializableExtra("movie");
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