package com.simpleapp.movieinfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.model.Movie;
import com.simpleapp.movieinfo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView artWorkImage;
        TextView trackName, trackPrice, primaryGenre;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            artWorkImage = itemView.findViewById(R.id.imageViewArtwork);
            trackName = itemView.findViewById(R.id.textViewTrackName);
            trackPrice = itemView.findViewById(R.id.textViewTrackPrice);
            primaryGenre = itemView.findViewById(R.id.textViewPrimaryGenre);
        }

        public void bind(Movie movie) {
            Picasso.get().load(extractIdFromUrl(movie.getArtworkUrl100())).into(artWorkImage);
            trackName.setText(movie.getTrackName());
            trackPrice.setText(String.valueOf(movie.getTrackPrice()));
            primaryGenre.setText(movie.getPrimaryGenreName());
        }

        private String extractIdFromUrl(String url) {
            return url.replace("100x100", "200x200");
        }
    }
}

