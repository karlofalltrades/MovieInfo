package com.simpleapp.movieinfo.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.adapters.SearchAdapter;
import com.simpleapp.movieinfo.databinding.FragmentDashboardBinding;
import com.simpleapp.movieinfo.viewmodel.search.DashboardViewModel;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private DashboardViewModel dashboardViewModel;
    private RecyclerView recyclerView;
    private SearchAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        dashboardViewModel.init(getContext());
        recyclerView = binding.recyclerViewSearch;
        adapter = new SearchAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);

        dashboardViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> adapter.updateList(movies));
        recyclerView.setVisibility(View.INVISIBLE);
        SearchView searchView = binding.searchView;
        setupSearchViewListener(searchView);

        return binding.getRoot();
    }

    private void setupSearchViewListener(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.trim().isEmpty()) {
                    if (recyclerView.getVisibility() == View.GONE || recyclerView.getVisibility() == View.INVISIBLE) recyclerView.setVisibility(View.VISIBLE);
                    dashboardViewModel.searchMovies(query);
                } else {
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!newText.trim().isEmpty()) {
                    if (recyclerView.getVisibility() == View.GONE || recyclerView.getVisibility() == View.INVISIBLE) recyclerView.setVisibility(View.VISIBLE);
                    dashboardViewModel.searchMovies(newText);
                } else {
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}