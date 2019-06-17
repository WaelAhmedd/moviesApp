package com.example.android.moviesapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.Network.Api;
import com.example.android.moviesapp.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.MyHolder> {
    private List<MovieR>movies;
    private Context context;
 final   private  ListItemClickListener mClickListener;
    public interface ListItemClickListener{
        void onListItemClick(int SelectedMovie);
    }


        public MainAdapter(Context context,ListItemClickListener clickListener)
        {
            this.context=context;
            this.mClickListener=clickListener;
        }

        public void setMovies(List<MovieR>movies)
        {
            this.movies=movies;
            notifyDataSetChanged();
        }

    public List<MovieR> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(context).inflate(R.layout.main_row_item,parent,false);
     return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        MovieR movie =movies.get(position);
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
            return (movies == null) ? 0 : movies.size();
    }



    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView moviePoster;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster=(ImageView)itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        public void bind(MovieR movie)
        {
            URL posterUrl= Api.buildPosterUrl(movie.getPoster());
            Picasso.with(context)
                    .load(posterUrl.toString())
                    .placeholder(R.color.colorAccent)
                    .into(moviePoster);
        }


        @Override
        public void onClick(View view) {
            mClickListener.onListItemClick(getAdapterPosition());
        }
    }


}
