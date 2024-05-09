package com.simpleapp.movieinfo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private OnItemClickListener onItemClickListener;

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
        void onImageButtonClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_movie_item, parent, false);
        return new MovieViewHolder(view, onItemClickListener, movies);
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

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView artWorkImage;
        TextView trackName, trackPrice, primaryGenre;
        ImageButton imageButton;

        public MovieViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener, List<Movie> movies) {
            super(itemView);
            artWorkImage = itemView.findViewById(R.id.homeImageViewArtwork);
            trackName = itemView.findViewById(R.id.homeTextViewTrackName);
            trackPrice = itemView.findViewById(R.id.homeTextViewTrackPrice);
            primaryGenre = itemView.findViewById(R.id.homeTextViewPrimaryGenre);
            imageButton = itemView.findViewById(R.id.imageButtonFavorites);

            itemView.setOnClickListener( v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        onItemClickListener.onItemClick(movies.get(position));
                    }
                }
            });
        }

        public void bind(Movie movie) {
            Picasso.get().load(extractIdFromUrl(movie.getArtworkUrl100())).into(artWorkImage);
            trackName.setText(movie.getTrackName());
            trackPrice.setText(String.format("$%.2f", movie.getTrackPrice()) + " " + movie.getCurrency().toUpperCase());
            primaryGenre.setText(movie.getPrimaryGenreName());
            imageButton.setImageResource(!movie.isChecked() ? R.drawable.round_favorite_border_24 : R.drawable.round_favorite_24);
            imageButton.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                        onItemClickListener.onImageButtonClick(movies.get(getAdapterPosition()));
                        movie.setChecked(!movie.isChecked());
                        imageButton.setImageResource(!movie.isChecked() ? R.drawable.round_favorite_border_24 : R.drawable.round_favorite_24);
                    }
                }
            });
        }

        private String extractIdFromUrl(String url) {
            return url.replace("100x100", "200x200");
        }
    }
}

