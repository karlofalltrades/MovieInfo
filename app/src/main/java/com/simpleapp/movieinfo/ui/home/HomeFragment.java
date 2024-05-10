package com.simpleapp.movieinfo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.adapters.MovieGenreRecyclerViewManager;
import com.simpleapp.movieinfo.databinding.FragmentHomeBinding;
import com.simpleapp.movieinfo.viewmodel.home.HomeViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private MovieGenreRecyclerViewManager movieGenreRecyclerViewManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init(getContext());
        homeViewModel.fetchMovies();

        setupRecyclerViews();
        return root;
    }

    private void setupRecyclerViews() {
        homeViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), movies -> {
            LinearLayout layout = binding.getRoot().findViewById(R.id.recycler_view_container);
            movieGenreRecyclerViewManager = new MovieGenreRecyclerViewManager(getActivity(), layout);
            movieGenreRecyclerViewManager.addMovies(movies, 0);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeViewModel.disposeSubscription();
        binding = null;
    }
}