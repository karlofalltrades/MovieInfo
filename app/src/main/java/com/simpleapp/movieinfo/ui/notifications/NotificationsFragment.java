package com.simpleapp.movieinfo.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.adapters.FavoritesAdapter;
import com.simpleapp.movieinfo.databinding.FragmentNotificationsBinding;
import com.simpleapp.movieinfo.ui.details.MovieDetailsActivity;
import com.simpleapp.movieinfo.viewmodel.favorites.NotificationsViewModel;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    private FavoritesAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        notificationsViewModel.setContext(getContext());
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        RecyclerView recyclerView = binding.favoritesRecyclerView;
        adapter = new FavoritesAdapter(new ArrayList<>());
        notificationsViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null && !movies.isEmpty()) {
                adapter.updateList(movies);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
        setAdapterClickListener();
        return binding.getRoot();
    }

    private void setAdapterClickListener() {
        adapter.setOnItemClickListener(movie -> {
            Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        notificationsViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            if (movies != null && !movies.isEmpty()) {
                adapter.updateList(movies);
            }else {
                adapter.updateList(new ArrayList<>());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}