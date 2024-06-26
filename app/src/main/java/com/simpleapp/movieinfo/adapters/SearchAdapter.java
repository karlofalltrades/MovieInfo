package com.simpleapp.movieinfo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simpleapp.movieinfo.R;
import com.simpleapp.movieinfo.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private OnItemClickListener onItemClickListener;

    public SearchAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public void updateList(List<Movie> newList) {
        movieList = newList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view, onItemClickListener, movieList);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView artWorkImage;
        TextView trackName, trackPrice, primaryGenre;

        public MovieViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener, List<Movie> movies) {
            super(itemView);
            artWorkImage = itemView.findViewById(R.id.imageViewArtwork);
            trackName = itemView.findViewById(R.id.textViewTrackName);
            trackPrice = itemView.findViewById(R.id.textViewTrackPrice);
            primaryGenre = itemView.findViewById(R.id.textViewPrimaryGenre);
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
        }

        private String extractIdFromUrl(String url) {
            return url.replace("100x100", "200x200");
        }
    }
}
