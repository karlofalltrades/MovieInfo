package com.simpleapp.movieinfo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.adapters.HomeAdapter;
import com.simpleapp.movieinfo.databinding.FragmentHomeBinding;
import com.simpleapp.movieinfo.viewmodel.home.HomeViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerViewHome;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.init(getContext());
        homeViewModel.fetchMovies();

        homeViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), movies -> {
            adapter.setMovies(movies);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homeViewModel.disposeSubscription();
        binding = null;
    }
}