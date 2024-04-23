package com.simpleapp.movieinfo.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        recyclerView = binding.recyclerViewHome;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);
        homeViewModel.fetchMovies();

        homeViewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), movies -> {
            adapter.setMovies(movies);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
//public class HomeFragment extends Fragment {
//    private RecyclerView recyclerView;
//    private HomeAdapter adapter;
//    private FragmentHomeBinding binding;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        HomeViewModel viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication())).get(HomeViewModel.class);
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View view = binding.getRoot();
//
//        recyclerView = binding.recyclerViewHome;
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new HomeAdapter();
//        recyclerView.setAdapter(adapter);
//        viewModel.fetchMovies();
//
//        viewModel.getMoviesLiveData().observe(getViewLifecycleOwner(), movies -> {
//            adapter.setMovies(movies);
//        });
//
//        return view;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}