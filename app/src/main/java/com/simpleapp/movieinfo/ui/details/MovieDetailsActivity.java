package com.simpleapp.movieinfo.ui.details;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import android.view.MenuItem;
import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.simpleapp.movieinfo.databinding.ActivityMovieDetailsBinding;
import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.viewmodel.moviedetails.MovieDetailsViewModel;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {

    private ActivityMovieDetailsBinding binding;
    private MovieDetailsViewModel movieDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        EdgeToEdge.enable(this);
        setContentView(view);

        movieDetailsViewModel.init(this);

        setupActionBarBackButton();

        Movie movie = getMovieFromIntent();
        if (movie != null) {
            bindDataToViews(movie);
        } else {
            Toast.makeText(this, "There was an error showing the details, try again later", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void setupActionBarBackButton() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private Movie getMovieFromIntent(){
        return (Movie) getIntent().getSerializableExtra("movie");
    }

    private void bindDataToViews(Movie movie) {
        binding.trackNameTextView.setText(movie.getTrackName());
        binding.priceTextView.setText(String.format("$%.2f", movie.getTrackPrice()));
        binding.genreTextView.setText(movie.getPrimaryGenreName());
        binding.artistTextView.setText(movie.getArtistName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault());
        binding.releaseDateTextView.setText(dateFormat.format(movie.getReleaseDate()));
        binding.descriptionTextView.setText(movie.getLongDescription());

        Picasso.get().load(extractIdFromUrl(movie.getArtworkUrl100())).into(binding.artworkImageView);
        setButtonClickListener(movie);
    }

    private void setButtonClickListener (Movie movie) {
        if (movieDetailsViewModel.isMovieInFavorites(movie)) binding.addToFavorites.setText("Remove from favorites");
        binding.addToFavorites.setOnClickListener(v -> {
            if (movieDetailsViewModel.isMovieInFavorites(movie)){
                movieDetailsViewModel.removeFromFavorites(movie);
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
                binding.addToFavorites.setText("Add to favorite");
            } else {
                movieDetailsViewModel.addToFavorites(movie);
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
                binding.addToFavorites.setText("Remove from favorite");
            }
        });
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

    private String extractIdFromUrl(String url) {
        return url.replace("100x100", "200x200");
    }
}