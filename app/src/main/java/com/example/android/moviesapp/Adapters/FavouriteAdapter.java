package com.example.android.moviesapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.Network.Api;
import com.example.android.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder>{

    List<MovieR>movieRS;
    Context context;

    public List<MovieR> getMovieRS() {
        return movieRS;
    }

    public void setMovieRS(List<MovieR> movieRS) {
        this.movieRS = movieRS;

        notifyDataSetChanged();
    }

    public FavouriteAdapter(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    public FavouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.favourite_row_item,parent,false);
        return new FavouriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteHolder holder, int position) {
        holder.title.setText(movieRS.get(position).getTitle());
        holder.bind(movieRS.get(position));

    }

    @Override
    public int getItemCount() {
      if(movieRS==null)
      {
          return 0;
      }
      else return movieRS.size();
    }

    class FavouriteHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;

        public FavouriteHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.tv_fav_movie_title);
            poster=itemView.findViewById(R.id.iv_fav_movie_poster);
        }

        public void bind(MovieR movie)
        {
            URL posterUrl= Api.buildPosterUrl(movie.getPoster());
            Picasso.with(context)
                    .load(posterUrl.toString())
                    .placeholder(R.color.colorAccent)
                    .into(poster);
        }

    }
}
