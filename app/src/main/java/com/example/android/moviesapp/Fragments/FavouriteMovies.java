package com.example.android.moviesapp.Fragments;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.moviesapp.Adapters.FavouriteAdapter;
import com.example.android.moviesapp.AppExecutors;
import com.example.android.moviesapp.Database.AppDatabase;
import com.example.android.moviesapp.Model.MovieR;
import com.example.android.moviesapp.R;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteMovies extends Fragment  {
        private Context context;
    private AppDatabase mdp;
    private FavouriteAdapter favouriteAdapter;
    private RecyclerView favList;
        List<MovieR>movieRlist;
    public FavouriteMovies() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourite_movies, container, false);

        context=getContext();

        mdp=AppDatabase.getsInstance(context);
        favouriteAdapter=new FavouriteAdapter(context);
        favList=view.findViewById(R.id.rv_favourites_list);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
        favList.setLayoutManager(gridLayoutManager);
        favList.setAdapter(favouriteAdapter);


        DividerItemDecoration decoration = new DividerItemDecoration(context, VERTICAL);
        favList.addItemDecoration(decoration);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position=viewHolder.getAdapterPosition();
                        List<MovieR> movieRS=favouriteAdapter.getMovieRS();
                        mdp.movieDao().deleteMovie(movieRS.get(position).getId());
                    }
                });
            }
        }).attachToRecyclerView(favList);

        retrieveData();
        return  view;
    }

    private void retrieveData() {
        LiveData<List<MovieR>> movies=mdp.movieDao().getAllMovies();
        movies.observe( this, new Observer<List<MovieR>>() {
            @Override
            public void onChanged(@Nullable List<MovieR> movieRS) {
                favouriteAdapter.setMovieRS(movieRS);
           movieRlist=movieRS;
            }
        });
    }
}
