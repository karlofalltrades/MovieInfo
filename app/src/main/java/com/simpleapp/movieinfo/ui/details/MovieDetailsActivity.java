package com.simpleapp.movieinfo.ui.details;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.databinding.ActivityMovieDetailsBinding;
import com.simpleapp.movieinfo.viewmodel.moviedetails.MovieDetailsViewModel;

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
    }
}